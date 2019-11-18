package university.model.service.impl;

import university.domain.SpecialityRequest;
import university.model.dao.ResultForSpecialityDao;
import university.model.dao.entity.SpecialityRequestEntity;
import university.model.mapper.SpecialityReqMapper;
import university.model.service.Page;
import university.model.service.ResultForSpecService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultForSpecServiceImpl implements ResultForSpecService {
    private final ResultForSpecialityDao resultForSpecialityDao;

    private final SpecialityReqMapper specialityReqMapper;


    public ResultForSpecServiceImpl(ResultForSpecialityDao resultForSpecialityDao, SpecialityReqMapper specialityRequestEntities) {
        this.resultForSpecialityDao = resultForSpecialityDao;
        this.specialityReqMapper = specialityRequestEntities;
    }

    @Override
    public List<SpecialityRequest> generateRating(Page page, Integer specialityId) {
        Integer defaultStart = 0;
        Integer defaultRecordsPerPage = 10;
        Integer currentPage = page.getCurrentPage();
        Integer recordsPerPage = page.getRecordsPerPage();

        if (currentPage <= 0 || recordsPerPage <= 0) {
            return returnRating(defaultRecordsPerPage, defaultStart, specialityId);

        } else {
            Integer start = currentPage * recordsPerPage - recordsPerPage;
            return returnRating(recordsPerPage, start, specialityId);
        }
    }

    @Override
    public void setResultForSpeciality(SpecialityRequest specialityRequest) {
        if (findByUserId(specialityRequest.getUserId()).getUserId()== null) {
            resultForSpecialityDao.save(specialityReqMapper.mapDomainToEntity(specialityRequest));
        }else{
            resultForSpecialityDao.setResultForSpeciality(specialityReqMapper.mapDomainToEntity(specialityRequest));
        }
    }

    @Override
    public SpecialityRequest findByUserId(Integer userId) {
        return specialityReqMapper.mapEntityToDomain(resultForSpecialityDao.findByUserId(userId));
    }

    @Override
    public List<SpecialityRequest> findToEnrollmentBySpecId(Integer specId, Integer governmentOrder) {
        return resultForSpecialityDao.findToEnrollmentBySpecId(specId, governmentOrder).stream()
                .map(specialityReqMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateConfirmedByUserId(Integer id, Boolean confirmed) {
        resultForSpecialityDao.updateConfirmedByUserId(id, confirmed);
    }

    private List<SpecialityRequest> returnRating(Integer recordsPerPage, Integer start, Integer specialityId) {
        List<SpecialityRequestEntity> requestEntities = resultForSpecialityDao.generateRating(start, recordsPerPage, specialityId);
        return requestEntities.isEmpty() ? Collections.emptyList() : requestEntities.stream()
                .map(specialityReqMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
