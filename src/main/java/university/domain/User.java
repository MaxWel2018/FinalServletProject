package university.domain;

import university.model.dao.entity.Role;

import java.util.Objects;

public class User {

    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Speciality speciality;
    private Role role;

    private User(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        secondName = builder.secondName;
        email = builder.email;
        password = builder.password;
        speciality = builder.speciality;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
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

    public Speciality getSpeciality() {
        return speciality;
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
        private Speciality speciality;
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

        public Builder withSpeciality(Speciality val) {
            speciality = val;
            return this;
        }

        public Builder withRole(Role val) {
            role = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
