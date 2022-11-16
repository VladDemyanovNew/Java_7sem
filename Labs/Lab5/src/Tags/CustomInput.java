package stafftag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import java.io.IOException;

public class CustomInput extends TagSupport {

    private String value = "";
    private String name = "";
    private String label = "";
    private String type = "";
    private String styles = "";

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyles() {
        return this.styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            String labelTag = "<label for=\"" + this.name + "\">" + this.label + "&nbsp</label>";
            String inputTagStyles = "style=\"" + this.styles + "\"";

            String inputId = this.name.equals("") ? "" : "id=\"" + this.name + "\" ";
            String inputName = this.name.equals("") ? "" : "name=\"" + this.name + "\" ";
            String inputTag = "<input " + inputId + inputName + "type=\"" + this.type + "\" " +
                    inputTagStyles + " value=\"" + this.value + "\" />";

            out.print(labelTag + inputTag);
        } catch (IOException e) {
            System.out.println("stafftag.Surname: " + e);
        }

        return SKIP_BODY;
    }
}
