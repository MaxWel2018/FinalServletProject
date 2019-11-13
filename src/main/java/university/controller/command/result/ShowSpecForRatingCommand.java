package university.controller.command.result;

import university.controller.command.Command;
import university.domain.Speciality;
import university.domain.SpecialityRequest;
import university.model.service.ResultService;
import university.model.service.SpecialityService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSpecForRatingCommand implements Command {

    private final ResultService resultService;

    private final SpecialityService specialityService;

    public ShowSpecForRatingCommand( ResultService resultService, SpecialityService specialityService) {
        this.resultService = resultService;
        this.specialityService = specialityService;
    }
    @Override
    public String execute(HttpServletRequest req) {
        showInfo(req);
        List<Speciality> specialityEntities = specialityService.findAll();
        req.setAttribute("speciality", specialityEntities);
        return PagesConstant.RESULT_PAGE;
    }

    private void showInfo(HttpServletRequest req) {
        String selectedSpeciality = req.getParameter("selectedSpeciality");
        Integer selectedSpecialityID =selectedSpeciality==null?1:Integer.parseInt(selectedSpeciality);
        req.setAttribute("select", selectedSpecialityID);
        Integer recordsPerPage = 10;
        Integer countRow = specialityService.countElementOfTableBySpecId(selectedSpecialityID);
        String pageString = req.getParameter("page");
        Integer countElement = (countRow % recordsPerPage == 0) ? countRow / recordsPerPage : countRow / recordsPerPage + 1;
        Integer page = pageString == null ? 1 : Integer.parseInt(pageString);
        List<SpecialityRequest> specialityRequests = resultService.generateRating(page, recordsPerPage, selectedSpecialityID);
        req.setAttribute("countElement", countElement);
        req.setAttribute("rating", specialityRequests);
        req.setAttribute("currentPage", page);
    }
}
