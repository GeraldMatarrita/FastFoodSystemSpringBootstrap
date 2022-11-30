package com.proyecto.core.ecommerce.controller;

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
@RequestMapping("/ecommerce")
public class mainController {

    @GetMapping(value = "/main")
    public String showCardType(Model model) {
        return "Ecommerce/main";
    }
}
