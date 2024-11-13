package com.emailIntegration.service;

import com.emailIntegration.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public interface EmailService
{
    Email sendMail(Email email);

    public void sendMailsToMultipleUsers(String from, String[] to, String subject, String body);

    public void sendMailWithHtml(Email email, String htmlFile) throws MessagingException;

    public void sendMailWithAttachment(String from, String to, String subject, String body, File file) throws MessagingException;

}
