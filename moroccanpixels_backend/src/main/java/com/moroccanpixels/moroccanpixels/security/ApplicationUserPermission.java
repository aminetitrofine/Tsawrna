package com.moroccanpixels.moroccanpixels.security;

public enum ApplicationUserPermission {
    IMAGE_READ("image:read"),
    IMAGE_WRITE("image:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_BAN("user:ban");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
