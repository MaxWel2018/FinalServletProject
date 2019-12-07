package university.model.dao.mapper.impl;

import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.mapper.CourseEntityMapper;
import university.model.dao.mapper.SpecialityEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityEntityMapperImpl implements SpecialityEntityMapper {
    private final CourseEntityMapper courseEntityMapper;

    public SpecialityEntityMapperImpl(CourseEntityMapper courseEntityMapper) {
        this.courseEntityMapper = courseEntityMapper;
    }

    @Override
    public SpecialityEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return resultSet.getObject("Id_Speciality") == null ? null : SpecialityEntity.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .withActivity(resultSet.getString("Activity"))
                .withBackground(resultSet.getString("Background"))
                .withEmployments(resultSet.getString("Employments"))
                .withExamsStart((resultSet.getDate("Exams_Start")).toLocalDate())
                .withExamsEnd((resultSet.getDate("Exams_End")).toLocalDate())
                .withRequiredCourses(mapResultSetToSpecCourse(resultSet, resultSet.getString("Speciality_Name")))
                .build();
    }

    private List<CourseEntity> mapResultSetToSpecCourse(ResultSet resultSet, String speciality_name) throws SQLException {
        List<CourseEntity> courses = new ArrayList<>();
        int currentRow = resultSet.getRow();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            if(resultSet.getString("Speciality_Name").equals(speciality_name))
                courses.add(courseEntityMapper.mapResultSetToEntity(resultSet));
        }
        resultSet.absolute(currentRow);
        return courses;
    }
}
