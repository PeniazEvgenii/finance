package by.it_academy.jd2.repository.entity;

public enum EUserRole /* implements GrantedAuthority*/ {
    ADMIN, USER, MANAGER;

   // @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
