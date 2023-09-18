package org.learning.java.springpizzeria.Controller;


import org.learning.java.springpizzeria.Model.Pizza;
import org.learning.java.springpizzeria.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizze", pizzaList);
        return "pizza/list";
    }

    @GetMapping("/show/{pizzaId}")
    public String show(@PathVariable("pizzaId") Integer Id, Model model) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(Id);

        if (pizzaOptional.isPresent()) {
            Pizza pizzaFromDB = pizzaOptional.get();
            model.addAttribute("pizza", pizzaFromDB);
            return "pizza/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}
