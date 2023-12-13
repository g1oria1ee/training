package org.gloria.training.dao;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.gloria.training.model.Rectangle;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RectangleDao {

    @PersistenceContext
    private final EntityManager em;

    public Session currentSession() {
        return em.unwrap(Session.class);
    }

    public Rectangle addNewRectangle(Rectangle rectangle) {
        Session session = this.currentSession();
        session.save(rectangle);
        return rectangle;
    }

//    public Rectangle deleteById(Integer id) {
//        Session session = this.currentSession();
//        return
//    }

    public Rectangle getRectangleById(Integer id) {

        Session session = this.currentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Rectangle> criteriaQuery = criteriaBuilder.createQuery(Rectangle.class);
        Root<Rectangle> root = criteriaQuery.from(Rectangle.class);
        Predicate idPredicate = criteriaBuilder
                .equal(root.get("id"), id);
        criteriaQuery.where(idPredicate);
        TypedQuery<Rectangle> query = session.createQuery(criteriaQuery);
        Rectangle rectangle = query.getSingleResult();
        return rectangle;
    }

    public List<Rectangle> getAllRectangles() {
        Session session = this.currentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Rectangle> criteriaQuery = criteriaBuilder.createQuery(Rectangle.class);

        Root<Rectangle> root = criteriaQuery.from(Rectangle.class);
        TypedQuery<Rectangle> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public Rectangle deleteRectangle(Integer id) {
        Session session = this.currentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Rectangle> criteriaQuery = criteriaBuilder.createQuery(Rectangle.class);
        Root<Rectangle> root = criteriaQuery.from(Rectangle.class);
        Predicate idPredicate = criteriaBuilder
                .equal(root.get("id"), id);
        criteriaQuery.where(idPredicate);
        TypedQuery<Rectangle> query = session.createQuery(criteriaQuery);

        Rectangle rectangle = query.getSingleResult();
        session.delete(rectangle);
        return rectangle;
    }

    public Rectangle updateRectangle(Rectangle rectangle) {
        Session session = this.currentSession();
        session.update(rectangle);
        return rectangle;
    }
}
