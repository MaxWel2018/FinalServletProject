package university.model.mapper;

import university.domain.SpecialityRequest;
import university.model.dao.entity.SpecialityRequestEntity;

public class SpecialityReqMapper implements Mapper<SpecialityRequest, SpecialityRequestEntity> {


    @Override
    public SpecialityRequest mapEntityToDomain(SpecialityRequestEntity specialityRequestEntity) {
        return SpecialityRequest.newBuilder()
                .withConfirmed(specialityRequestEntity.getConfirmed())
                .withFinalMark(specialityRequestEntity.getFinalMark())
                .withSpecialityId(specialityRequestEntity.getSpecialityId())
                .withSpecialityName(specialityRequestEntity.getSpecialityName())
                .withUserId(specialityRequestEntity.getUserId())
                .withUserName(specialityRequestEntity.getUserName())
                .withUserSurName(specialityRequestEntity.getUserSurName())
                .withId(specialityRequestEntity.getId())
                .build();
    }

    @Override
    public SpecialityRequestEntity mapDomainToEntity(SpecialityRequest specialityRequest) {
        return SpecialityRequestEntity.newBuilder()
                .withConfirmed(specialityRequest.getConfirmed())
                .withFinalMark(specialityRequest.getFinalMark())
                .withSpecialityId(specialityRequest.getSpecialityId())
                .withSpecialityName(specialityRequest.getSpecialityName())
                .withUserId(specialityRequest.getUserId())
                .withUserName(specialityRequest.getUserName())
                .withUserSurName(specialityRequest.getUserSurName())
                .withId(specialityRequest.getId())
                .build();
    }
}
