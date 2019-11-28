package university.model.dao.entity;

import java.util.Objects;

public class UserEntity {

    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private SpecialityEntity specialityEntity;
    private Role role;

    private UserEntity(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        secondName = builder.secondName;
        email = builder.email;
        password = builder.password;
        specialityEntity = builder.specialityEntity;
        role = builder.role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public Role getRole() {
        return role;
    }

    public static final class Builder {
        private Integer id;
        private String firstName;
        private String secondName;
        private String email;
        private String password;
        private SpecialityEntity specialityEntity;
        private Role role;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withSecondName(String val) {
            secondName = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withSpecialityEntity(SpecialityEntity val) {
            specialityEntity = val;
            return this;
        }

        public Builder withRole(Role val) {
            role = val;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
