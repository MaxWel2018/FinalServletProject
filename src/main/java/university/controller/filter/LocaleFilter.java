package university.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocaleFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getParameter("language") != null) {
            req.getSession().setAttribute("language", req.getParameter("language"));
        }

        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
