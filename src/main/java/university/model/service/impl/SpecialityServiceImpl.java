package university.model.service.impl;

import university.domain.Course;
import university.domain.Speciality;
import university.model.dao.contract.ResultForSpecialityDao;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.exception.EntityNotFoundException;
import university.model.service.contract.SpecialityService;
import university.model.service.mapper.CourseMapperDomain;
import university.model.service.mapper.SpecialityDomainMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityDao specialityDao;
    private final SpecialityDomainMapper specialityMapper;
    private final CourseMapperDomain courseMapperDomain;
    private final ResultForSpecialityDao resultForSpecialityDao;


    public SpecialityServiceImpl(SpecialityDao specialityDao, SpecialityDomainMapper specialityMapper, CourseMapperDomain courseMapper, ResultForSpecialityDao resultForSpecialityDao) {
        this.specialityDao = specialityDao;
        this.specialityMapper = specialityMapper;
        this.courseMapperDomain = courseMapper;
        this.resultForSpecialityDao = resultForSpecialityDao;
    }


    @Override
    public void register(SpecialityEntity specialityEntity) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public Speciality findById(Integer id) {
        return specialityMapper.mapSpecialityEntityToSpeciality(specialityDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality with id: " + id + " dont found")));
    }

    @Override
    public void update(Speciality entity) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public List<Speciality> findAll() {
        List<SpecialityEntity> specialityEntities = specialityDao.findAll();
        return specialityEntities.isEmpty() ? Collections.emptyList() : specialityEntities
                .stream().map(specialityMapper::mapSpecialityEntityToSpeciality)
                .collect(Collectors.toList());

    }


    @Override
    public List<Course> getRequiredCoursesListBySpecId(Integer specialityId) {
        List<CourseEntity> coursesListBySpecId = specialityDao.getRequiredCoursesListBySpecId(specialityId);
        return coursesListBySpecId.isEmpty() ? Collections.emptyList() : coursesListBySpecId.stream()
                .map(courseMapperDomain::mapCourseEntityToCourse)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countElementOfTableBySpecId(Integer idSpeciality) {
        return resultForSpecialityDao.countElementOfTableBySpecId(idSpeciality);
    }


    @Override
    public void addCourse(Integer specialityId, Integer courseId) {
        throw new UnsupportedOperationException("In developing");
    }
}
