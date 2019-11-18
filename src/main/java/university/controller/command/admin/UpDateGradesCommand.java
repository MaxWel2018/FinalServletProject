package university.controller.command.admin;

import university.controller.command.Command;
import university.model.service.ResultService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UpDateGradesCommand implements Command {
    private final ResultService resultService;

    public UpDateGradesCommand(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Boolean> isUpdateGrade = new ArrayList<>();

        String[] grades = request.getParameterValues("grade");
        String[] idRecord = request.getParameterValues("idRecord");
        updateGrade(isUpdateGrade, grades, idRecord);
        return PagesConstant.ADMIN_PROFILE;
    }

    private void updateGrade(List<Boolean> isUpdateGrade, String[] grades, String[] idRecord) {
        for (int i = 0; i < idRecord.length; i++) {
            isUpdateGrade.add(resultService.updateGrade(Integer.parseInt(grades[i]), Integer.parseInt(idRecord[i])));
        }
    }
}
