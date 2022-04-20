package al.ikubinfo.hrmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;

@Entity
@Where(clause = "deleted=0")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
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
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "paid_time_off")
    private int paidTimeOff;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "role")
    private RoleEntity role;


    @ManyToMany(mappedBy = "userEntityDepartment")
    private Set<DepartmentEntity> departmentEntity = new HashSet<>();




}


