package com.app.consMed.Modules.User.Enums;

public enum UserRole {
        ADMIN("admin"),
        RECEPCIONISTA("recepcionista"),
        MEDICO("medico");

        private String role;

        UserRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
}
