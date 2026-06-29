package ru.otus.currencyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.currencyservice.aggregate.entity.Country;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}