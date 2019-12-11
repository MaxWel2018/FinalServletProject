package university.controller.command.admin;

import university.controller.command.Command;
import university.model.service.ResultService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;

public class UpDateGradesCommand implements Command {
    private final ResultService resultService;

    public UpDateGradesCommand(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String[] grades = request.getParameterValues("grade");
        String[] ids = request.getParameterValues("idRecord");
        updateGrade(grades, ids);
        return PagesConstant.ADMIN_PROFILE;
    }

    private void updateGrade( String[] grades, String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            resultService.updataGrade(Integer.parseInt(grades[i]), Integer.parseInt(ids[i]));
        }
    }

}
