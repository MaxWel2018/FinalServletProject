package university.model.service;

import university.domain.Speciality;

import java.util.List;

public interface SpecialityService {
    Speciality findById(Integer id);

    void update(Speciality entity);

    List<Speciality> findAll();

    Integer countElementOfTableBySpecId(Integer idSpeciality);

}
