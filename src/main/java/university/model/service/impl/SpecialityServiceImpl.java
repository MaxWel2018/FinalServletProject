package university.model.service.impl;

import university.model.service.contract.SpecialityService;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityDao specialityDao;

    public SpecialityServiceImpl(SpecialityDao specialityDao) {
        this.specialityDao = specialityDao;
    }


    @Override
    public void register(SpecialityEntity specialityEntity) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public Optional<SpecialityEntity> findById(Integer id) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public void update(SpecialityEntity entity) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public List<SpecialityEntity> findAll() {
        return Optional.ofNullable(specialityDao.findAll()).orElseThrow(() -> new EntityNotFoundException("Courses not found"));
    }


    @Override
    public List<CourseEntity> getRequiredCoursesList(Integer specialityId) {
        return null;
    }

    @Override
    public void addCourse(Integer specialityId, Integer courseId) {

    }
}
