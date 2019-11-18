package university.controller.command.admin;

import university.controller.command.Command;
import university.domain.Speciality;
import university.model.service.SpecialityService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowEnrollCommand implements Command {
    private final SpecialityService specialityService;

    public ShowEnrollCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Speciality> specialityEntities = specialityService.findAll();
        request.setAttribute("speciality", specialityEntities);
        return PagesConstant.ENROLL_PAGE;
    }
}
