package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            List<Member> result = em.createQuery("select m from Member as m",Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

            /*insert */
            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            //영속
//            em.persist(member);

            //준영속 상태

            Member member = new Member();
            member.setUsername("A");
            System.out.println("===============");
            em.persist(member);
            System.out.println("===============");
            /* update */
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloC");

//            em.remove(findMember); deleted

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();



    }
}
