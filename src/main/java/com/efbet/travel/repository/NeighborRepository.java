package com.efbet.travel.repository;

import com.efbet.travel.domain.entity.Country;
import com.efbet.travel.domain.entity.Neighbour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface NeighborRepository extends JpaRepository<Neighbour, String> {

    @Query(value = "SELECT count(neighbours.id) FROM travel.public.neighbours where country_id like :countryId", nativeQuery = true)
    long getNeighbourCountry(String countryId);

    Set<Neighbour> findByCountry(Country country);
}
