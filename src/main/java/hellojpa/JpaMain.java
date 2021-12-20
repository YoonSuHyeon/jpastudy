package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //하나만 생성되고 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //쓰레드간에 공유 X
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //insert
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("TEST");
            Member member = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            member.setName("Change");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
