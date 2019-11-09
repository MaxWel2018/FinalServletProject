package university.controller;

import university.context.ApplicationContextInjector;
import university.domain.Speciality;
import university.model.service.contract.SpecialityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static university.util.PagesConstant.HOME_PAGE;

public class HomePageServlet extends HttpServlet {
    private final ApplicationContextInjector context = ApplicationContextInjector.getInstance();
    private final SpecialityService specialityService = context.getSpecialityService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setSpeciality(req);
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer selectedSpecialityID = Integer.valueOf(req.getParameter("selectedSpeciality"));
        Speciality speciality = specialityService.findById(selectedSpecialityID);
        req.setAttribute("specialityFounded", speciality);
        setSpeciality(req);
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }

    private void setSpeciality(HttpServletRequest req) {
        List<Speciality> specialityEntities = specialityService.findAll();
        req.setAttribute("speciality", specialityEntities);
    }
}
