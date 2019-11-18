package university.model.service.impl;

import university.domain.ExamResult;
import university.model.dao.ExamResultDao;
import university.model.dao.ResultForSpecialityDao;
import university.model.dao.entity.ExamResultEntity;
import university.model.dao.exception.EntityNotFoundException;
import university.model.mapper.ExamResultMapper;
import university.model.mapper.SpecialityReqMapper;
import university.model.service.ResultService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ResultServiceImpl implements ResultService {

    private final ResultForSpecialityDao resultForSpecialityDao;

    private final ExamResultDao examResultDao;

    private final SpecialityReqMapper specialityRequestEntities;

    private final ExamResultMapper examResultMapper;

    public ResultServiceImpl(ResultForSpecialityDao resultForSpecialityDao, ExamResultDao examResultDao, SpecialityReqMapper specialityRequestEntities, ExamResultMapper examResultMapper) {
        this.resultForSpecialityDao = resultForSpecialityDao;
        this.examResultDao = examResultDao;
        this.specialityRequestEntities = specialityRequestEntities;
        this.examResultMapper = examResultMapper;
    }


    @Override
    public void save(ExamResult examResult) {
        examResultDao.save(examResultMapper.mapDomainToEntity(examResult));
    }

    @Override
    public void updateExamResult(ExamResult examResult) {
        examResultDao.update(examResultMapper.mapDomainToEntity(examResult));
    }

    @Override
    public List<ExamResult> findAllByCourseIdAndData(Integer id, LocalDate date) {
        List<ExamResultEntity> allByCourseIdAndData = examResultDao.findAllByCourseIdAndData(id, date);
        return allByCourseIdAndData.stream()
                .map(examResultMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResult> findAll() {
       return examResultDao.findAll().stream()
               .map(examResultMapper::mapEntityToDomain)
               .collect(Collectors.toList());
    }



    @Override
    public ExamResult findExamResultByUserIdAndCourseId(Integer userId, Integer course) {
        return examResultMapper.mapEntityToDomain(examResultDao.findByUserIdAndCourseId(userId, course)
                .orElseThrow(() -> new EntityNotFoundException("Exam result dont found")));
    }

    @Override
    public boolean updateGrade(Integer id, Integer grade) {
        if (id > 0 && grade > 0 && grade<=100) {
            examResultDao.updateGrade(id, grade);
            return true;
        }
        return false;
    }

}
