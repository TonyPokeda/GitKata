package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car ("VAZ", 2107);
      Car car2 = new Car ("BMW", 3);
      userService.addCar(car1);
      userService.addCar(car2);

      User user1 = new User("Kolya", "Petrov", "kolya@mail.ru");
      User user2 = new User("Pasha", "Ivanov", "pasha@mail.ru");
      userService.add(user1);
      userService.add(user2);

      user1.setCar(car1);
      user2.setCar(car2);

      userService.findUserByCarModelAndSeries("VAZ", 2107);
      userService.findUserByCarModelAndSeries("BMW", 3);

      context.close();
   }
}
