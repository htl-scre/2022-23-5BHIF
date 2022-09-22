package at.htlstp.bhif5.persistence;

import at.htlstp.bhif5.domain.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record DatabaseInit(PizzaRepository pizzaRepository,
                           StudentRepository studentRepository,
                           ClassRepository classRepository) implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var pizzas = List.of(
                new Pizza(null, "Funghi", 7),
                new Pizza(null, "Hawaii", 12),
                new Pizza(null, "Margarita", 5),
                new Pizza(null, "Tonno", 10),
                new Pizza(null, "Provinziale", 7)
        );
        pizzaRepository.saveAll(pizzas);
        var classes = List.of(
                new SchoolClass("5BHIF"),
                new SchoolClass("5AHIF"),
                new SchoolClass("4BHIF"),
                new SchoolClass("1BHIF")
        );
        classes = classRepository.saveAll(classes);
        var students = List.of(
                new Student(null, "Schöppl", 2012345, classes.get(0)),
                new Student(null, "Schachner", 20123453, classes.get(0)),
                new Student(null, "Kainrath", 2012245, classes.get(1)),
                new Student(null, "Kasic", 201234985, classes.get(1)),
                new Student(null, "Heigl", 387465, classes.get(1)),
                new Student(null, "Eberhart", 2348293, classes.get(2)),
                new Student(null, "Jahrling", 23746857, classes.get(3))
        );
        studentRepository.saveAll(students);
    }
}
