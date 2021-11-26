package world.inetum.realdolmen.inetumrealJobs.payload.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}