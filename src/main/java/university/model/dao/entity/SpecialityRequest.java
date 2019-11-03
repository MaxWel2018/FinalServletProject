package university.model.dao.entity;

import java.util.Objects;

public class SpecialityRequest {

    private int id;
    private int specialityId;
    private int userId;
    private int finalMark;
    private String confirmed;
    private Speciality speciality;

    private SpecialityRequest(Builder builder) {
        id = builder.id;
        specialityId = builder.specialityId;
        userId = builder.userId;
        finalMark = builder.finalMark;
        confirmed = builder.confirmed;
        speciality = builder.speciality;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public int getId() {
        return id;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public int getUserId() {
        return userId;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public Speciality getSpeciality() {
        return speciality;
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
        return id == that.id &&
                specialityId == that.specialityId &&
                userId == that.userId &&
                finalMark == that.finalMark &&
                Objects.equals(confirmed, that.confirmed) &&
                Objects.equals(speciality, that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialityId, userId, finalMark, confirmed, speciality);
    }

    public static final class Builder {
        private int id;
        private int specialityId;
        private int userId;
        private int finalMark;
        private String confirmed;
        private Speciality speciality;

        private Builder() {
        }

        public Builder withId(int val) {
            id = val;
            return this;
        }

        public Builder withSpecialityId(int val) {
            specialityId = val;
            return this;
        }

        public Builder withUserId(int val) {
            userId = val;
            return this;
        }

        public Builder withFinalMark(int val) {
            finalMark = val;
            return this;
        }

        public Builder withConfirmed(String val) {
            confirmed = val;
            return this;
        }

        public Builder withSpeciality(Speciality val) {
            speciality = val;
            return this;
        }

        public SpecialityRequest build() {
            return new SpecialityRequest(this);
        }
    }
}
