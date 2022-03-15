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
        /*  String hql = "from Car where model = ? and series = ?";*/
        String hql2 = "from User inner join Car c on User.id = c.user.id where Car.model = ? and Car.series = ?";

        String hql = "from User where id = (select Car.user_id from Car where Car.model = ? and Car.series = ?)";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlV2)
                .setParameter(0, model)
                .setParameter(1, series);
        List<User> findCarList = query.getResultList();
        if (!findCarList.isEmpty()) {
            return findCarList;
        }
        return null;
    }


}
