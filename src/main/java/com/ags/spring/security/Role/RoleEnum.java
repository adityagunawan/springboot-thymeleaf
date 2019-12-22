package com.ags.spring.security.Role;

import com.ags.spring.security.model.Role;

public enum RoleEnum {
    ADMIN_BUJP("ADMIN_BUJP"),
    ADMIN_REPORTING("ADMIN_REPORTING"),
    ADMIN_SATPAM("ADMIN_SATPAM"),
    BINMAS_MEMBER("BINMAS_MEMBER"),
    BUJP("BUJP"),
    ORGANIC_ORGANIZATION("ORGANIC_ORGANIZATION"),
    SATPAM("SATPAM"),
    USER("user"),
    ;

    private Role role;

    RoleEnum(String authority) {
        this.role = new Role(authority);
    }

    public Role getRole() {
        return role;
    }

    public String getValue() {
        return role.getAuthority();
    }

    public String getLabel() {
        return "label.role." + role.getAuthority();
    }
}