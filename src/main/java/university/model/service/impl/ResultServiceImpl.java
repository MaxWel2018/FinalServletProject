package university.model.service.impl;

import university.domain.ExamResult;
import university.domain.SpecialityRequest;
import university.model.dao.ExamResultDao;
import university.model.dao.ResultForSpecialityDao;
import university.model.dao.entity.SpecialityRequestEntity;
import university.model.mapper.ExamResultMapper;
import university.model.mapper.SpecialityReqMapper;
import university.model.service.ResultService;

import java.util.Collections;
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
    public List<SpecialityRequest> generateRating(Integer currentPage, Integer recordsPerPage, Integer specialityId) {
        Integer defaultStart = 0;
        Integer defaultRecordsPerPage = 10;

        if (currentPage <= 0 || recordsPerPage <= 0) {
            return returnRating(defaultRecordsPerPage, defaultStart, specialityId);

        } else {
            Integer start = currentPage * recordsPerPage - recordsPerPage;
            return returnRating(recordsPerPage, start, specialityId);
        }


    }


    private List<SpecialityRequest> returnRating(Integer recordsPerPage, Integer start, Integer specialityId) {
        List<SpecialityRequestEntity> requestEntities = resultForSpecialityDao.generateRating(start, recordsPerPage, specialityId);
        return requestEntities.isEmpty() ? Collections.emptyList() : requestEntities.stream()
                .map(specialityRequestEntities::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
