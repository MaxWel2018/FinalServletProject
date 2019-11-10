package university.controller.command.result;

import university.controller.command.Command;
import university.domain.Speciality;
import university.domain.SpecialityRequest;
import university.model.service.SpecialityService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowRatingCommand implements Command {
    private final UserService userService;

    private final SpecialityService specialityService;

    public ShowRatingCommand(UserService userService, SpecialityService specialityService) {
        this.userService = userService;
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String pageString = req.getParameter("page");
        Integer selectedSpecialityID = Integer.valueOf(req.getParameter("selectedSpeciality"));
        req.setAttribute("select", selectedSpecialityID);
        Integer recordsPerPage = 10;
        Integer countRow = specialityService.countElementOfTableBySpecId(selectedSpecialityID);
        Integer countElement = (countRow % recordsPerPage == 0) ? countRow / recordsPerPage : countRow / recordsPerPage + 1;
        Integer page = pageString == null ? 1 : Integer.parseInt(pageString);
        List<SpecialityRequest> specialityRequests = userService.generateRating(page, recordsPerPage, selectedSpecialityID);
        req.setAttribute("countElement", countElement);
        req.setAttribute("rating", specialityRequests);
        req.setAttribute("currentPage", page);
        return  new ShowSpecForRatingCommand(specialityService).execute(req);
    }
}
