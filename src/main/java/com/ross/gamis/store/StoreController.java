package com.ross.gamis.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

//    @GetMapping("/stores")
//    public String showStores(Model model){
//        model.addAttribute("stores", storeService.getStores);
//        return "stores";
//    }
}
