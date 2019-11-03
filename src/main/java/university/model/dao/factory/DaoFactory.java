package university.model.dao.factory;


import university.model.dao.contract.CourseDao;
import university.model.dao.contract.SpecialityDao;

public interface DaoFactory {
    CourseDao getCourseDao();

    SpecialityDao getSpecialityDao();

}
