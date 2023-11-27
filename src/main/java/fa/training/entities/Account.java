package fa.training.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account", schema = "dbo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private int status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", unique = true)
    private Employee employee;
}
