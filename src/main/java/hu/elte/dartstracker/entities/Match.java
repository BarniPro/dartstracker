package hu.elte.dartstracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Match implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Competition.class)
    @NotNull
    private Competition competition;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User player_one;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User player_two;

    @Column(nullable = false)
    @NotNull
    private Integer player_one_score;

    @Column(nullable = false)
    @NotNull
    private Integer player_two_score;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    public List<Round> rounds;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public User getPlayer_one() {
        return player_one;
    }

    public void setPlayer_one(User player_one) {
        this.player_one = player_one;
    }

    public User getPlayer_two() {
        return player_two;
    }

    public void setPlayer_two(User player_two) {
        this.player_two = player_two;
    }

    public Integer getPlayer_one_score() {
        return player_one_score;
    }

    public void setPlayer_one_score(Integer player_one_score) {
        this.player_one_score = player_one_score;
    }

    public Integer getPlayer_two_score() {
        return player_two_score;
    }

    public void setPlayer_two_score(Integer player_two_score) {
        this.player_two_score = player_two_score;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}