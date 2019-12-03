package university.controller.command.admin;

import university.controller.command.Command;
import university.domain.ExamResult;
import university.domain.UserResult;
import university.model.service.ResultForSpecService;
import university.model.service.ResultService;
import university.model.service.UserService;
import university.util.PagesConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateRatingCommand implements Command {

    private final ResultService resultService;

    private final ResultForSpecService resultForSpecService;

    private final UserService userService;

    public UpdateRatingCommand(ResultService resultService, ResultForSpecService resultForSpecService, UserService userService) {
        this.resultService = resultService;
        this.resultForSpecService = resultForSpecService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        setFinalMark();
        return PagesConstant.ADMIN_PROFILE;
    }

    private void setFinalMark() {
        List<ExamResult> allExamResult = resultService.findAll();
        Map<Integer, List<ExamResult>> userIdToExamResult = allExamResult.stream().collect(Collectors.groupingBy(ExamResult::getUserId));
        for (Integer integer : userIdToExamResult.keySet()) {
            setExamResult(userIdToExamResult, integer);
        }
    }

    private void setExamResult(Map<Integer, List<ExamResult>> collect, Integer integer) {
        resultForSpecService.setResultForSpeciality(UserResult.newBuilder()
                .withSpecialityId(getSpecialityId(integer))
                .withFinalMark(calculateFinalMark(collect.get(integer)))
                .withUserId(getUserId(collect, integer))
                .build());
    }

    private Integer getUserId(Map<Integer, List<ExamResult>> collect, Integer integer) {
        return collect.get(integer).get(0).getUserId();
    }

    private Integer getSpecialityId(Integer integer) {
        return userService.findById(integer).getSpeciality().getId();
    }

    private Integer calculateFinalMark(List<ExamResult> examResults) {
        return  examResults.stream().map(ExamResult::getMark).reduce(0, Integer::sum);

    }

}
