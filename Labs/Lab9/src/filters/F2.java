package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class F2 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("F2:init");
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        boolean isBlock = Boolean.valueOf(request.getParameter("isBlockF2"));
        if (isBlock) {
            PrintWriter out = response.getWriter();
            out.println("Request blocked by filter F2");
            return;
        }

        System.out.println("F2:doFilter");
        filterChain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("F2:destroy");
    }
}
