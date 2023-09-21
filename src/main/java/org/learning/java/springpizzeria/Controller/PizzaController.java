package org.learning.java.springpizzeria.Controller;


import jakarta.validation.Valid;
import org.learning.java.springpizzeria.Model.Pizza;
import org.learning.java.springpizzeria.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizza/form";
        }
        formPizza.setName(formPizza.getName().toUpperCase());
        pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizza/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza con id" + id + "not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pizza/edit";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/pizza";
    }


    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        //cancello la pizza
        pizzaRepository.deleteById(id);
        return "redirect:/pizza";
    }

}





