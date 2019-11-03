package university.model.dao.factory;

import org.apache.log4j.Logger;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.CourseDao;
import university.model.dao.contract.SpecialityDao;
import university.model.dao.impl.CourseDaoImpl;
import university.model.dao.impl.SpecialityDaoImpl;

import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {

    private final static Logger LOGGER = Logger.getLogger(DaoFactoryImpl.class);

    private static volatile DaoFactoryImpl instance;
    private CourseDao courseDao;
    private SpecialityDao specialityDao;

    private DaoFactoryImpl(HikariConnectionPool dataSource) throws SQLException {
        courseDao = new CourseDaoImpl(dataSource);
        specialityDao = new SpecialityDaoImpl(dataSource);
    }

    public static DaoFactoryImpl getInstance()  {
        if (instance == null) {
            synchronized (DaoFactoryImpl.class) {
                if (instance == null) {
                    try {
                        instance = new DaoFactoryImpl(new HikariConnectionPool());
                    } catch (SQLException e) {
                        LOGGER.error("Connection failed"+e);
                    }
                    //TODO  Это норма ?
                }
            }
        }
        return instance;
    }


    @Override
    public CourseDao getCourseDao() {
        return courseDao;
    }

    @Override
    public SpecialityDao getSpecialityDao() {
        return specialityDao;
    }
}
