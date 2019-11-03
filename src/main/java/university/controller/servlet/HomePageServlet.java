package university.controller.servlet;

import university.controller.service.contract.SpecialityService;
import university.controller.service.impl.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomePageServlet extends HttpServlet {
    SpecialityService specialityService = new SpecialityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("spec",specialityService.findAll());
        req.getRequestDispatcher("/views/HomePage.jsp").forward(req, resp);

    }
}
