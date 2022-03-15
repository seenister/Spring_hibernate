package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserByCar(String model, int series) {


        String hql = "SELECT U FROM User AS U INNER JOIN U.car AS C WHERE C.model=:model AND C.series=:series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("series", series)
                .setParameter("model", model);
        List<User> findUserList = query.getResultList();
        if (!findUserList.isEmpty()) {
            return findUserList;
        }
        return null;
    }


    @Override
    public void cleanUserTable() {
        String sql = "DROP TABLE IF EXISTS users";
        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }
}
