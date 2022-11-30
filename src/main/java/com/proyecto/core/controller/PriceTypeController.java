//package com.proyecto.core.controller;
//
//import com.proyecto.core.model.PriceType;
//import com.proyecto.core.repository.PriceTypeRepository;
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
//@RequestMapping("/pricetypes")
//public class PriceTypeController {
//
//    @Autowired
//    private PriceTypeRepository priceTypeRepository;
//
//    @GetMapping(value = "/show")
//    public String showPriceType(Model model) {
//        model.addAttribute("priceTypes", priceTypeRepository.findAll());
//
//        return "PriceType/showPriceType";
//    }
//
//    @GetMapping(value = "/create")
//    public String savePriceType(Model model) {
//        model.addAttribute("priceType", new PriceType());
//        return "PriceType/addPriceType";
//    }
//    @PostMapping(value = "/create")
//    public String create(@ModelAttribute @Validated PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "PriceType/addPriceType";
//        }
//        if (priceTypeRepository.findById(priceType.getId()).isPresent()) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un tipo de precio con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/pricetypes/create";
//        }
//        priceTypeRepository.save(priceType);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Agregado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/pricetypes/create";
//    }
//
//    @PostMapping(value = "/delete")
//    public String deletePriceType(@ModelAttribute PriceType priceType, RedirectAttributes redirectAttrs) {
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Eliminado correctamente")
//                .addFlashAttribute("clase", "warning");
//        priceTypeRepository.deleteById(priceType.getId());
//        return "redirect:/pricetypes/show";
//    }
//
//    @PostMapping(value = "/edit/{id}")
//    public String editPriceType(@ModelAttribute @Valid PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            if (priceType.getId() != null) {
//                return "PriceType/editPriceType";
//            }
//            return "redirect:/pricetypes/show";
//        }
//        PriceType possiblePriceType = priceTypeRepository.findFirstById(priceType.getId());
//
//        if (possiblePriceType != null && !possiblePriceType.getId().equals(priceType.getId())) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un priceType con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/pricetypes/create";
//        }
//        priceTypeRepository.save(priceType);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Editado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/pricetypes/show";
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public String showEditForm(@PathVariable Integer id, Model model) {
//        model.addAttribute("priceType", priceTypeRepository.findById(id).orElse(null));
//        return "PriceType/editPriceType";
//    }
//}
package com.proyecto.core.controller;

import com.proyecto.core.model.PriceType;
import com.proyecto.core.repository.PriceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/pricetypes")
public class PriceTypeController {

    @Autowired
    private PriceTypeRepository priceTypeRepository;

    ////////////////////////////////////Maintenance//////////////////////////////////////////////////////////

    @GetMapping(value = "/showMaintenance")
    public String showPriceTypeMaintenance(Model model) {
        model.addAttribute("priceTypes", priceTypeRepository.findAll());

        return "MaintenanceFunctions/PriceType/showPriceType";
    }

    @GetMapping(value = "/createMaintenance")
    public String savePriceTypeMaintenance(Model model) {
        model.addAttribute("priceType", new PriceType());
        return "MaintenanceFunctions/PriceType/addPriceType";
    }
    @PostMapping(value = "/createMaintenance")
    public String createPriceTypeMaintenance(@ModelAttribute @Validated PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "MaintenanceFunctions/PriceType/addPriceType";
        }
        if (priceTypeRepository.findById(priceType.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un tipo de precio con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pricetypes/createMaintenance";
        }
        priceTypeRepository.save(priceType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/pricetypes/createMaintenance";
    }

    @PostMapping(value = "/deleteMaintenance")
    public String deletePriceTypeMaintenance(@ModelAttribute PriceType priceType, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        priceTypeRepository.deleteById(priceType.getId());
        return "redirect:/pricetypes/showMaintenance";
    }

    @PostMapping(value = "maintenance/edit/{id}")
    public String editPriceTypeMaintenance(@ModelAttribute @Valid PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (priceType.getId() != null) {
                return "MaintenanceFunctions/PriceType/editPriceType";
            }
            return "redirect:/pricetypes/showMaintenance";
        }
        PriceType possiblePriceType = priceTypeRepository.findFirstById(priceType.getId());

        if (possiblePriceType != null && !possiblePriceType.getId().equals(priceType.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un priceType con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pricetypes/createMaintenance";
        }
        priceTypeRepository.save(priceType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/pricetypes/showMaintenance";
    }

    @GetMapping(value = "maintenance/edit/{id}")
    public String showEditFormMaintenance(@PathVariable Integer id, Model model) {
        model.addAttribute("priceType", priceTypeRepository.findById(id).orElse(null));
        return "MaintenanceFunctions/PriceType/editPriceType";
    }

    ////////////////////////////////////Administrator//////////////////////////////////////////////////////////
    @GetMapping(value = "/showAdministrator")
    public String showPriceTypeAdministrator(Model model) {
        model.addAttribute("priceTypes", priceTypeRepository.findAll());

        return "AdministratorFunctions/PriceType/showPriceType";
    }

    @GetMapping(value = "/createAdministrator")
    public String savePriceTypeAdministrator(Model model) {
        model.addAttribute("priceType", new PriceType());
        return "AdministratorFunctions/PriceType/addPriceType";
    }
    @PostMapping(value = "/createAdministrator")
    public String createPriceTypeAdministrator(@ModelAttribute @Validated PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/PriceType/addPriceType";
        }
        if (priceTypeRepository.findById(priceType.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un tipo de precio con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pricetypes/createAdministrator";
        }
        priceTypeRepository.save(priceType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/pricetypes/createAdministrator";
    }

    @PostMapping(value = "/deleteAdministrator")
    public String deletePriceTypeAdministrator(@ModelAttribute PriceType priceType, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        priceTypeRepository.deleteById(priceType.getId());
        return "redirect:/pricetypes/showAdministrator";
    }

    @PostMapping(value = "administrator/edit/{id}")
    public String editPriceTypeAdministrator(@ModelAttribute @Valid PriceType priceType, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (priceType.getId() != null) {
                return "AdministratorFunctions/PriceType/editPriceType";
            }
            return "redirect:/pricetypes/showAdministrator";
        }
        PriceType possiblePriceType = priceTypeRepository.findFirstById(priceType.getId());

        if (possiblePriceType != null && !possiblePriceType.getId().equals(priceType.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un priceType con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/pricetypes/createAdministrator";
        }
        priceTypeRepository.save(priceType);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/pricetypes/showAdministrator";
    }

    @GetMapping(value = "administrator/edit/{id}")
    public String showEditFormAdministrator(@PathVariable Integer id, Model model) {
        model.addAttribute("priceType", priceTypeRepository.findById(id).orElse(null));
        return "AdministratorFunctions/PriceType/editPriceType";
    }
}
