package ucll.project.domain.model.user;

public enum Role {
    CAMPUSADMIN("campusadmin"),
    ADMIN("admin");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
