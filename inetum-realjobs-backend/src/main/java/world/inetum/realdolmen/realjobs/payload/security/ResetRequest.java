package world.inetum.realdolmen.realjobs.payload.security;

import java.util.UUID;

public class ResetRequest {

    private UUID code;
    private String password;

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
