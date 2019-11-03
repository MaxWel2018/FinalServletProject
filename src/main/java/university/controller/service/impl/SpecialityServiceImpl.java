package university.controller.service.impl;

import university.controller.service.contract.SpecialityService;
import university.model.dao.contract.CourseDao;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.entity.Course;
import university.model.dao.entity.Speciality;
import university.model.dao.exception.EntityNotFoundException;
import university.model.dao.factory.DaoFactoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SpecialityServiceImpl implements SpecialityService {
    private SpecialityDao specialityDao = DaoFactoryImpl.getInstance().getSpecialityDao();


    @Override
    public void register(Speciality speciality) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public Optional<Speciality> findById(Integer id) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public void update(Speciality entity) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public Speciality deleteById(Integer id) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public List<Speciality> findAll() {
        return Optional.ofNullable(specialityDao.findAll()).orElseThrow(() -> new EntityNotFoundException("Courses not found"));
    }

    @Override
    public void deleteAllByIds(Set<Integer> integers) {
        throw new UnsupportedOperationException("In developing");
    }

    @Override
    public List<Course> getRequiredCoursesList(Integer specialityId) {
        return null;
    }

    @Override
    public void addCourse(Integer specialityId, Integer courseId) {

    }
}
