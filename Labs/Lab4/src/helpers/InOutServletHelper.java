package helpers;

import java.io.*;
import javax.servlet.*;

public class InOutServletHelper {
    public static void perform(ServletOutputStream servletOutputStream, InputStream inputStream)
            throws IOException {
        int buf;
        while ((buf = inputStream.read()) > 0) {
            servletOutputStream.write(buf);
        }

        servletOutputStream.flush();
    }
}