package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.From;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car"); //
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        String hql = "From User where Car.model = ? and Car.series = ?";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, model).setParameter(1, series);
        return query.getResultList();
    }
}
