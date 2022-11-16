import java.io.IOException; // исключения ввода/вывода
import javax.servlet.*; // интерфейсы и классы общего типа
import javax.servlet.http.*; // расширение javax.servlet для http
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class Aaa extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Aaa.java:service:" + request.getMethod());

        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String uri = "http://localhost:8080" + request.getContextPath() + "/Bbb";

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(uri);

        postMethod.addParameter("Param1", "ParamValue1");
        postMethod.addParameter("Param2", "ParamValue2");
        postMethod.addParameter("Param3", "ParamValue3");

        postMethod.addRequestHeader("X-Header1", "HeaderValue1");
        postMethod.addRequestHeader("X-Header2", "HeaderValue2");
        postMethod.addRequestHeader("X-Header3", "HeaderValue3");

        httpClient.executeMethod(postMethod);
        String responseBody = postMethod.getResponseBodyAsString();

        StringBuilder resultResponseBody = new StringBuilder();
        resultResponseBody.append(responseBody);
        resultResponseBody.append("<br>");

        Header[] headers = postMethod.getResponseHeaders();
        for (Header header : headers) {
            resultResponseBody.append(header.getName());
            resultResponseBody.append(":");
            resultResponseBody.append(header.getValue());
            resultResponseBody.append("<br>");
        }

        PrintWriter out = response.getWriter();
        out.println(resultResponseBody.toString());

        out.flush();
    }
}