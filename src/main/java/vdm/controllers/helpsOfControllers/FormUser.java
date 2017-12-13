package vdm.controllers.helpsOfControllers;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import vdm.helpers.Role;
import javax.validation.constraints.Size;

@Component
public class FormUser {

    @NotNull
    private Role role;

    @NotNull
    @Size(min=3, max=20,
            message="  name must be between 3 and 20 characters long.")
    private String name;

    @NotNull
    @Size(min=3, max=20,
            message=" lastName must be between 3 and 20 characters long.")
    private String lastName;

    @NotNull
    @Size(min=3, max=20,
            message=" email must be between 3 and 20 characters long.")
    private String email;

    @NotNull
    @Size(min=4, max=20,
          message="password must be between 4 and 20 characters long")
    private String password;

    private boolean isEnabled;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
