package university.model.mapper;


import university.context.ApplicationContextInjector;
import university.domain.Course;
import university.domain.Speciality;
import university.model.dao.SpecialityDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.impl.SpecialityDaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityMapper implements Mapper<Speciality, SpecialityEntity> {
    private final CourseMapper courseMapper;

    public SpecialityMapper() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        courseMapper = injector.getCourseMapper();
    }

    @Override
    public Speciality mapEntityToDomain(SpecialityEntity specialityEntity) {
        return Speciality.newBuilder()
                .withId(specialityEntity.getId())
                .withName(specialityEntity.getName())
                .withStudentsNumber(specialityEntity.getStudentsNumber())
                .withActivity(specialityEntity.getActivity())
                .withBackground(specialityEntity.getBackground())
                .withEmployments(specialityEntity.getEmployments())
                .withExamsStart(specialityEntity.getExamsStart())
                .withExamsEnd(specialityEntity.getExamsEnd())
                .withRequiredCourses(mapSpecEntityToDomain(specialityEntity))
                .build();
    }

    private List<Course> mapSpecEntityToDomain(SpecialityEntity specialityEntity) {
        List<CourseEntity> requiredCourses = specialityEntity.getRequiredCourses();
        return requiredCourses.isEmpty() ?
                Collections.emptyList() :
                requiredCourses.stream()
                        .map(courseMapper::mapEntityToDomain)
                        .collect((Collectors.toList()));
    }

    @Override
    public SpecialityEntity mapDomainToEntity(Speciality speciality) {
        return SpecialityEntity.newBuilder()
                .withId(speciality.getId())
                .withName(speciality.getName())
                .withStudentsNumber(speciality.getStudentsNumber())
                .withActivity(speciality.getActivity())
                .withBackground(speciality.getBackground())
                .withEmployments(speciality.getEmployments())
                .withExamsStart(speciality.getExamsStart())
                .build();
    }
}
