//package com.proyecto.core.controller;
//
//import com.proyecto.core.model.User;
//import com.proyecto.core.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping(value = "/findAll")
//    public String findAll(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "";
//    }
//    @GetMapping(value = "/create")
//    public String saveFoodLine(Model model) {
//        model.addAttribute("user", new User());
//        return "addUser";
//    }
//    @PostMapping(value = "/create")
//    public String create(@ModelAttribute @Validated User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "addUser";
//        }
//        if (userRepository.findById(user.getId()).isPresent()) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un usuario con ese Id")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/users/create";
//        }
//        userRepository.save(user);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Agregado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/users/create";
//    }
//
//}

package com.proyecto.core.controller;

import com.proyecto.core.model.Role;
import com.proyecto.core.model.User;
import com.proyecto.core.model.payment.CardType;
import com.proyecto.core.repository.RoleRepository;
import com.proyecto.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    ////////////////////////////////////////// - Roles - /////////////////////////////////////////////////////////
    @RequestMapping(value = "/noRole")
    public String noRolPage(Model model) {
        model.addAttribute("user", new User());
        return "/Roles/noRole";
    }

    @RequestMapping(value = "/administrator")
    public String administratorPage(Model model) {
        model.addAttribute("user", new User());
        return "/Roles/administrator";
    }

    @RequestMapping(value = "/consultation")
    public String consultationPage(Model model) {
        model.addAttribute("user", new User());
        return "/Roles/consultation";
    }

    @RequestMapping(value = "/maintenance")
    public String maintenancePage(Model model) {
        model.addAttribute("user", new User());
        return "/Roles/maintenance";
    }

    @RequestMapping(value = "/security")
    public String securityPage(Model model) {
        model.addAttribute("user", new User());
        return "/Roles/security";
    }

    /////////////////////////////////////////// - Login -  /////////////////////////////////////////////////////
    @RequestMapping(value = "/login")
    public String userLogin(Model model) {
        model.addAttribute("user", new User());
        return "/User/login";
    }

    @PostMapping(value = "/login")
    public String validateUser(@ModelAttribute @Validated User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "/User/register";
        }
        Optional<User> possibleUser = userRepository.userExists(user.getName(), user.getPassword());

        if (possibleUser.isPresent()) {
            if (possibleUser.get().getRole().getName().equals("administrator")) {
                return "/Roles/administrator";
            }
            if (possibleUser.get().getRole().getName().equals("security")) {
                return "/Roles/security";
            }
            if (possibleUser.get().getRole().getName().equals("maintenance")) {
                return "/Roles/maintenance";
            }
            if (possibleUser.get().getRole().getName().equals("consultation")) {
                return "/Roles/consultation";
            }
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Información ingresada es incorrecta")
                    .addFlashAttribute("clase", "warning");
        }

        return "redirect:/users/login";
    }


        @GetMapping(value = "/show")
        public String showUsers (Model model){
            model.addAttribute("users", userRepository.findAll());
            return "User/showUser";
        }

        @RequestMapping(value = "/create")
        public String saveUser (Model model){
            model.addAttribute("user", new User());
            return "/User/addUser";
        }

        @PostMapping(value = "/create")
        public String createUser (@ModelAttribute @Validated User user, BindingResult bindingResult, RedirectAttributes
        redirectAttrs){
            if (bindingResult.hasErrors()) {
                return "/User/addUser";
            }

            User user1 = userRepository.findFirstById(user.getId());

            if (user1 != null && Objects.equals(user1.getName(), user.getName())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El nombre de usuario está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }
            if (user1 !=null && Objects.equals(user1.getMail(), user.getMail())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El correo ya está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }

            if (user.getId() != null && !Objects.equals(user.getPassword(), user.getPasswordConfirmed())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Contraseña de confirmación incorrecta")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }
            if (user.getId() == null || user.getName() == null || user.getPassword() == null || user.getPasswordConfirmed() == null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Ingrese datos correctos")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }

            userRepository.save(user);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "Roles/noRole";

        }


        //////////////////////////////////////- Administrator////////////////////////////////////////////////////////////////

        @GetMapping(value = "/showAdministrator")
        public String showUsersAdministrator (Model model){
            model.addAttribute("users", userRepository.findAll());
            return "AdministratorFunctions/User/showUser";
        }

        @RequestMapping(value = "/createAdministrator")
        public String saveUserAdministrator (Model model){
            model.addAttribute("user", new User());
            return "AdministratorFunctions/User/addUser";
        }

        @PostMapping(value = "/createAdministrator")
        public String createUserAdministrator (@ModelAttribute @Validated User user, BindingResult
        bindingResult, RedirectAttributes redirectAttrs){
            if (bindingResult.hasErrors()) {
                return "redirect:/users/createAdministrator";
            }

            User user1 = userRepository.findFirstById(user.getId());

            if (user1 != null && Objects.equals(user1.getName(), user.getName())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El nombre de usuario está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createAdministrator";
            }
            if (user1 != null && Objects.equals(user1.getMail(), user.getMail())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El correo ya está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createAdministrator";
            }

            if (user.getId() != null && !Objects.equals(user.getPassword(), user.getPasswordConfirmed())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Contraseña de confirmación incorrecta")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createAdministrator";
            }
            if (user.getName() == null || user.getPassword() == null || user.getPasswordConfirmed() == null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "No se permiten espacios vacíos")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createAdministrator";
            }

            userRepository.save(user);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/users/showAdministrator";

        }

        @PostMapping(value = "/deleteAdministrator")
        public String deleteUserAdministrator (@ModelAttribute User user, RedirectAttributes redirectAttrs){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Eliminado correctamente")
                    .addFlashAttribute("clase", "warning");
            userRepository.deleteByName(user.getName());
            return "redirect:/users/showAdministrator";
        }

        @PostMapping(value = "administrator/edit/{id}")
        public String editUserAdministrator (@ModelAttribute @Valid User user, BindingResult
        bindingResult, RedirectAttributes redirectAttrs){
            if (bindingResult.hasErrors()) {
                if (user.getName() != null) {
                    return "AdministratorFunctions/User/editUser";
                }
                return "redirect:/user/showAdministrator";
            }

            User possibleUser = userRepository.findFirstById(user.getId());

            if (!possibleUser.getId().equals(user.getId())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Ya existe un Usuario con ese nombre")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createAdministrator";
            }

            userRepository.save(user);

            redirectAttrs
                    .addFlashAttribute("mensaje", "Editado correctamente")
                    .addFlashAttribute("clase", "success");
            return "redirect:/users/showAdministrator";
        }

        @GetMapping(value = "administrator/edit/{id}")
        public String showEditForm (@PathVariable Integer id, Model model){
            model.addAttribute("user", userRepository.findFirstById(id));
            model.addAttribute("roles", roleRepository.findAll());
            return "AdministratorFunctions/User/editUser";
        }

        //////////////////////////////////////- Security ////////////////////////////////////////////////////////////////
        @GetMapping(value = "/showSecurity")
        public String showUsersSecurity (Model model){
            model.addAttribute("users", userRepository.findAll());
            return "SecurityFunctions/User/showUser";
        }

        @RequestMapping(value = "/createSecurity")
        public String saveUserSecurity (Model model){
            model.addAttribute("user", new User());
            return "SecurityFunctions/User/addUser";
        }

        @PostMapping(value = "/createSecurity")
        public String createUserSecurity (@ModelAttribute @Validated User user, BindingResult
        bindingResult, RedirectAttributes redirectAttrs){
            if (bindingResult.hasErrors()) {
                return "/User/addUser";
            }

            User user1 = userRepository.findFirstById(user.getId());

            if (Objects.equals(user1.getName(), user.getName())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El nombre de usuario está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }
            if (Objects.equals(user1.getMail(), user.getMail())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "El correo ya está en uso")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }

            if (user.getId() != null && !Objects.equals(user.getPassword(), user.getPasswordConfirmed())) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Contraseña de confirmación incorrecta")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/create";
            }
            if (user.getName() == null || user.getPassword() == null || user.getPasswordConfirmed() == null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "No se permiten espacios vacíos")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/users/createSecurity";
            }

            userRepository.save(user);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/users/showSecurity";

        }
    }