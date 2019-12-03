package university.model.mapper;


import university.domain.Course;
import university.domain.Speciality;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialityMapper implements Mapper<Speciality, SpecialityEntity> {
    private final CourseMapper courseMapper;

    public SpecialityMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
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
                .withRequiredCourses(mapSpecialityCoursesEntityToDomain(specialityEntity))
                .build();
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
                .withRequiredCourses(mapSpecialityEntityToDomain(speciality))
                .build();
    }

    private List<CourseEntity> mapSpecialityEntityToDomain(Speciality speciality) {
        List<Course> requiredCourses = speciality.getRequiredCourses();
        return requiredCourses
                .stream()
                .map(courseMapper::mapDomainToEntity)
                .collect(Collectors.toList());
    }

    private List<Course> mapSpecialityCoursesEntityToDomain(SpecialityEntity specialityEntity) {
        List<CourseEntity> requiredCourses = specialityEntity.getRequiredCourses();
        return requiredCourses==null?null:requiredCourses.stream()
                .map(courseMapper::mapEntityToDomain)
                .collect((Collectors.toList()));
    }
}
