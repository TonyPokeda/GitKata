package web.service;

import org.springframework.stereotype.Service;
import web.model.Cars;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final List<Cars> cars;

    public CarService () {
        cars = new ArrayList<>();
        cars.add(new Cars("BMW", 5,"WHITE"));
        cars.add(new Cars("BMW", 4,"BLACK"));
        cars.add(new Cars("BMW",3,"GREEN"));
        cars.add(new Cars("BMW",4,"RED"));
        cars.add(new Cars("BMW",5,"BLUE"));
    }

    public List<Cars> getCars(int count) {
        if (count >= 5) {
            return cars;
        }
        return cars.subList(0, count);
    }

}
