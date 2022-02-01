package com.moroccanpixels.moroccanpixels.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.moroccanpixels.moroccanpixels.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN("admin", Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CONTRIBUTOR("contributor", Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CLIENT("client", Set.of(IMAGE_READ,USER_WRITE,USER_READ));

    private final String value;
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(String value, Set<ApplicationUserPermission> permissions) {
        this.value = value;
        this.permissions = permissions;
    }

    public String getValue() {
        return value;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return grantedAuthorities;
    }
}
