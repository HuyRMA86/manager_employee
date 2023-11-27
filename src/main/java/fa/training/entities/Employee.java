
package fa.training.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee", schema = "dbo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Column(name = "phone", unique = true)
    private String phone;

    private String address;

    @Column(name = "department_name")
    private  String departmentName;

    private String remark;

    @ToString.Exclude
    @OneToOne(mappedBy = "employee")
    private Account account;
}
