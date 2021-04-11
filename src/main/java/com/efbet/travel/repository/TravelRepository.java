package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, String> {
}
