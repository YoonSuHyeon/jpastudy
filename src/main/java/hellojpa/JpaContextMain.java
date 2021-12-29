package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaContextMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");
            //영속
            em.persist(member);
            //1차  캐시 사용
            Member findMember = em.find(Member.class, 100L);
//            //준영속
//            em.detach(member);
//            //삭제
//            em.remove(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
