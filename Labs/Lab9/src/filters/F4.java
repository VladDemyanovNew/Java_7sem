package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class F4 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("F4:init");
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        boolean isBlock = Boolean.valueOf(request.getParameter("isBlockF4"));
        if (isBlock) {
            PrintWriter out = response.getWriter();
            out.println("Request blocked by filter F4");
            return;
        }

        System.out.println("F4:doFilter");
        filterChain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("F4:destroy");
    }
}
