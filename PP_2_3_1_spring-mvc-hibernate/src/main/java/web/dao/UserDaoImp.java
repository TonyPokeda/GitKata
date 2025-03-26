package web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<web.model.User> getAllUsers() {
        System.out.println("getAllUsers method called");
        return entityManager.createQuery("select u from User u", web.model.User.class).getResultList();
    }

    public web.model.User getUserById(int id) {
        return entityManager.find(web.model.User.class, id);
    }

    public void save(web.model.User user) {
        entityManager.persist(user);
    }

    public void update(web.model.User user) {
        entityManager.merge(user);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.find(web.model.User.class, id));
    }
}
