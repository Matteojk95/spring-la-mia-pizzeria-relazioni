package org.learning.java.springpizzeria.Controller;

import org.learning.java.springpizzeria.Model.Ingredienti;
import org.learning.java.springpizzeria.Repository.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredients")
public class IngredientiController {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientsList", ingredientiRepository.findAll());
        model.addAttribute("ingredientsObj", new Ingredienti());
        return "ingredients/index";
    }

    @PostMapping("create")
    public String doCreate(@ModelAttribute("ingredientsObj") Ingredienti ingredientsForm,
                           RedirectAttributes redirectAttributes) {
        ingredientiRepository.save(ingredientsForm);
        redirectAttributes.addFlashAttribute("message", "Category successfully added!");
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{ingId}")
    public String delete(@PathVariable("ingId") Integer id) {
        ingredientiRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
