package university.model.dao.entity;

public enum Role {
    USER {
        @Override
        public String toString() {
            return "user";
        }
    },
    ADMIN {
        @Override
        public String toString() {
            return "admin";
        }
    };

    public static Role getRole(String role) {
        if (role != null) {
            switch (role) {
                case "user":
                    return USER;
                case "admin":
                    return ADMIN;
                default:
                    throw new IllegalArgumentException("This role doesn't exist");
            }

        } else {
            throw new IllegalArgumentException("Role is null");
        }
    }
}