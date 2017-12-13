package vdm.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private static final boolean ACCOUNT_NON_EXPIRED = true;
    private static final boolean CREDENTIALS_NON_EXPIRED = true;
    private static final boolean ACCOUNT_NON_LOCKED = true;

    private final int userId;

    SecurityUser(String username,
                 String password,
                 boolean isEnabled,
                 Collection<? extends GrantedAuthority> authorities,
                 int userId) {
        super(username, password, isEnabled, ACCOUNT_NON_EXPIRED, CREDENTIALS_NON_EXPIRED, ACCOUNT_NON_LOCKED, authorities);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        SecurityUser that = (SecurityUser) obj;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId);
    }

}
