package university.controller.filter;

import university.model.service.PasswordInCode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/user")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }
//TODO пофиксить копипаст
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        getPassword(req, "password", "passwordEncode");

        getPassword(req, "confirmPassword", "confirmPassword");
        filterChain.doFilter(req, servletResponse);
    }

    private void getPassword(HttpServletRequest req, String password2, String passwordEncode) {
        String password = req.getParameter(password2);
        String newPass = null;
        if (password != null) {
            newPass = PasswordInCode.passwordEncoded(password);
        }
        req.getSession().setAttribute(passwordEncode, newPass);
    }

    @Override
    public void destroy() {

    }


}
