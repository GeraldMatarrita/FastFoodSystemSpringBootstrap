package com.proyecto.core.controller;


import com.proyecto.core.model.*;
import com.proyecto.core.repository.FoodLineRepository;
import com.proyecto.core.repository.PriceTypeRepository;
import com.proyecto.core.repository.ProductPriceRepository;
import com.proyecto.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FoodLineRepository foodLineRepository;

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    PriceTypeRepository priceTypeRepository;

    //////////////////////////////////////////Maintenance//////////////////////////////////////

    @GetMapping(value = "/showMaintenance")
    public String showProductMaintenance(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "MaintenanceFunctions/Product/showProduct";
    }
    @GetMapping(value = "/createMaintenance")
    public String saveProductMaintenance(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "MaintenanceFunctions/Product/addProduct";
    }
    @PostMapping(value = "/createMaintenance")
    public String createMaintenanceMaintenance(@ModelAttribute @Validated Product product,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttrs,
                         @RequestParam (name = "archivo")MultipartFile archivo) {
        if (bindingResult.hasErrors()) {
            return "MaintenanceFunctions/Product/addProduct";
        }
        if (productRepository.findById(product.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/products/createMaintenance";
        }

        String ruta = "./src/main/resources/images";
        int index = Objects.requireNonNull(archivo.getOriginalFilename()).indexOf(".");
        String extension;
        extension = "." + archivo.getOriginalFilename().substring(index+1);
        String nombreFoto = Calendar.getInstance().getTimeInMillis() + extension;
        Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);

        try {
            Files.write(rutaAbsoluta, archivo.getBytes());
            product.setImage(nombreFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productRepository.save(product);
        List<PriceType> pricesTypes = priceTypeRepository.findAll();
        for (PriceType priceType: pricesTypes
             ) {
            ProductPrice newDefaultPrice = new ProductPrice(new ProductPriceKey(product.getId(), priceType.getId()), product, priceType, 0);
            productPriceRepository.save(newDefaultPrice);
        }
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/createMaintenance/";
    }

    @GetMapping(value = "maintenance/addprices/{id}")
    public String addPricesMaintenance(Model model, @ModelAttribute @Valid Product product) {
        model.addAttribute("product", productRepository.findFirstById(product.getId()));
        model.addAttribute("pricesTypes", priceTypeRepository.findAll());
        model.addAttribute("prices", productPriceRepository.findPricesByProductId(product.getId()));
        model.addAttribute("dataStorage", new ProductPriceType());
        return "MaintenanceFunctions/Product/addPrices";
    }

    @GetMapping(value = "maintenance/editprices/{id}")
    public String editPricesMaintenance(Model model, @ModelAttribute @Valid Product product) {
        Product productAt = productRepository.findFirstById(product.getId());
        model.addAttribute("idProduct", product.getId());
        model.addAttribute("pricesTypes", priceTypeRepository.findAll());
        model.addAttribute("prices", productPriceRepository.findPricesByProductId(product.getId()));
        model.addAttribute("dataStorage", new ProductPriceType());
        return "MaintenanceFunctions/Product/editPrices";
    }

    @PostMapping(value = "maintenance/editprices/{id}")
    public String editPricesMaintenance(@ModelAttribute @Valid ProductPriceType dataStorage, RedirectAttributes redirectAttrs) {

        Product product = productRepository.findFirstById(dataStorage.getProductID());
        PriceType priceType = priceTypeRepository.findFirstById(dataStorage.getPriceTypeID());

        ProductPrice productPrice = new ProductPrice(
                new ProductPriceKey(dataStorage.getProductID(), dataStorage.getPriceTypeID()),
                product, priceType, dataStorage.getPrice()
        );

        productPriceRepository.save(productPrice);

        redirectAttrs
                .addFlashAttribute("mensaje", "Precio actualizado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/maintenance/addprices/" + dataStorage.getProductID();
    }

    @PostMapping(value = "maintenance/deleteprice/{id}")
    public String deletePriceMaintenance(@ModelAttribute ProductPriceType dataStorage, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Product product = productRepository.findFirstById(dataStorage.getProductID());
        PriceType priceType = priceTypeRepository.findFirstById(dataStorage.getPriceTypeID());

        ProductPrice productPrice = new ProductPrice(
                new ProductPriceKey(dataStorage.getProductID(), dataStorage.getPriceTypeID()),
                product, priceType, 0
        );

        productPriceRepository.save(productPrice);

        redirectAttrs
                .addFlashAttribute("mensaje", "Precio actualizado correctamente")
                .addFlashAttribute("clase", "success");

        return "redirect:/products/maintenance/addprices/" + dataStorage.getProductID();
    }

    @PostMapping(value = "/deleteMaintenance")
    public String deleteProductMaintenance(@ModelAttribute Product product, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productPriceRepository.deleteProductPricesByProductId(product.getId());
        productRepository.deleteById(product.getId());
        return "redirect:/products/showMaintenance";
    }

    @PostMapping(value = "maintenance/edit/{id}")
    public String editProductMaintenance(@ModelAttribute @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (product.getId() != null) {
                return "MaintenanceFunctions/Product/editProduct";
            }
            return "redirect:/products/showMaintenance";
        }
        Product possibleProduct = productRepository.findFirstById(product.getId());

        if (possibleProduct != null && !possibleProduct.getId().equals(product.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un product con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/products/createMaintenance";
        }
        productRepository.save(product);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/showMaintenance";
    }

    @GetMapping(value = "maintenance/edit/{id}")
    public String showEditFormMaintenance(@PathVariable int id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "MaintenanceFunctions/Product/editProduct";
    }

    /////////////////////////////////////////Administrator//////////////////////////////////////

    @GetMapping(value = "/showAdministrator")
    public String showProductAdministrator(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "AdministratorFunctions/Product/showProduct";
    }
    @GetMapping(value = "/createAdministrator")
    public String saveProductAdministrator(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "AdministratorFunctions/Product/addProduct";
    }
    @PostMapping(value = "/createAdministrator")
    public String createAdministrator(@ModelAttribute @Validated Product product,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttrs,
                         @RequestParam (name = "archivo")MultipartFile archivo) {
        if (bindingResult.hasErrors()) {
            return "AdministratorFunctions/Product/addProduct";
        }
        if (productRepository.findById(product.getId()).isPresent()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/products/create";
        }

        String ruta = "./src/main/resources/images";
        int index = Objects.requireNonNull(archivo.getOriginalFilename()).indexOf(".");
        String extension;
        extension = "." + archivo.getOriginalFilename().substring(index+1);
        String nombreFoto = Calendar.getInstance().getTimeInMillis() + extension;
        Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);

        try {
            Files.write(rutaAbsoluta, archivo.getBytes());
            product.setImage(nombreFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productRepository.save(product);
        List<PriceType> pricesTypes = priceTypeRepository.findAll();
        for (PriceType priceType: pricesTypes
        ) {
            ProductPrice newDefaultPrice = new ProductPrice(new ProductPriceKey(product.getId(), priceType.getId()), product, priceType, 0);
            productPriceRepository.save(newDefaultPrice);
        }
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/create/";
    }

    @GetMapping(value = "administrator/addprices/{id}")
    public String addPricesAdministrator(Model model, @ModelAttribute @Valid Product product) {
        model.addAttribute("product", productRepository.findFirstById(product.getId()));
        model.addAttribute("pricesTypes", priceTypeRepository.findAll());
        model.addAttribute("prices", productPriceRepository.findPricesByProductId(product.getId()));
        model.addAttribute("dataStorage", new ProductPriceType());
        return "AdministratorFunctions/Product/addPrices";
    }

    @GetMapping(value = "administrator/editprices/{id}")
    public String editPricesAdministrator(Model model, @ModelAttribute @Valid Product product) {
        Product productAt = productRepository.findFirstById(product.getId());
        model.addAttribute("idProduct", product.getId());
        model.addAttribute("pricesTypes", priceTypeRepository.findAll());
        model.addAttribute("prices", productPriceRepository.findPricesByProductId(product.getId()));
        model.addAttribute("dataStorage", new ProductPriceType());
        return "AdministratorFunctions/Product/editPrices";
    }

    @PostMapping(value = "administrator/editprices/{id}")
    public String editPricesAdministrator(@ModelAttribute @Valid ProductPriceType dataStorage, RedirectAttributes redirectAttrs) {

        Product product = productRepository.findFirstById(dataStorage.getProductID());
        PriceType priceType = priceTypeRepository.findFirstById(dataStorage.getPriceTypeID());

        ProductPrice productPrice = new ProductPrice(
                new ProductPriceKey(dataStorage.getProductID(), dataStorage.getPriceTypeID()),
                product, priceType, dataStorage.getPrice()
        );

        productPriceRepository.save(productPrice);

        redirectAttrs
                .addFlashAttribute("mensaje", "Precio actualizado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/administrator/addprices/" + dataStorage.getProductID();
    }

    @PostMapping(value = "administrator/deleteprice/{id}")
    public String deletePriceAdministrator(@ModelAttribute ProductPriceType dataStorage, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Product product = productRepository.findFirstById(dataStorage.getProductID());
        PriceType priceType = priceTypeRepository.findFirstById(dataStorage.getPriceTypeID());

        ProductPrice productPrice = new ProductPrice(
                new ProductPriceKey(dataStorage.getProductID(), dataStorage.getPriceTypeID()),
                product, priceType, 0
        );

        productPriceRepository.save(productPrice);

        redirectAttrs
                .addFlashAttribute("mensaje", "Precio actualizado correctamente")
                .addFlashAttribute("clase", "success");

        return "redirect:/products/administrator/addprices/" + dataStorage.getProductID();
    }

    @PostMapping(value = "/deleteAdministrator")
    public String deleteProduct(@ModelAttribute Product product, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productPriceRepository.deleteProductPricesByProductId(product.getId());
        productRepository.deleteById(product.getId());
        return "redirect:/products/showAdministrator";
    }

    @PostMapping(value = "administrator/edit/{id}")
    public String editProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (product.getId() != null) {
                return "AdministratorFunctions/Product/editProduct";
            }
            return "redirect:/products/showAdministrator";
        }
        Product possibleProduct = productRepository.findFirstById(product.getId());

        if (possibleProduct != null && !possibleProduct.getId().equals(product.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un product con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/products/createAdministrator";
        }
        productRepository.save(product);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/products/showAdministrator";
    }

    @GetMapping(value = "administrator/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        model.addAttribute("foodlines", foodLineRepository.findAll());
        return "AdministratorFunctions/Product/editProduct";
    }

}
