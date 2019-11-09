package university.model.service.mapper;


import university.context.ApplicationContextInjector;
import university.domain.Speciality;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.impl.SpecialityDaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityDomainMapper {
    private static final ApplicationContextInjector applicationContextInjector = ApplicationContextInjector.getInstance();
    private final HikariConnectionPool connectionPool = applicationContextInjector.getHikariConnectionPool();
    private final SpecialityDao specialityDao = new SpecialityDaoImpl(connectionPool);
    private final CourseMapperDomain courseMapperDomain = new CourseMapperDomain();


    public Speciality mapSpecialityEntityToSpeciality(SpecialityEntity specialityEntity) {
        Speciality.Builder builder = Speciality.newBuilder();
        builder.withId(specialityEntity.getId());
        builder.withName(specialityEntity.getName());
        builder.withStudentsNumber(specialityEntity.getStudentsNumber());
        builder.withActivity(specialityEntity.getActivity());
        builder.withBackground(specialityEntity.getBackground());
        builder.withEmployments(specialityEntity.getEmployments());
        List<CourseEntity> requiredCoursesListBySpecId = specialityDao.getRequiredCoursesListBySpecId(specialityEntity.getId());
        builder.withRequiredCourses(
                requiredCoursesListBySpecId.isEmpty()
                        ? Collections.emptyList()
                        : requiredCoursesListBySpecId.stream()
                        .map(courseMapperDomain::mapCourseEntityToCourse)
                        .collect((Collectors.toList())));
        return builder
                .build();

    }
}
