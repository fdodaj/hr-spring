package al.ikubinfo.hrmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "deleted = 0")
@Table(name = "user")
@NamedQuery(name = "User.findAllWithLowerPtoThanTen", query = "SELECT user FROM UserEntity user where user.paidTimeOff < 10 ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "paid_time_off")
    private int paidTimeOff;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_role")
    @JsonIgnore
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "user_department")
    @JsonIgnore
    private DepartmentEntity department;



    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<RequestEntity> requests = new ArrayList<>();


}


