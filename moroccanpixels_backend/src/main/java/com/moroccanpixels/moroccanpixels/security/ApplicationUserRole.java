package com.moroccanpixels.moroccanpixels.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.moroccanpixels.moroccanpixels.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CONTRIBUTOR(Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CLIENT(Set.of(IMAGE_READ,USER_WRITE,USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public static ApplicationUserRole fromName(String value){
        for(ApplicationUserRole role : ApplicationUserRole.values()){
            if(role.name().equals(value)){
                return role;
            }
        }
        return null;
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
