package com.moroccanpixels.moroccanpixels.security;

import java.util.Set;

import static com.moroccanpixels.moroccanpixels.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CONTRIBUTOR(Set.of(IMAGE_WRITE,IMAGE_READ,USER_READ,USER_WRITE)),
    CLIENT(Set.of(IMAGE_READ,USER_WRITE,USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
