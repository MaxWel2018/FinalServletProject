package university.controller.command.result;

import university.controller.command.Command;
import university.domain.Speciality;
import university.domain.SpecialityRequest;
import university.model.service.Page;
import university.model.service.ResultService;
import university.model.service.SpecialityService;
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
        String pageString = req.getParameter("page");
        Integer selectedSpecialityID = convertToInteger(selectedSpeciality);
        Integer page = convertToInteger(pageString);
        Integer recordsPerPage = 10;
        Integer countRow = specialityService.countElementOfTableBySpecId(selectedSpecialityID);
        Integer countElement = getCountElement(recordsPerPage, countRow);
        List<SpecialityRequest> specialityRequests = createSpecialityRequests(selectedSpecialityID, page, recordsPerPage);
        setAttribute(req, countElement, page, specialityRequests,selectedSpecialityID);
    }

    private List<SpecialityRequest> createSpecialityRequests(Integer selectedSpecialityID, Integer page, Integer recordsPerPage) {
        return resultService.generateRating(mapToPage(recordsPerPage, page), selectedSpecialityID);
    }

    private int getCountElement(Integer recordsPerPage, Integer countRow) {
        return (countRow % recordsPerPage == 0) ? countRow / recordsPerPage : countRow / recordsPerPage + 1;
    }

    private Integer convertToInteger(String value) {
        return value == null ? 1 : Integer.parseInt(value);
    }
    private void setAttribute(HttpServletRequest req, Integer countElement, Integer page, List<SpecialityRequest> specialityRequests,Integer selectedSpecialityID) {
        req.setAttribute("countElement", countElement);
        req.setAttribute("rating", specialityRequests);
        req.setAttribute("currentPage", page);
        req.setAttribute("select", selectedSpecialityID);

    }

    private Page mapToPage(Integer recordsPerPage, Integer page) {
        return new Page(page,recordsPerPage);
    }
}
