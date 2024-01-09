package com.ross.gamis.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public String showStores(Model model){
        model.addAttribute("stores",storeService.getStores());
        return "stores/stores";
    }

    @GetMapping("/{id}")
    public String showStore(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("store",storeService.getStore(id));
        return "stores/store";
    }

    @GetMapping("/add")
    public String addStore(Model model){
        model.addAttribute("addStoreForm", new Store());
        return "stores/add";
    }

    @PostMapping("register")
    public String registerStore(@ModelAttribute Store store, Model model){
        storeService.addStore(store);
        model.addAttribute("addStoreForm", new Store());
        model.addAttribute("message", "Successfully added");
        return "stores/add";
    }

}
