package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);
    void cleanUserTable();
    List<User> listUsers();
    List<User> getUserByCar(String model, int series);
}
