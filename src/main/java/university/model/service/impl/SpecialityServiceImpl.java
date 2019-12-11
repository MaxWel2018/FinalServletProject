package university.model.service.impl;

import university.domain.Speciality;
import university.model.dao.ResultForSpecialityDao;
import university.model.dao.SpecialityDao;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.exception.EntityNotFoundException;
import university.model.mapper.CourseMapper;
import university.model.mapper.SpecialityMapper;
import university.model.service.SpecialityService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityDao specialityDao;
    private final SpecialityMapper specialityMapper;
    private final ResultForSpecialityDao resultForSpecialityDao;

    public SpecialityServiceImpl(SpecialityDao specialityDao, SpecialityMapper specialityMapper, ResultForSpecialityDao resultForSpecialityDao) {
        this.specialityDao = specialityDao;
        this.specialityMapper = specialityMapper;
        this.resultForSpecialityDao = resultForSpecialityDao;
    }

    @Override
    public Speciality findById(Integer id) {

        return specialityDao.findById(id)
                .map(specialityMapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("Speciality with id: " + id + " dont found"));

    }

    @Override
    public List<Speciality> findAll() {
        return  specialityDao.findAll()
                .stream()
                .map(specialityMapper::mapEntityToDomain)
                .collect(Collectors.toList());

    }

    @Override
    public Integer countElementOfTableBySpecId(Integer idSpeciality) {
        return resultForSpecialityDao.countElementOfTableBySpecId(idSpeciality);
    }

}
