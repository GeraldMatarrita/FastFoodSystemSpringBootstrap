package com.proyecto.core.controller;

import com.proyecto.core.model.payment.CardType;
import com.proyecto.core.repository.CardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/cardtypes")
public class CardTypeController {

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @GetMapping(value = "/show")
    public String showCardType(Model model) {
        model.addAttribute("cardtypes", cardTypeRepository.findAll());
        return "AdministratorFunctions/CardType/showCardType";
    }
    @GetMapping(value = "/create")
    public String saveCardType(Model model) {
        model.addAttribute("cardtype", new CardType());
        return "AdministratorFunctions/CardType/addCardType";
    }
    @PostMapping(value = "/create")
    public String create(@ModelAttribute @Validated CardType cardType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/CardType/addCardType";
        }
        if (cardTypeRepository.findById(cardType.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cardtypes/create";
        }
        cardTypeRepository.save(cardType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/cardtypes/create";
    }

    @PostMapping(value = "/delete")
    public String deleteCardType(@ModelAttribute CardType cardtype, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        cardTypeRepository.deleteById(cardtype.getId());
        return "redirect:/cardtypes/show";
    }

    @PostMapping(value = "/edit/{id}")
    public String editCardType(@ModelAttribute @Valid CardType cardType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (cardType.getId() != null) {
                return "AdministratorFunctions/CardType/editCardType";
            }
            return "redirect:/cardtypes/show";
        }
        CardType possibleCardType = cardTypeRepository.findFirstById(cardType.getId());

        if (possibleCardType != null && !possibleCardType.getId().equals(cardType.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un cardtype con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cardtypes/create";
        }
        cardTypeRepository.save(cardType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/cardtypes/show";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("cardtype", cardTypeRepository.findById(id).orElse(null));
        return "AdministratorFunctions/CardType/editCardType";
    }

}
