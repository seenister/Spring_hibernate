package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    void add(Car car);
    List<Car> listCars();
    void cleanCarsTable();
}
