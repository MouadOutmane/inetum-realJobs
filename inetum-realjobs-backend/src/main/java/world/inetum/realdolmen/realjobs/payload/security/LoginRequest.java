package world.inetum.realdolmen.realjobs.payload.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginRequest {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
