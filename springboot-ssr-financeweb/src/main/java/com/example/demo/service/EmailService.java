package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.util.GmailOAuthSender;

@Service
public class EmailService {

	public void sendEmail(String to, String subject, String body) {
        try {
            var service = GmailOAuthSender.getGmailService();
            var email = GmailOAuthSender.createEmail(to, subject, body);
            GmailOAuthSender.sendMessage(service, "me", email);
        } catch (Exception e) {
            throw new RuntimeException("郵件發送失敗", e);
        }
    }
}
