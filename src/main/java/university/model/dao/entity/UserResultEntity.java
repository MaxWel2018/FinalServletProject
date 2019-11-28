package university.model.dao.entity;

import java.util.Objects;

public class UserResultEntity {

    private final Integer id;
    private final Integer specialityId;
    private final String specialityName;
    private final String userName;
    private final String userSurName;
    private final Integer userId;
    private final Integer finalMark;
    private final Boolean confirmed;

    private UserResultEntity(Builder builder) {
        id = builder.id;
        specialityId = builder.specialityId;
        specialityName = builder.specialityName;
        userName = builder.userName;
        userSurName = builder.userSurName;
        userId = builder.userId;
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
        if (!(o instanceof UserResultEntity)) {
            return false;
        }
        UserResultEntity that = (UserResultEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialityId, that.specialityId) &&
                Objects.equals(specialityName, that.specialityName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userSurName, that.userSurName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(finalMark, that.finalMark) &&
                Objects.equals(confirmed, that.confirmed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialityId, specialityName, userName, userSurName, userId, finalMark, confirmed);
    }

    public Integer getId() {
        return id;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFinalMark() {
        return finalMark;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public static final class Builder {
        private Integer id;
        private Integer specialityId;
        private String specialityName;
        private String userName;
        private String userSurName;
        private Integer userId;
        private Integer finalMark;
        private Boolean confirmed;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withSpecialityId(Integer val) {
            specialityId = val;
            return this;
        }

        public Builder withSpecialityName(String val) {
            specialityName = val;
            return this;
        }

        public Builder withUserName(String val) {
            userName = val;
            return this;
        }

        public Builder withUserSurName(String val) {
            userSurName = val;
            return this;
        }

        public Builder withUserId(Integer val) {
            userId = val;
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

        public UserResultEntity build() {
            return new UserResultEntity(this);
        }
    }
}
