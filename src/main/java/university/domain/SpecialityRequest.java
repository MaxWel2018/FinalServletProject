package university.domain;

import java.util.Objects;

public class SpecialityRequest {

    private Integer id;
    private Speciality specialityId;
    private User user;
    private Integer finalMark;
    private Boolean confirmed;

    private SpecialityRequest(Builder builder) {
        id = builder.id;
        specialityId = builder.specialityId;
        user = builder.userId;
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
        if (!(o instanceof SpecialityRequest)) {
            return false;
        }
        SpecialityRequest that = (SpecialityRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialityId, that.specialityId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(finalMark, that.finalMark) &&
                Objects.equals(confirmed, that.confirmed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialityId, user, finalMark, confirmed);
    }

    public Integer getId() {
        return id;
    }

    public Speciality getSpecialityId() {
        return specialityId;
    }

    public User getUser() {
        return user;
    }

    public Integer getFinalMark() {
        return finalMark;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public static final class Builder {
        private Integer id;
        private Speciality specialityId;
        private User userId;
        private Integer finalMark;
        private Boolean confirmed;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withSpecialityId(Speciality val) {
            specialityId = val;
            return this;
        }

        public Builder withUserId(User val) {
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

        public SpecialityRequest build() {
            return new SpecialityRequest(this);
        }
    }
}
