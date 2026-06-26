package ru.otus.currencyservice.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String symbol;

    private Integer numericCode;

    private Integer fractionDigits;

    private Boolean active;

}
