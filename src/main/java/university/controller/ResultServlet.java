package university.controller;

import university.context.ApplicationContextInjector;
import university.controller.command.Command;
import university.domain.SpecialityRequest;
import university.model.service.SpecialityService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static university.context.ApplicationContextInjector.*;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public ResultServlet() {
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getRatingCommandNameToCommand();
        this.defaultCommand = request -> PagesConstant.ERROR_404_PAGE;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        final String page = commandNameToCommand.getOrDefault(commandName, defaultCommand).execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
