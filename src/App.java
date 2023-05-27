import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static String generateOTP() {
        int randomNo = (int) (Math.random() * 9000) + 1000;
        String otp = String.valueOf(randomNo);
        return otp;
    }

    public static void main(String[] args) {
        String otpStr = generateOTP();
        System.out.println("OTP: " + otpStr);

        // Gmail account credentials
        String username = "loaiashraf285@gmail.com";
        String password = "Ahmed148635";

        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender address
            message.setFrom(new InternetAddress(username));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("loaiashraf285@gmail.com"));

            // Set the email subject
            message.setSubject("OTP");

            // Set the email content
            message.setText("Your OTP: " + otpStr);

            // Send the email
            Transport.send(message);

            System.out.println("OTP sent successfully to the recipient.");
        } catch (MessagingException e) {
            System.out.println("Failed to send OTP. Error message: " + e.getMessage());
        }
    }
}
