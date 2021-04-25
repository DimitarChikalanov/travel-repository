package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Travel;
import com.efbet.travel.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, String> {

    List<Travel>findAllByUser(User user);
}
