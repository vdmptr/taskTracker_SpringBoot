package vdm.controllers.helpsOfControllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailInstall {

    public static final String FROM;
    public static final Authenticator authenticator;
    public static final Properties smtpServerProperties = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader
                                .getResourceAsStream("email.properties");
        Properties emailProperties = new Properties();
        try {
            emailProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FROM = emailProperties.getProperty("mail.from", "web@gmail.com");

        smtpServerProperties.put("mail.smtp.host",
                                emailProperties.getProperty("mail.smtp.host",
                                                            "localhost"));
        smtpServerProperties.put("mail.smtp.port",
                                emailProperties.getProperty("mail.smtp.port",
                                                            "25"));
        smtpServerProperties.put("mail.smtp.auth",
                                emailProperties.getProperty("mail.smtp.auth",
                                                            "false"));
        smtpServerProperties.put("mail.smtp.starttls.enable",
                                emailProperties
                                    .getProperty("mail.smtp.starttls.enable",
                                                            "false"));
        authenticator = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties
                                                      .getProperty("authenticationName"),
                                                  emailProperties
                                                      .getProperty("authenticationPassword"));
            }
        };
    }
}
