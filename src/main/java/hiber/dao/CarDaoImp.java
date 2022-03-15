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
    @SuppressWarnings("unchecked")
    public List<Car> getUserByCar(String model, int series) {
        String hql = "from Car where model = ? and series = ?";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter(0, model)
                .setParameter(1, series);
        List<Car> findCarList = query.getResultList();
        if (!findCarList.isEmpty()) {
            return findCarList;
        }
        return null;
    }


}
