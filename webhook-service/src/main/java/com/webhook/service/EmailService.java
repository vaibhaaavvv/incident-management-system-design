package com.webhook.service;

import com.webhook.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendIncidentEmail(Incident incident) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(incident.getEmail());
            helper.setSubject("🚨 Incident Alert - " + incident.getId());
            helper.setText(createEmailContent(incident), true);
            
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    private String createEmailContent(Incident incident) {
        return String.format("""
            <html>
            <body style="font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); margin: 0; padding: 20px;">
                <div style="max-width: 600px; margin: 0 auto; background: white; border-radius: 15px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
                    <div style="background: linear-gradient(135deg, #ff6b6b 0%%, #ee5a24 100%%); color: white; padding: 30px; text-align: center;">
                        <h1 style="margin: 0; font-size: 32px; text-shadow: 2px 2px 4px rgba(0,0,0,0.3);">🚨 INCIDENT ALERT</h1>
                        <p style="margin: 10px 0 0 0; font-size: 18px; opacity: 0.9;">ID: %s</p>
                    </div>
                    <div style="padding: 40px;">
                        <div style="background: linear-gradient(135deg, #f093fb 0%%, #f5576c 100%%); color: white; padding: 20px; border-radius: 10px; margin-bottom: 25px; text-align: center;">
                            <h2 style="margin: 0 0 10px 0; font-size: 20px;">📋 Description</h2>
                            <p style="margin: 0; font-size: 16px; line-height: 1.5;">%s</p>
                        </div>
                        <div style="display: flex; gap: 15px; margin-bottom: 25px;">
                            <div style="flex: 1; background: linear-gradient(135deg, #4facfe 0%%, #00f2fe 100%%); color: white; padding: 20px; border-radius: 10px; text-align: center;">
                                <h3 style="margin: 0 0 10px 0; font-size: 16px;">👤 Customer</h3>
                                <p style="margin: 0; font-size: 14px; font-weight: bold;">%s</p>
                            </div>
                            <div style="flex: 1; background: linear-gradient(135deg, #43e97b 0%%, #38f9d7 100%%); color: white; padding: 20px; border-radius: 10px; text-align: center;">
                                <h3 style="margin: 0 0 10px 0; font-size: 16px;">📁 Project</h3>
                                <p style="margin: 0; font-size: 14px; font-weight: bold;">%s</p>
                            </div>
                        </div>
                        <div style="background: linear-gradient(135deg, #fa709a 0%%, #fee140 100%%); color: white; padding: 20px; border-radius: 10px; text-align: center;">
                            <h3 style="margin: 0 0 10px 0; font-size: 16px;">⏰ Created</h3>
                            <p style="margin: 0; font-size: 14px; font-weight: bold;">%s</p>
                        </div>
                    </div>
                </div>
            </body>
            </html>
            """, 
            incident.getId(),
            incident.getIncidentDescription(),
            incident.getCustomerId() != null ? incident.getCustomerId() : "N/A",
            incident.getProjectId() != null ? incident.getProjectId() : "N/A",
            incident.getCreationTime()
        );
    }
}