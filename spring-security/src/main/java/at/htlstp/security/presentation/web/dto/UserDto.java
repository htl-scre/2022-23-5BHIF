package at.htlstp.security.presentation.web.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class UserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordRepeat;

    @AssertTrue(message = "Passwords must match")
    public boolean isValid() {
        return password.equals(passwordRepeat);
    }
}
