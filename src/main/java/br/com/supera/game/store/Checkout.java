package br.com.supera.game.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal Delivery;

    private BigDecimal subTotal;

    private BigDecimal total;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;



}
