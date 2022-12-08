package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "team")  //읽기 기능만 되고 mappedBy에 team ->Member team이 주인이 된다. ~toOne 쪽이 무조건 주인이 된다.
//    private List<Member> members = new ArrayList<>();

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

//    public List<Member> getMembers() {
//        return members;
//    }

//    public void setMembers(List<Member> members) {
//        this.members = members;
//    }

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }
}
