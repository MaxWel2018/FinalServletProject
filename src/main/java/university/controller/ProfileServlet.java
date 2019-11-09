package university.controller;

import university.context.ApplicationContextInjector;
import university.domain.SpecialityRequest;
import university.model.service.contract.SpecialityService;
import university.model.service.contract.UserService;
import university.util.PagesConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private ApplicationContextInjector contextInjector = ApplicationContextInjector.getInstance();
    private UserService userService = contextInjector.getUserService();
    private SpecialityService specialityService = contextInjector.getSpecialityService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer countRow = specialityService.countElementOfTableBySpecId(2);
        Integer recordsPerPage = 10;
        Integer countPage = countRow / recordsPerPage;
        Integer countElement = (countRow % recordsPerPage == 0) ? countPage : countPage + 1;
        String pageString = req.getParameter("page");
        Integer page = pageString == null ? 1 : Integer.parseInt(pageString);
        List<SpecialityRequest> specialityRequests = userService.generateRating(page, recordsPerPage, 2);
        req.setAttribute("countElement", countElement);
        req.setAttribute("rating", specialityRequests);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher(PagesConstant.PROFILE_PAGE).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesConstant.PROFILE_PAGE).forward(req, resp);
    }
}
