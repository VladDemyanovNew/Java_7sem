package beans;

import java.sql.*;

public class Comment {

    private int Id;
    private int ReferenceId;
    private String SessionId;
    private Date Stamp;
    private String Text = null;

    public Comment() {
    }

    public Comment(int id, int referenceId, String sessionId, Date stamp, String text) {
        Id = id;
        ReferenceId = referenceId;
        SessionId = sessionId;
        Stamp = stamp;
        Text = text;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getReferenceId() {
        return ReferenceId;
    }

    public void setReferenceId(int referenceId) {
        ReferenceId = referenceId;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public Date getStamp() {
        return Stamp;
    }

    public void setStamp(Date stamp) {
        Stamp = stamp;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}