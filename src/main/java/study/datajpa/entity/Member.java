package study.datajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) //연관관계 필드X (team)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //연관관계: LAZY
    @JoinColumn(name = "team_id")
    private Team team;


    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }


    }

    /**
     *
     * 연관관계 메소드, Member는 Team을 변경 가능
     */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
