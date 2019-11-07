package university.model.dao.entity;

import java.util.Objects;

public class SpecialityRequestEntity {

    private Integer id;
    private SpecialityEntity specialityEntityId;
    private UserEntity userEntityId;
    private Integer finalMark;
    private Boolean confirmed;

    private SpecialityRequestEntity(Builder builder) {
        id = builder.id;
        specialityEntityId = builder.specialityEntityId;
        userEntityId = builder.userEntityId;
        finalMark = builder.finalMark;
        confirmed = builder.confirmed;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialityRequestEntity)) {
            return false;
        }
        SpecialityRequestEntity that = (SpecialityRequestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialityEntityId, that.specialityEntityId) &&
                Objects.equals(userEntityId, that.userEntityId) &&
                Objects.equals(finalMark, that.finalMark) &&
                Objects.equals(confirmed, that.confirmed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialityEntityId, userEntityId, finalMark, confirmed);
    }

    public Integer getId() {
        return id;
    }

    public SpecialityEntity getSpecialityEntityId() {
        return specialityEntityId;
    }

    public UserEntity getUserEntityId() {
        return userEntityId;
    }

    public Integer getFinalMark() {
        return finalMark;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public static final class Builder {
        private Integer id;
        private SpecialityEntity specialityEntityId;
        private UserEntity userEntityId;
        private Integer finalMark;
        private Boolean confirmed;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withSpecialityId(SpecialityEntity val) {
            specialityEntityId = val;
            return this;
        }

        public Builder withUserId(UserEntity val) {
            userEntityId = val;
            return this;
        }

        public Builder withFinalMark(Integer val) {
            finalMark = val;
            return this;
        }

        public Builder withConfirmed(Boolean val) {
            confirmed = val;
            return this;
        }

        public SpecialityRequestEntity build() {
            return new SpecialityRequestEntity(this);
        }
    }
}
