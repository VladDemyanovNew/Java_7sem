import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import util.*;

public class Sss_JSON extends HttpServlet implements Servlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Sss_JSON:doPost start");
            response.setContentType("text/xml");

            Integer extremum = new Integer(request.getHeader("XRand-N"));

            PrintWriter out = response.getWriter();
            StringBuilder responseBody = new StringBuilder();

            responseBody.append("{\"numbers\":[");
            Integer sequenceLength = XXRand.Get(5, 10);
            for (int i = 1; i <= sequenceLength; i++) {
                Integer randomInt = XXRand.Get(-extremum, extremum);
                responseBody.append("{\"rand\":" + randomInt.toString() + "}" + ((i < sequenceLength) ? "," : " "));
            }
            responseBody.append("]}");

            out.println(responseBody.toString());

            Thread.currentThread().sleep(1000);
            System.out.println("Sss_JSON:doPost end");
        } catch (Exception error) {
            response.getWriter().println(error.getMessage());
        }
    }
}
