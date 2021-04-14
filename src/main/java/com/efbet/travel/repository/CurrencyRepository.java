package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,String> {

    Currency findByCurrencyCode(String currencyCode);
}
