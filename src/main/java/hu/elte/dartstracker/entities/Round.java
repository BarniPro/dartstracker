package hu.elte.dartstracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "rounds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Round implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonIgnore
    public Match match;

    @Column(nullable = false)
    @NotNull
    private Integer player_one_throw_one;

    @Column(nullable = false)
    @NotNull
    private Integer player_one_throw_two;

    @Column(nullable = false)
    @NotNull
    private Integer player_one_throw_three;

    @Column(nullable = false)
    @NotNull
    private Integer player_two_throw_one;

    @Column(nullable = false)
    @NotNull
    private Integer player_two_throw_two;

    @Column(nullable = false)
    @NotNull
    private Integer player_two_throw_three;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getPlayer_one_throw_one() {
        return player_one_throw_one;
    }

    public void setPlayer_one_throw_one(Integer player_one_throw_one) {
        this.player_one_throw_one = player_one_throw_one;
    }

    public Integer getPlayer_one_throw_two() {
        return player_one_throw_two;
    }

    public void setPlayer_one_throw_two(Integer player_one_throw_two) {
        this.player_one_throw_two = player_one_throw_two;
    }

    public Integer getPlayer_one_throw_three() {
        return player_one_throw_three;
    }

    public void setPlayer_one_throw_three(Integer player_one_throw_three) {
        this.player_one_throw_three = player_one_throw_three;
    }

    public Integer getPlayer_two_throw_one() {
        return player_two_throw_one;
    }

    public void setPlayer_two_throw_one(Integer player_two_throw_one) {
        this.player_two_throw_one = player_two_throw_one;
    }

    public Integer getPlayer_two_throw_two() {
        return player_two_throw_two;
    }

    public void setPlayer_two_throw_two(Integer player_two_throw_two) {
        this.player_two_throw_two = player_two_throw_two;
    }

    public Integer getPlayer_two_throw_three() {
        return player_two_throw_three;
    }

    public void setPlayer_two_throw_three(Integer player_two_throw_three) {
        this.player_two_throw_three = player_two_throw_three;
    }
}