//package com.proyecto.core.controller;
//
//import com.proyecto.core.model.FoodLine;
//import com.proyecto.core.repository.FoodLineRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/foodlines")
//public class FoodLineController {
//
//    @Autowired
//    private FoodLineRepository foodLineRepository;
//
//    @GetMapping(value = "/show")
//    public String showFoodLine(Model model) {
//        model.addAttribute("foodlines", foodLineRepository.findAll());
//        return "FoodLine/showFoodLine";
//    }
//    @GetMapping(value = "/create")
//    public String saveFoodLine(Model model) {
//        model.addAttribute("foodline", new FoodLine());
//        return "FoodLine/addFoodLine";
//    }
//    @PostMapping(value = "/create")
//    public String create(@ModelAttribute @Validated FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "FoodLine/addFoodLine";
//        }
//        if (foodLineRepository.findById(foodLine.getId()).isPresent()) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/foodlines/create";
//        }
//        foodLineRepository.save(foodLine);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Agregado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/foodlines/create";
//    }
//
//    @PostMapping(value = "/delete")
//    public String deleteFoodLine(@ModelAttribute FoodLine foodLine, RedirectAttributes redirectAttrs) {
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Eliminado correctamente")
//                .addFlashAttribute("clase", "warning");
//        foodLineRepository.deleteById(foodLine.getId());
//        return "redirect:/foodlines/show";
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public String showEditForm(@PathVariable int id, Model model) {
//        model.addAttribute("foodline", foodLineRepository.findById(id).orElse(null));
//        return "FoodLine/editFoodLine";
//    }
//
//    @PostMapping(value = "/edit/{id}")
//    public String editFoodLine(@ModelAttribute @Valid FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            if (foodLine.getId() != null) {
//                return "FoodLine/editFoodLine";
//            }
//            return "redirect:/foodlines/show";
//        }
//        FoodLine possibleFoodLine = foodLineRepository.findFirstById(foodLine.getId());
//
//        if (possibleFoodLine != null && !possibleFoodLine.getId().equals(foodLine.getId())) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un foodLine con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/foodlines/create";
//        }
//        foodLineRepository.save(foodLine);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Editado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/foodlines/show";
//    }
//}

package com.proyecto.core.controller;

import com.proyecto.core.model.FoodLine;
import com.proyecto.core.repository.FoodLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/foodlines")
public class FoodLineController {

    @Autowired
    private FoodLineRepository foodLineRepository;

    //////////////////////////////////////////Maintenance//////////////////////////////////////
    @GetMapping(value = "/showMaintenance")
    public String showFoodLinesMaintenance(Model model) {
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "MaintenanceFunctions/FoodLine/showFoodLine";
    }
    @GetMapping(value = "/createMaintenance")
    public String saveFoodlineMaintenance(Model model) {
        model.addAttribute("foodline", new FoodLine());
        return "MaintenanceFunctions/FoodLine/addFoodLine";
    }

    @PostMapping(value = "/createMaintenance")
    public String createFoodlineMaintenance(@ModelAttribute @Validated FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "MaintenanceFunctions/FoodLine/addFoodLine";
        }
        if (foodLineRepository.findById(foodLine.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/foodlines/createMaintenance";
        }
        foodLineRepository.save(foodLine);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/foodlines/createMaintenance";
    }

    @PostMapping(value = "/deleteMaintenance")
    public String deleteFoodLineMaintenance(@ModelAttribute FoodLine foodLine, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        foodLineRepository.deleteById(foodLine.getId());
        return "redirect:/foodlines/showMaintenance";
    }

    @GetMapping(value = "maintenance/edit/{id}")
    public String showEditFormMaintenance(@PathVariable int id, Model model) {
        model.addAttribute("foodline", foodLineRepository.findById(id).orElse(null));
        return "MaintenanceFunctions/FoodLine/editFoodLine";
    }

    @PostMapping(value = "maintenance/edit/{id}")
    public String editFoodLineMaintenance(@ModelAttribute @Valid FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (foodLine.getId() != null) {
                return "MaintenanceFunctions/FoodLine/editFoodLine";
            }
            return "redirect:/foodlines/showMaintenance";
        }
        FoodLine possibleFoodLine = foodLineRepository.findFirstById(foodLine.getId());

        if (possibleFoodLine != null && !possibleFoodLine.getId().equals(foodLine.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un foodLine con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/foodlines/createMaintenance";
        }
        foodLineRepository.save(foodLine);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/foodlines/showMaintenance";
    }


    /////////////////////////////////////////Administrator//////////////////////////////////////

    @GetMapping(value = "/showAdministrator")
    public String foodLinesAdministrator(Model model) {
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "AdministratorFunctions/FoodLine/showFoodLine";
    }

    @GetMapping(value = "/createAdministrator")
    public String saveFoodLineAdministrator(Model model) {
        model.addAttribute("foodline", new FoodLine());
        return "AdministratorFunctions/FoodLine/addFoodLine";
    }

    @PostMapping(value = "/createAdministrator")
    public String createFoodLineAdministrator(@ModelAttribute @Validated FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/FoodLine/addFoodLine";
        }
        if (foodLineRepository.findById(foodLine.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/foodlines/createAdministrator";
        }
        foodLineRepository.save(foodLine);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/foodlines/createAdministrator";
    }
    @PostMapping(value = "/deleteAdministrator")
    public String deleteFoodLineAdministrator(@ModelAttribute FoodLine foodLine, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        foodLineRepository.deleteById(foodLine.getId());
        return "redirect:/foodlines/showAdministrator";
    }

    @GetMapping(value = "administrator/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("foodline", foodLineRepository.findById(id).orElse(null));
        return "AdministratorFunctions/FoodLine/editFoodLine";
    }

    @PostMapping(value = "administrator/edit/{id}")
    public String editFoodLine(@ModelAttribute @Valid FoodLine foodLine, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (foodLine.getId() != null) {
                return "AdministratorFunctions/FoodLine/editFoodLine";
            }
            return "redirect:/foodlines/showAdministrator";
        }
        FoodLine possibleFoodLine = foodLineRepository.findFirstById(foodLine.getId());

        if (possibleFoodLine != null && !possibleFoodLine.getId().equals(foodLine.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un foodLine con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/foodlines/createAdministrator";
        }
        foodLineRepository.save(foodLine);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/foodlines/showAdministrator";
    }
}
