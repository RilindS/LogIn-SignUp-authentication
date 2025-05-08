package com.example.LogIn.SignUp.Authentication.service;

import com.example.LogIn.SignUp.Authentication.data.email.ReplacedWildCardsDTO;
import com.example.LogIn.SignUp.Authentication.entity.PasswordResetToken;
import com.example.LogIn.SignUp.Authentication.entity.User;
import com.example.LogIn.SignUp.Authentication.repository.PasswordResetTokenRepository;
import com.example.LogIn.SignUp.Authentication.repository.UserRepository;
import com.example.LogIn.SignUp.Authentication.util.TemplateUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordResetTokenRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateUtil templateUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void sendPasswordResetCode(String email) throws MessagingException, IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        //Object user = userOpt.isPresent();

        String code = String.format("%06d", new Random().nextInt(999999));
        tokenRepository.deleteByEmail(email);

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setToken(code);
        token.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        Map<String, String> variables = emailService.replaceUserFields(user);
        variables.put("token", code);


        String bodyTemplatePath = "src/main/resources/templates/ChangePasswordTemplate.html";
        String bodyTemplate = new String(Files.readAllBytes(Paths.get(bodyTemplatePath)), StandardCharsets.UTF_8);

        ReplacedWildCardsDTO replacedWildCardsDTO = templateUtil.getReplacedWildCards(
                variables,
                "Password Reset Request",
                bodyTemplate
        );

        // Send email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject(replacedWildCardsDTO.getSubject());
        helper.setText(replacedWildCardsDTO.getBody(), true);
        mailSender.send(message);
    }

    @Transactional
    public void resetPassword(String email, String code, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        PasswordResetToken token = tokenRepository.findByEmailAndToken(email, code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired token"));

        if (token.isExpired()) {
            throw new IllegalArgumentException("Token expired");
        }

        if (!token.getToken().equals(code)) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.saveAndFlush(user);

        tokenRepository.delete(token);
    }


}
