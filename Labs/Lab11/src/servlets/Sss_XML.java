import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import util.*;

public class Sss_XML extends HttpServlet implements Servlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Sss_XML:doPost start");
            response.setContentType("text/xml");

            Integer extremum = new Integer(request.getHeader("XRand-N"));

            PrintWriter out = response.getWriter();
            StringBuilder responseBody = new StringBuilder();

            responseBody.append("<?xml version=\"1.0\"  encoding = \"utf-8\" ?><rand>");
            Integer sequenceLength = XXRand.Get(5, 10);
            for (int i = 0; i < sequenceLength; i++) {
                Integer randomInt = XXRand.Get(-extremum, extremum);
                responseBody.append("<num>" + randomInt.toString() + "</num>");
            }
            responseBody.append("</rand>");

            out.println(responseBody.toString());

            Thread.currentThread().sleep(5000);
            System.out.println("Sss_XML:doPost end");
        } catch (Exception error) {
            response.getWriter().println(error.getMessage());
        }
    }
}