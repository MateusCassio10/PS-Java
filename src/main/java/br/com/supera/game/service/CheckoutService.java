package br.com.supera.game.service;

import br.com.supera.game.repository.CheckoutRepository;
import br.com.supera.game.repository.ProductRepository;
import br.com.supera.game.store.Checkout;
import br.com.supera.game.store.Product;
import org.springframework.stereotype.Service;

import java.lang.invoke.SwitchPoint;
import java.math.BigDecimal;
import java.util.Comparator;
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
        updateValues(checkout, productIds);
        return checkoutRepository.save(checkout);
    }

    public Checkout update(Long id, List<Long> productIds) {
        Checkout checkout = checkoutRepository.getOne(id);
        updateValues(checkout, productIds);
        return checkoutRepository.save(checkout);
    }

    private void updateValues(Checkout checkout, List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        checkout.setProducts(products);

        BigDecimal price = BigDecimal.ZERO;
        for (Product product : products) {
            price = price.add(product.getPrice());
        }
        checkout.setSubTotal(price);

        checkout.setDelivery(BigDecimal.ZERO);
        if (price.compareTo(BigDecimal.valueOf(250)) < 0) {
            checkout.setDelivery(BigDecimal.valueOf(products.size() * 10L));
        }

        checkout.setTotal(checkout.getSubTotal().add(checkout.getDelivery()));
    }


    public Checkout getOne(Long id, String order) {
        Checkout checkout = checkoutRepository.findById(id).get();

        if (!order.equals("")) {
            List<Product> products = checkout.getProducts();

            switch (order) {
                case "score":
                    products.sort(Comparator.comparing(Product::getScore));
                    break;
                case "price":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "name":
                    products.sort(Comparator.comparing(Product::getName));
                    break;
            }

        }
        return checkout;
    }
}