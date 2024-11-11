package az.huseynov.springMVC.securityThymeleaf.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebUser {

    @NotNull(message = "is required")
    @Size(min = 3, message = "min size 3")
    String username;

    @NotNull(message = "is required")
    @Size(min = 3, message = "min size 3")
    String password;

    @NotNull(message = "is required")
    String name;

    @NotNull(message = "is required")
    String surname;

    @NotNull(message = "is required")
    String email;


}
