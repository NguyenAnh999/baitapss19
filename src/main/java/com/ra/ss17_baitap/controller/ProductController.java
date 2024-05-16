package com.ra.ss17_baitap.controller;

import com.ra.ss17_baitap.dao.ProductDAOImpl;
import com.ra.ss17_baitap.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductDAOImpl productDAO;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Product> list = productDAO.getProducts();
        model.addAttribute("list", list);
        Product newProduct = new Product();
        model.addAttribute("product", newProduct);
        return "home";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("param1") Integer param1) {
        productDAO.deleteProduct(param1);
        return "redirect:/home";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam("param1") Integer param1, Model model) {
        model.addAttribute("editPro",productDAO.findProduct(param1));
        return "edit";
    }
    @GetMapping("/add")
    public String add( Model model) {
        model.addAttribute("addPro",new Product());
        return "add";
    }
    @PostMapping("/addAfter")
    public String addAfter(@ModelAttribute("addPro") @Valid Product addPro, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("addPro",addPro);
            return "add";
        }else {
        productDAO.addProduct(addPro);
        return "redirect:/home";}
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("editPro") @Valid Product editPro, BindingResult bindingResult, Model model ){
        if(bindingResult.hasErrors()) {
            model.addAttribute("editPro",editPro);
            return "edit";
        }else {
            productDAO.editProduct(editPro);
        return "redirect:/home";}
    }
}
