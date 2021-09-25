package br.com.supera.game.service;

import br.com.supera.game.repository.CheckoutRepository;
import br.com.supera.game.repository.ProductRepository;
import br.com.supera.game.store.Checkout;
import br.com.supera.game.store.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    private final ProductRepository productRepository;
    private final CheckoutRepository checkoutRepository;

    public CheckoutService(ProductRepository productRepository, CheckoutRepository checkoutRepository) {
        this.productRepository = productRepository;
        this.checkoutRepository = checkoutRepository;
    }


    public Checkout create(List<Long> productIds) {
        Checkout checkout = new Checkout();
        List<Product> products = productRepository.findAllById(productIds);
        checkout.setProducts(products);
        return checkoutRepository.save(checkout);
    }

    public Checkout update(Long id, List<Long> productIds) {
        Checkout checkout = checkoutRepository.getOne(id);
        List<Product> products = productRepository.findAllById(productIds);
        checkout.setProducts(products);
        return checkoutRepository.save(checkout);
    }

}