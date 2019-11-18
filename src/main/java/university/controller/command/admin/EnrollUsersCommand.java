package university.controller.command.admin;

import university.controller.command.Command;
import university.domain.SpecialityRequest;
import university.model.service.ResultForSpecService;
import university.model.service.SpecialityService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EnrollUsersCommand implements Command {

    private final ResultForSpecService resultForSpecService;

    private final SpecialityService specialityService;

    public EnrollUsersCommand(ResultForSpecService resultForSpecService, SpecialityService specialityService) {
        this.resultForSpecService = resultForSpecService;
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer specId = parseReqToIdSpec(request);
        Integer governmentOrderFromSpec = specialityService.findById(specId).getStudentsNumber();
        List<SpecialityRequest> toEnrollmentBySpecId = resultForSpecService.findToEnrollmentBySpecId(specId, governmentOrderFromSpec);
        for (SpecialityRequest specialityRequest : toEnrollmentBySpecId) {
            resultForSpecService.updateConfirmedByUserId(specialityRequest.getUserId(), true);
        }
        return PagesConstant.ADMIN_PROFILE;
    }

    private Integer parseReqToIdSpec(HttpServletRequest request) {
        return Integer.valueOf(request.getParameter("selectedSpeciality"));
    }
}
