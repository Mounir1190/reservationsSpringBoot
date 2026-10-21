package be.iccbxl.pid.reservationsspringboot.dto;

import be.iccbxl.pid.reservationsspringboot.validation.PasswordMatches;
import be.iccbxl.pid.reservationsspringboot.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class UserRegistrationDto {

    @NotBlank(message = "Le login ne peut pas être vide")
    @Size(min = 3, max = 50, message = "Le login doit faire entre 3 et 50 caractères")
    private String login;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @StrongPassword
    private String password;

    private String confirmPassword;

    private String lastname;
    private String firstname;
    private String langue;
}