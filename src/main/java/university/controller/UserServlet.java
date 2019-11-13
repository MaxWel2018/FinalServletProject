package university.controller;


import university.context.ApplicationContextInjector;
import university.controller.command.Command;
import university.util.PagesConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class UserServlet extends HttpServlet {

    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public UserServlet() {
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getUserCommands();
        this.defaultCommand = request -> PagesConstant.HOME_PAGE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        final String page = commandNameToCommand.getOrDefault(commandName, defaultCommand).execute(req);
        req.getRequestDispatcher(page).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        final String page = commandNameToCommand.getOrDefault(commandName, defaultCommand).execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
