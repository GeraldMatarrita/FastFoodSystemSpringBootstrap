//package com.proyecto.core.controller;
//
//import com.proyecto.core.model.payment.DiscountTicket;
//import com.proyecto.core.repository.DiscountTicketRepository;
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
//@RequestMapping("/discounttickets")
//public class DiscountTicketController {
//
//    @Autowired
//    private DiscountTicketRepository discountTicketRepository;
//
//    @GetMapping(value = "/show")
//    public String showDiscountTicket(Model model) {
//        model.addAttribute("discounttickets", discountTicketRepository.findAll());
//        return "DiscountTicket/showDiscountTicket";
//    }
//    @GetMapping(value = "/create")
//    public String saveDiscountTicket(Model model) {
//        model.addAttribute("discountticket", new DiscountTicket());
//        return "DiscountTicket/addDiscountTicket";
//    }
//    @PostMapping(value = "/create")
//    public String create(@ModelAttribute @Validated DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "DiscountTicket/addDiscountTicket";
//        }
//        if (discountTicketRepository.findById(discountTicket.getId()).isPresent()) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/discounttickets/create";
//        }
//        discountTicketRepository.save(discountTicket);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Agregado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/discounttickets/create";
//    }
//
//    @PostMapping(value = "/delete")
//    public String deleteDiscountTicket(@ModelAttribute DiscountTicket discountTicket, RedirectAttributes redirectAttrs) {
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Eliminado correctamente")
//                .addFlashAttribute("clase", "warning");
//        discountTicketRepository.deleteById(discountTicket.getId());
//        return "redirect:/discounttickets/show";
//    }
//
//    @PostMapping(value = "/edit/{id}")
//    public String editDiscountTicket(@ModelAttribute @Valid DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        discountTicket.setId(discountTicket.getId().substring(0, discountTicket.getId().indexOf(",")));
//        if (bindingResult.hasErrors()) {
//            if (discountTicket.getId() != null) {
//                return "DiscountTicket/editDiscountTicket";
//            }
//            return "redirect:/discounttickets/show";
//        }
//        DiscountTicket possibleDiscountTicket = discountTicketRepository.findFirstById(discountTicket.getId());
//
//        if (possibleDiscountTicket != null && !possibleDiscountTicket.getId().equals(discountTicket.getId())) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un discountticket con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/discounttickets/create";
//        }
//        discountTicketRepository.save(discountTicket);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Editado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/discounttickets/show";
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public String showEditForm(@PathVariable String id, Model model) {
//        model.addAttribute("discountticket", discountTicketRepository.findById(id).orElse(null));
//        return "DiscountTicket/editDiscountTicket";
//    }
//
//}

package com.proyecto.core.controller;

import com.proyecto.core.model.payment.DiscountTicket;
import com.proyecto.core.repository.DiscountTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/discounttickets")
public class DiscountTicketController {

    @Autowired
    private DiscountTicketRepository discountTicketRepository;

    //////////////////////////////////////////Maintenance//////////////////////////////////////
    @GetMapping(value = "/showMaintenance")
    public String showDiscountTicketMaintenance(Model model) {
        model.addAttribute("discounttickets", discountTicketRepository.findAll());
        return "MaintenanceFunctions/DiscountTicket/showDiscountTicket";
    }
    @GetMapping(value = "/createMaintenance")
    public String saveDiscountTicketMaintenance(Model model) {
        model.addAttribute("discountticket", new DiscountTicket());
        return "MaintenanceFunctions/DiscountTicket/addDiscountTicket";
    }
    @PostMapping(value = "/createMaintenance")
    public String createDiscountTicketMaintenance(@ModelAttribute @Validated DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "MaintenanceFunctions/DiscountTicket/addDiscountTicket";
        }
        if (discountTicketRepository.findById(discountTicket.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/discounttickets/createMaintenance";
        }
        discountTicketRepository.save(discountTicket);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/discounttickets/createMaintenance";
    }

    @PostMapping(value = "/deleteMaintenance")
    public String deleteDiscountTicketMaintenance(@ModelAttribute DiscountTicket discountTicket, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        discountTicketRepository.deleteById(discountTicket.getId());
        return "redirect:/discounttickets/showMaintenance";
    }

    @PostMapping(value = "maintenance/edit/{id}")
    public String editDiscountTicketMaintenance(@ModelAttribute @Valid DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        discountTicket.setId(discountTicket.getId().substring(0, discountTicket.getId().indexOf(",")));
        if (bindingResult.hasErrors()) {
            if (discountTicket.getId() != null) {
                return "MaintenanceFunctions/DiscountTicket/editDiscountTicket";
            }
            return "redirect:/discounttickets/showMaintenance";
        }
        DiscountTicket possibleDiscountTicket = discountTicketRepository.findFirstById(discountTicket.getId());

        if (possibleDiscountTicket != null && !possibleDiscountTicket.getId().equals(discountTicket.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un discountticket con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/discounttickets/createMaintenance";
        }
        discountTicketRepository.save(discountTicket);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/discounttickets/showMaintenance";
    }

    @GetMapping(value = "maintenance/edit/{id}")
    public String showEditFormMaintenance(@PathVariable String id, Model model) {
        model.addAttribute("discountticket", discountTicketRepository.findById(id).orElse(null));
        return "MaintenanceFunctions/DiscountTicket/editDiscountTicket";
    }

    /////////////////////////////////////////Administrator//////////////////////////////////////

    @GetMapping(value = "/showAdministrator")
    public String showDiscountTicketAdministrator(Model model) {
        model.addAttribute("discounttickets", discountTicketRepository.findAll());
        return "AdministratorFunctions/DiscountTicket/showDiscountTicket";
    }
    @GetMapping(value = "/createAdministrator")
    public String saveDiscountTicketAdministrator(Model model) {
        model.addAttribute("discountticket", new DiscountTicket());
        return "AdministratorFunctions/DiscountTicket/addDiscountTicket";
    }
    @PostMapping(value = "/createAdministrator")
    public String create(@ModelAttribute @Validated DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/DiscountTicket/addDiscountTicket";
        }
        if (discountTicketRepository.findById(discountTicket.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/discounttickets/createAdministrator";
        }
        discountTicketRepository.save(discountTicket);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/discounttickets/createAdministrator";
    }

    @PostMapping(value = "/deleteAdministrator")
    public String deleteDiscountTicket(@ModelAttribute DiscountTicket discountTicket, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        discountTicketRepository.deleteById(discountTicket.getId());
        return "redirect:/discounttickets/showAdministrator";
    }

    @PostMapping(value = "administrator/edit/{id}")
    public String editDiscountTicket(@ModelAttribute @Valid DiscountTicket discountTicket, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        discountTicket.setId(discountTicket.getId().substring(0, discountTicket.getId().indexOf(",")));
        if (bindingResult.hasErrors()) {
            if (discountTicket.getId() != null) {
                return "Administrator/DiscountTicket/editDiscountTicket";
            }
            return "redirect:/discounttickets/showAdministrator";
        }
        DiscountTicket possibleDiscountTicket = discountTicketRepository.findFirstById(discountTicket.getId());

        if (possibleDiscountTicket != null && !possibleDiscountTicket.getId().equals(discountTicket.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un discountticket con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/discounttickets/createAdministrator";
        }
        discountTicketRepository.save(discountTicket);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/discounttickets/showAdministrator";
    }

    @GetMapping(value = "administrator/edit/{id}")
    public String showEditFormAdministrator(@PathVariable String id, Model model) {
        model.addAttribute("discountticket", discountTicketRepository.findById(id).orElse(null));
        return "AdministratorFunctions/DiscountTicket/editDiscountTicket";
    }
}