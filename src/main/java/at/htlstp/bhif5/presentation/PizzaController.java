package at.htlstp.bhif5.presentation;

import at.htlstp.bhif5.persistence.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("pizzas")
public record PizzaController(PizzaRepository pizzaRepository) {

//    @GetMapping("hello")
//    public ModelAndView getGreeting() {
//        var model = Map.of("name", "SCRE");
//        return new ModelAndView("greeting", model);
//    }

    @GetMapping("hello")
    public String getGreeting(Model model) {
        model.addAttribute("name", "SCRE");
        return "greeting";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "menu";
    }
}
