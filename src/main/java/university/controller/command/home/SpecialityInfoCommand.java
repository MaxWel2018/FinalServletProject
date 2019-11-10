package university.controller.command.home;

import university.controller.command.Command;
import university.domain.Speciality;
import university.model.service.SpecialityService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SpecialityInfoCommand implements Command {
    private final SpecialityService specialityService;

    public SpecialityInfoCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }
    @Override
    public String execute(HttpServletRequest req) {

        List<Speciality> specialityEntities = specialityService.findAll();
        req.setAttribute("speciality", specialityEntities);
        String selectedSpeciality = req.getParameter("selectedSpeciality");
        Integer selectedSpecialityID = selectedSpeciality == null ? 1 : Integer.parseInt(selectedSpeciality);
        Speciality speciality = specialityService.findById(selectedSpecialityID);
        req.setAttribute("specialityFounded", speciality);
        return PagesConstant.HOME_PAGE;
    }

}
