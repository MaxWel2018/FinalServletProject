package university.model.mapper;

import university.domain.UserResult;
import university.model.dao.entity.UserResultEntity;

public class SpecialityReqMapper implements Mapper<UserResult, UserResultEntity> {

    @Override
    public UserResult mapEntityToDomain(UserResultEntity userResultEntity) {
        return UserResult.newBuilder()
                .withConfirmed(userResultEntity.getConfirmed())
                .withFinalMark(userResultEntity.getFinalMark())
                .withSpecialityId(userResultEntity.getSpecialityId())
                .withSpecialityName(userResultEntity.getSpecialityName())
                .withUserId(userResultEntity.getUserId())
                .withUserName(userResultEntity.getUserName())
                .withUserSurName(userResultEntity.getUserSurName())
                .withId(userResultEntity.getId())
                .build();
    }

    @Override
    public UserResultEntity mapDomainToEntity(UserResult userResult) {
        return UserResultEntity.newBuilder()
                .withConfirmed(userResult.getConfirmed())
                .withFinalMark(userResult.getFinalMark())
                .withSpecialityId(userResult.getSpecialityId())
                .withSpecialityName(userResult.getSpecialityName())
                .withUserId(userResult.getUserId())
                .withUserName(userResult.getUserName())
                .withUserSurName(userResult.getUserSurName())
                .withId(userResult.getId())
                .build();
    }
}
