package university.model.service.mapper;

import university.domain.SpecialityRequest;
import university.model.dao.entity.SpecialityRequestEntity;

public class SpecialityReqDomainMapper {

    public SpecialityRequest mapSpecReqEntityToSpecReq(SpecialityRequestEntity specialityRequestEntity) {
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

}
