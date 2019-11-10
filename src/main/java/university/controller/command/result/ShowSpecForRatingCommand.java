package university.controller.command.result;

import university.controller.command.Command;
import university.domain.Speciality;
import university.model.service.SpecialityService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSpecForRatingCommand implements Command {

    private final SpecialityService specialityService;

    public ShowSpecForRatingCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<Speciality> specialityEntities = specialityService.findAll();
        req.setAttribute("speciality", specialityEntities);
        return PagesConstant.RESULT_PAGE;
    }
}
