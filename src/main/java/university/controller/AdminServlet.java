package university.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/profile")
public class AdminServlet extends AbstractServlet {
    public AdminServlet() {
        super("admin-profile", "show-profile");
    }

}
