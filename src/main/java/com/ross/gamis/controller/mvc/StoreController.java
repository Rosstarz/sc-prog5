package com.ross.gamis.controller.mvc;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ross.gamis.domain.Store;
import com.ross.gamis.service.StoreService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    // @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public String showStores(Model model){
        model.addAttribute("stores",storeService.getStores());
        return "store/index";
    }

    @GetMapping("/{id}")
    public String showStore(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("store",storeService.getStore(id));
        return "store/store";
    }

    @GetMapping("/add")
    public String addStore(Model model){
        model.addAttribute("addStoreForm", new Store());
        return "store/add";
    }

    @PostMapping("register")
    public String registerStore(@Valid @ModelAttribute Store store, BindingResult errors, Model model){
        model.addAttribute("addStoreForm", new Store());
        if (errors.hasErrors()) {
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "store/add";
        }
        storeService.addStore(store);
        model.addAttribute("message", "Successfully added");
        return "store/add";
    }
}
