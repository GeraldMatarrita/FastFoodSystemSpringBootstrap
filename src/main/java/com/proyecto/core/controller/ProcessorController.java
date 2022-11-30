package com.proyecto.core.controller;

import com.proyecto.core.model.payment.ProcessorPayment;
import com.proyecto.core.repository.CardTypeRepository;
import com.proyecto.core.repository.PaymentTypeRepository;
import com.proyecto.core.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/processors")
public class ProcessorController {

    @Autowired
    private ProcessorRepository processorRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private CardTypeRepository cardTypeRepository;

    @GetMapping(value = "/show")
    public String showProcessor(Model model) {
        model.addAttribute("processors", processorRepository.findAll());

        return "AdministratorFunctions/Processor/showProcessor";
    }

    @GetMapping(value = "/create")
    public String saveProcessor(Model model) {
        model.addAttribute("processor", new ProcessorPayment());
        model.addAttribute("paymentTypes", paymentTypeRepository.findAll());
        model.addAttribute("cardTypes", cardTypeRepository.findAll());
        return "AdministratorFunctions/Processor/addProcessor";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute @Validated ProcessorPayment processor, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/Processor/addProcessor";
        }
        if (processorRepository.findById(processor.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        if (processorRepository.theresActiveCardProcessor().isPresent() && processor.getType().getName().equals("Tarjeta de Crédito o Débito") && processor.getState().equals(true)){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Sólo puede haber un procesador de Tarjetas activo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        if (processorRepository.theresActiveCheckProcessor().isPresent() && processor.getType().getName().equals("Cheque Electrónico") && processor.getState().equals(true)){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Sólo puede haber un procesador de Cheques Electrónicos activo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        if (processorRepository.theresCashProcessor().isPresent() && processor.getType().getName().equals("Efectivo")){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Sólo puede haber un procesador de Efectivo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        processorRepository.save(processor);

        if (processor.getType().getName().equals("Tarjeta de Crédito o Débito")) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente. Por favor, edite el procesador para agregar tipos de tarjeta aceptados")
                    .addFlashAttribute("clase", "success");
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");
        }
        return "redirect:AdministratorFunctions/processors/create";
    }

    @PostMapping(value = "/delete")
    public String deleteProcessor(@ModelAttribute ProcessorPayment processor, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        processorRepository.deleteById(processor.getId());
        return "redirect:/processors/show";
    }

    @PostMapping(value = "/edit/{id}")
    public String editProcessor(@ModelAttribute @Valid ProcessorPayment processor, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        processor.setId(processor.getId().substring(0, processor.getId().indexOf(",")));
        if (bindingResult.hasErrors()) {
            if (processor.getId() != null) {
                return "Processor/editProcessor";
            }
            return "redirect:/processors/show";
        }
        ProcessorPayment possibleProcessor = processorRepository.findFirstById(processor.getId());

        if (possibleProcessor != null && !possibleProcessor.getId().equals(processor.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un processor con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        if (processorRepository.theresActiveCardProcessor().isPresent() &&
                !processorRepository.theresActiveCardProcessor().get().getId().equals(processor.getId()) &&
                processor.getState().equals(true)) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Sólo puede haber un procesador de Tarjetas activo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        if (processorRepository.theresCashProcessor().isPresent() && processor.getType().getName().equals("Efectivo")) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Sólo puede haber un procesador de Efectivo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/processors/create";
        }
        processorRepository.save(processor);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/processors/show";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("processor", processorRepository.findById(id).orElse(null));
        model.addAttribute("paymentTypes", paymentTypeRepository.findAll());
        model.addAttribute("cardTypes", cardTypeRepository.findAll());
        return "AdministratorFunctions/Processor/editProcessor";
    }
}
