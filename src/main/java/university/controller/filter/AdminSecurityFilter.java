package university.controller.filter;

import university.domain.User;
import university.model.dao.entity.Role;
import university.util.PagesConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD})
public class AdminSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getRole().equals(Role.ADMIN)) {
            request.getRequestDispatcher(PagesConstant.NO_ADMIN_PAGE).forward(request, servletResponse);
        }else{
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}