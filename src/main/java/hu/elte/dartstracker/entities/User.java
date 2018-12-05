package hu.elte.dartstracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column()
    @NotNull
    private String password;

    @Column
    @NotNull
    private String human_name;

    @Column
    @NotNull
    private String country;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToMany(fetch = FetchType.LAZY, targetEntity=Competition.class, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "competitions_officials",
            joinColumns = {@JoinColumn(name ="official_id")},
            inverseJoinColumns = {@JoinColumn(name = "competition_id")})
    @JsonIgnore
    private List<Competition> competitions;

    public enum Role {
        ROLE_ADMIN, ROLE_OFFICIAL, ROLE_PLAYER
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
