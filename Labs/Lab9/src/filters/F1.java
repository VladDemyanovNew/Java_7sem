package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class F1 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("F1:init");
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        boolean isBlock = Boolean.valueOf(request.getParameter("isBlockF1"));
        if (isBlock) {
            PrintWriter out = response.getWriter();
            out.println("Request blocked by filter F1");
            return;
        }

        System.out.println("F1:doFilter");
        filterChain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("F1:destroy");
    }
}
