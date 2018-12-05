package hu.elte.dartstracker.entities;

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
@Table(name = "competitions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Competition implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column()
    @NotNull
    private String country;

    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date end_date;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity=User.class, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "competitions_officials",
            joinColumns = {@JoinColumn(name ="competition_id")},
            inverseJoinColumns = {@JoinColumn(name = "official_id")})
    private List<User> officials;

    public void removeOfficial(User official) {
        officials.remove(official);
    }

    public List<User> getOfficials() {
        return officials;
    }

    public void setOfficials(List<User> officials) {
        this.officials = officials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
