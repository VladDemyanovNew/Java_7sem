package dtos;

import java.util.Date;
import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;

public class MessageDto {

    private String subject = null;
    private String from = null;
    private Date sentDate = null;
    private int messageNumber;
    private String content = null;

    public MessageDto() {}

    public static MessageDto convertToDto(Message message) throws MessagingException, IOException {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(message.getSubject());
        messageDto.setFrom(InternetAddress.toString(message.getFrom()));
        messageDto.setSentDate(message.getSentDate());
        messageDto.setMessageNumber(message.getMessageNumber());
        messageDto.setContent(parseContent(message.getContent(), message.getContentType()));

        return messageDto;
    }

    public MessageDto(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getSentDate() {
        return this.sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getMessageNumber() {
        return this.messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private static String parseContent(Object content, String contentType)
            throws MessagingException, IOException {
        String parsedContent = "";

        if (content == null) {
            return parsedContent;
        }

        if (contentType.contains("multipart")) {
            Multipart multiPart = (Multipart)content;

            int numberOfParts = multiPart.getCount();
            for (int partCount = 0; partCount < numberOfParts; partCount++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                parsedContent += part.getContent().toString();
            }
        }
        else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
            parsedContent = content.toString();
        }

        return parsedContent;
    }
}