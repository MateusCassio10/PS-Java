package br.com.supera.game.controller;

import br.com.supera.game.service.CheckoutService;
import br.com.supera.game.store.Checkout;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public Checkout addProducts(@RequestBody List<Long> productIds) {
        return checkoutService.create(productIds);
    }

    @PutMapping("/{id}")
    public Checkout update(@PathVariable Long id, @RequestBody List<Long> productIds) {
        return checkoutService.update(id, productIds);
    }

    @GetMapping("/{id}")
    public Checkout getOne(@PathVariable Long id,
                           @RequestParam(value = "order", required = false, defaultValue = "") String order) {
        return checkoutService.getOne(id, order);
    }

}
