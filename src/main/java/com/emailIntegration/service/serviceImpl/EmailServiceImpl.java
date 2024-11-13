package com.emailIntegration.service.serviceImpl;

import com.emailIntegration.entity.Email;
import com.emailIntegration.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    public JavaMailSender javaMailSender;
    private final String FROM="priyanka.batch3.codecrafter@gmail.com";
    @Override
    public Email sendMail(Email email)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email.getFrom());
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getBody());
        javaMailSender.send(simpleMailMessage);
        return email;
    }

    @Override
    public void sendMailsToMultipleUsers(String from, String[] to, String subject, String body)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMailWithHtml(Email email, String htmlFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(htmlFile, true);
        MimeMessage mimeMessage1 = mimeMessageHelper.getMimeMessage();
        javaMailSender.send(mimeMessage1);

    }


    @Override
    public void sendMailWithAttachment(String from, String to, String subject, String body, File file) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true, "UTF-8");
        mmh.setFrom(from);
        mmh.setTo(to);
        mmh.setSubject(subject);
        mmh.setText(body);
        FileSystemResource fs = new FileSystemResource(file);
        mmh.addAttachment(Objects.requireNonNull(fs.getFilename()),file);
        javaMailSender.send(mm);
    }

}
