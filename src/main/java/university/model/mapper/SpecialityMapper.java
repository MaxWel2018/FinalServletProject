package university.model.mapper;


import university.context.ApplicationContextInjector;
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
    private static final ApplicationContextInjector applicationContextInjector = ApplicationContextInjector.getInstance();
    private final HikariConnectionPool connectionPool = applicationContextInjector.getHikariConnectionPool();
    private final SpecialityDao specialityDao = new SpecialityDaoImpl(connectionPool);
    private final CourseMapper courseMapper = new CourseMapper();


    @Override
    public Speciality mapEntityToDomain(SpecialityEntity specialityEntity) {
        Speciality.Builder builder = Speciality.newBuilder();
        builder.withId(specialityEntity.getId());
        builder.withName(specialityEntity.getName());
        builder.withStudentsNumber(specialityEntity.getStudentsNumber());
        builder.withActivity(specialityEntity.getActivity());
        builder.withBackground(specialityEntity.getBackground());
        builder.withEmployments(specialityEntity.getEmployments());
        builder.withExamsStart(specialityEntity.getExamsStart());
        builder.withExamsEnd(specialityEntity.getExamsEnd());
        List<CourseEntity> requiredCoursesListBySpecId = specialityDao.getRequiredCoursesListBySpecId(specialityEntity.getId());
        builder.withRequiredCourses(
                requiredCoursesListBySpecId.isEmpty()
                        ? Collections.emptyList()
                        : requiredCoursesListBySpecId.stream()
                        .map(courseMapper::mapCourseEntityToCourse)
                        .collect((Collectors.toList())));
        return builder
                .build();
    }

    @Override
    public SpecialityEntity mapDomainToEntity(Speciality speciality) {
        return null;
    }
}
