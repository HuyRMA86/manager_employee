package fa.training.dto;

import fa.training.entities.Gender;
import fa.training.entities.Role;
import fa.training.validator.Phone;
import fa.training.validator.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployee {

    @NotEmpty(message = "{check.notnull}")
    @Length(min = 1, max = 15, message = "{firstName.valid}")
    private String firstName;

    @NotEmpty(message = "{check.notnull}")
    @Length(min = 1, max = 15, message = "{lastName.valid}")
    private String lastName;

    @Sex(message = "{check.notnull}")
    private Gender gender;

    @NotBlank(message = "{check.notnull}")
    private String address;

    private Role role;

    @Past(message = "{dob.valid}")
    private LocalDate dob;

    @NotEmpty(message = "{check.notnull}")
    @Phone(message = "{phone.valid}")
    private String phone;

    private String departmentName;

    @Length(max = 1000, message = "{remark.valid}")
    private String remark;

    @NotEmpty(message = "{check.notnull}")
    private String account;

    @NotEmpty(message = "{check.notnull}")
    @Email(message = "{email.valid}")
    private String email;

    @NotEmpty(message = "{check.notnull}")
    private String password;

    private Boolean status;

}
