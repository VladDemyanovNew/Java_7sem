import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dtos.*;

public class MailServlet extends HttpServlet implements Servlet {

    private String systemEmailAddress = null;
    private String systemEmailPassword = null;

    public void init() throws ServletException {
        this.systemEmailAddress = getServletContext().getInitParameter("SystemEmailAddress");
        this.systemEmailPassword = getServletContext().getInitParameter("SystemEmailPassword");

        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String messageNumber = request.getParameter("messageNumber");
        if (messageNumber != null) {
            MessageDto message = this.getMessage(Integer.parseInt(messageNumber));
            request.setAttribute("message", message);
            request.getRequestDispatcher("Mail.jsp")
                    .forward(request, response);
            return;
        }

        LinkedList<MessageDto> messages = this.getAllMessages();
        request.setAttribute("messages", messages);

        request.getRequestDispatcher("Mails.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet:MailServlet:doPost");

        String mail = request.getParameter("mail");
        String message = request.getParameter("message");
        String subject = "Java Lab14";

        PrintWriter out = response.getWriter();

        boolean hasMailBeenSent = this.sendMail(mail, message, subject);
        if (hasMailBeenSent) {
            out.println("Mail has been sent successfully :)");
            return;
        }

        out.println("Mail has not been sent :(");
    }

    private LinkedList<MessageDto> getAllMessages() throws IOException {
        try {
            Properties properties = this.preparePropertiesForImap();
            Session mailSession = Session.getDefaultInstance(properties);

            Store mailStore = mailSession.getStore("imap");
            mailStore.connect(this.systemEmailAddress, this.systemEmailPassword);

            Folder folder = mailStore.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            LinkedList<MessageDto> messageDtos = new LinkedList<MessageDto>();
            for (Message message: folder.getMessages()) {
                MessageDto messageDto = MessageDto.convertToDto(message);
                messageDtos.add(messageDto);
            }

            folder.close(true);
            mailStore.close();

            return messageDtos;
        }
        catch (MessagingException error) {
            error.printStackTrace();
            return new LinkedList<MessageDto>();
        }
    }

    private MessageDto getMessage(int messageNumber) throws IOException {
        try {
            Properties properties = this.preparePropertiesForImap();
            Session mailSession = Session.getDefaultInstance(properties);

            Store mailStore = mailSession.getStore("imap");
            mailStore.connect(this.systemEmailAddress, this.systemEmailPassword);

            Folder folder = mailStore.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message message = folder.getMessage(messageNumber);
            MessageDto messageDto = MessageDto.convertToDto(message);

            folder.close(true);
            mailStore.close();

            return messageDto;
        }
        catch (MessagingException error) {
            error.printStackTrace();
            return null;
        }
    }

    private boolean sendMail(String mail, String message, String subject) {
        try
        {
            Properties properties = this.preparePropertiesForSmtp();
            Session mailSession = this.prepareSession(this.systemEmailAddress, this.systemEmailPassword, properties);
            MimeMessage mimeMessage = new MimeMessage(mailSession);

            mimeMessage.setFrom(new InternetAddress(this.systemEmailAddress, "Vladislav Demyanov"));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail, false));

            Transport.send(mimeMessage);
            return true;
        }
        catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    private Session prepareSession(String address, String password, Properties properties) {
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(address, password);
            }
        };

        return Session.getInstance(properties, authenticator);
    }

    private Properties preparePropertiesForSmtp() {
        String smtpHost = getServletContext().getInitParameter("SmtpHost");
        String smtpPort = getServletContext().getInitParameter("SmtpPort");
        String smtpAuth = getServletContext().getInitParameter("SmtpAuth");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }

    private Properties preparePropertiesForImap() {
        String imapHost = getServletContext().getInitParameter("ImapHost");
        String imapPort = getServletContext().getInitParameter("ImapPort");

        Properties properties = new Properties();
        properties.put("mail.imap.host", imapHost);
        properties.put("mail.imap.port", imapPort);
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", imapPort);

        return properties;
    }
}