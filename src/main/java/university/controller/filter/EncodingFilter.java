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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String password = req.getParameter("password");
        String newPass = null;
        String confirmPassword = req.getParameter("confirmPassword");
        String newConfirmPass = null;
        if (password != null) {
            newPass = PasswordInCode.passwordEncoded(password);
        }
        if (confirmPassword != null) {
            newConfirmPass = PasswordInCode.passwordEncoded(confirmPassword);
        }
        req.getSession().setAttribute("passwordEncode", newPass);
        req.getSession().setAttribute("confirmPassword", newConfirmPass);
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }


}