package university.controller;

import org.apache.log4j.Logger;
import university.context.ApplicationContextInjector;
import university.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AbstractServlet.class);
    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    AbstractServlet(String command, String defaultCommand) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        switch (command) {
            case "home": {
                this.commandNameToCommand = injector.getHomeCommandNameToCommand();
                break;
            }
            case "admin-profile": {
                this.commandNameToCommand = injector.getAdminCommand();
                break;
            }
            case "result": {
                this.commandNameToCommand = injector.getRatingCommandNameToCommand();
                break;
            }
            case "user-profile": {
                this.commandNameToCommand = injector.getUserProfileCommand();
                break;
            }
            case "user": {
                this.commandNameToCommand = injector.getUserCommands();
                break;
            }
            default: {
                LOGGER.error("Not valid Command");
                throw new IllegalArgumentException("Not valid Command");
            }

        }
        this.defaultCommand = commandNameToCommand.get(defaultCommand);

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
