package domain.models.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class InicioSesionDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
