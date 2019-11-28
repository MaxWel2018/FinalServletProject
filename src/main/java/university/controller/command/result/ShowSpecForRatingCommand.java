package university.controller.command.result;

import university.controller.command.Command;
import university.domain.Speciality;
import university.domain.UserResult;
import university.model.service.Page;
import university.model.service.ResultForSpecService;
import university.model.service.SpecialityService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSpecForRatingCommand implements Command {

    public static final int RECORDS_PER_PAGE = 10;
    private final SpecialityService specialityService;

    private final ResultForSpecService resultForSpecialityDao;

    public ShowSpecForRatingCommand(SpecialityService specialityService, ResultForSpecService resultForSpecialityDao) {
        this.specialityService = specialityService;
        this.resultForSpecialityDao = resultForSpecialityDao;
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
        Integer countRow = specialityService.countElementOfTableBySpecId(selectedSpecialityID);
        Integer countElement = getCountElement(RECORDS_PER_PAGE, countRow);
        List<UserResult> userResults = createSpecialityRequests(selectedSpecialityID, page, RECORDS_PER_PAGE);
        setAttribute(req, countElement, page, userResults, selectedSpecialityID);
    }

    private List<UserResult> createSpecialityRequests(Integer selectedSpecialityID, Integer page, Integer recordsPerPage) {
        return resultForSpecialityDao.generateRating(mapToPage(recordsPerPage, page), selectedSpecialityID);
    }

    private int getCountElement(Integer recordsPerPage, Integer countRow) {
        return (countRow % recordsPerPage == 0) ? countRow / recordsPerPage : countRow / recordsPerPage + 1;
    }

    private Integer convertToInteger(String value) {
        return value == null ? 1 : Integer.parseInt(value);
    }

    private void setAttribute(HttpServletRequest req, Integer countElement, Integer page, List<UserResult> userResults, Integer selectedSpecialityID) {
        req.setAttribute("countElement", countElement);
        req.setAttribute("rating", userResults);
        req.setAttribute("currentPage", page);
        req.setAttribute("select", selectedSpecialityID);

    }

    private Page mapToPage(Integer recordsPerPage, Integer page) {
        return new Page(page, recordsPerPage);
    }
}
