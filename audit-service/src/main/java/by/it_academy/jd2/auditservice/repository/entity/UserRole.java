package by.it_academy.jd2.auditservice.repository.entity;

public enum UserRole /* implements GrantedAuthority*/ {
    ADMIN, USER, MANAGER;

   // @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
