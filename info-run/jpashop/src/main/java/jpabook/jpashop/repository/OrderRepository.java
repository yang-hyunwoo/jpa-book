package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSeach) {

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        if(orderSeach.getOrderStatus() != null) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        if (StringUtils.hasText(orderSeach.getMemberName())) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if(orderSeach.getOrderStatus() != null) {
            query = query.setParameter("status", orderSeach.getOrderStatus());
        }
        if (StringUtils.hasText(orderSeach.getMemberName())) {
            query = query.setParameter("name", orderSeach.getMemberName());
        }

        return query.getResultList();



//        return em.createQuery("select o from Order o join o.member m" +
//                " where o.status = :status" +
//                " and m.name like :name", Order.class)
//                .setParameter("status",orderSeach.getOrderStatus())
//                .setParameter("name",orderSeach.getMemberName())
//                .setMaxResults(1000) //최대 1000건
//                .getResultList();

    }

}
