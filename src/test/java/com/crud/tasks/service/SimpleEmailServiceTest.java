package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailBuilder mailBuilder;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test subject", "Test message", "");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        Optional.ofNullable(mail.getToCc())
                .ifPresent(c ->mailMessage.setCc(mail.getToCc()));

        //SimpleMailMessage mailMessage = simpleEmailService.createMailMessage(mail);

        //When
        simpleEmailService.send(mail, mailBuilder);

        //Then
        verify(javaMailSender, times(1)).send(Mockito.any(MimeMessagePreparator.class));
    }
}