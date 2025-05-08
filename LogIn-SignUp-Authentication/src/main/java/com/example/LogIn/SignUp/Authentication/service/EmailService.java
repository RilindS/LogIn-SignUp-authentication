package com.example.LogIn.SignUp.Authentication.service;

import com.example.LogIn.SignUp.Authentication.entity.User;
import com.example.LogIn.SignUp.Authentication.util.TemplateWildcards;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    public Map<String, String> replaceUserFields(User user) {
        Map<String, String> variables = new HashMap<>();

        variables.put(TemplateWildcards.USER_FIRST_NAME, user.getFirstName());
        variables.put(TemplateWildcards.USER_LAST_NAME, user.getLastName());
        variables.put(TemplateWildcards.USER_EMAIL, user.getEmail());
        variables.put(TemplateWildcards.USER_ID, user.getId().toString());

        variables.put(TemplateWildcards.REQUEST_TIME, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return variables;
    }


}
