package university.model.dao.entity;

import java.util.Objects;

public class User {

    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Role role;

    private User(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        secondName = builder.secondName;
        email = builder.email;
        password = builder.password;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public int getId() {
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

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, email, password, role);
    }

    public static final class Builder {
        private int id;
        private String firstName;
        private String secondName;
        private String email;
        private String password;
        private Role role;

        private Builder() {
        }

        public Builder withId(int val) {
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

        public Builder withRole(Role val) {
            role = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
