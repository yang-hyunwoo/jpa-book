package jpql;


import javax.persistence.*;
import java.util.List;

public class JpaMain {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m" , MemberDTO.class).getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO. = " + memberDTO.getUsername());
//            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());
//            Member findMember = memberList.get(0);
//            findMember.setAge(20);

//            Member singleResult = em.createQuery("select m from Member m where m.username=:username", Member.class).
//                    setParameter("username", "member1")
//                    .getSingleResult();

//            System.out.println("result = "+singleResult);
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//
//            em.flush();
//            em.clear();
//
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println(resultList.size());
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("teamA");
//            member.setAge(10);
//
//            member.setTeam(team);
//
//            em.persist(member);
//            em.flush();
//            em.clear();
//
//            String query = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> resultList = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            System.out.println(resultList.size());

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("teamA");
//            member.setType(MemberType.ADMIN);
//            member.setAge(10);
//
//            em.persist(member);
//            em.flush();
//            em.clear();

//            String query = "select m.username , 'HELLO' ,TRUE From Member m " +
//                    "where m.type = :userType";
//            List<Object[]> resultList = em.createQuery(query).setParameter("userType",MemberType.ADMIN).getResultList();
//
//            for (Object[] objects : resultList) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }

//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "      when m.age >= 60 then '경로요금' " +
//                    "else '일반요금'" +
//                    "end " +
//                    "from Member m";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for (String s : resultList) {
//                System.out.println("s = "+s);
//            }



            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m From Member m join fetch m.team";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            String query = "select distinct t From Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class).getResultList();

            for (Team team : resultList) {
                System.out.println("member= " + team.getName() +" ," + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("::"+member);
                }
            }


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
            emf.close();
    }

}
