package org.learning.java.springpizzeria.Controller;

import org.learning.java.springpizzeria.Model.Offer;
import org.learning.java.springpizzeria.Model.Pizza;
import org.learning.java.springpizzeria.Repository.OfferRepository;
import org.learning.java.springpizzeria.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        Optional<Pizza> pizzaResult = pizzaRepository.findById(pizzaId);
        if (pizzaResult.isPresent()) {
            Pizza pizza = pizzaResult.get();
            Offer offer = new Offer();
            offer.setPizza(pizza);
            offer.setStartDate(LocalDate.now().plusDays(30));
            model.addAttribute("offer", offer);
            return "offer/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza con id" + pizzaId + "nessuna pizza trovata");

        }
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("offer") Offer offerForm) {
        offerRepository.save(offerForm);
        return "redirect:/pizza/show/" + offerForm.getPizza().getId();
    }

    @GetMapping("/edit/{offerId}")
    public String edit(@PathVariable("offerId") Integer id, Model model) {
        Optional<Offer> offerResult = offerRepository.findById(id);
        if (offerResult.isPresent()) {
            model.addAttribute("offer", offerResult.get());
            return "/offer/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }


    @PostMapping("/edit/{offerId}")
    public String doEdit(@PathVariable("offerId") Integer offerId, @ModelAttribute("offer") Offer offerForm) {
        offerForm.setId(offerId);
        offerRepository.save(offerForm);
        return "redirect:/pizza/show/" + offerForm.getPizza().getId();
    }


    @PostMapping("/delete/{offerId}")
    public String delete(@PathVariable("createId") Integer id) {
        Optional<Offer> offerResult = offerRepository.findById(id);
        if (offerResult.isPresent()) {
            Integer pizzaId = offerResult.get().getPizza().getId();
            offerRepository.deleteById(id);
            return "redirect:/pizza/show/" + pizzaId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }
}
