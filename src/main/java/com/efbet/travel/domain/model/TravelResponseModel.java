package com.efbet.travel.domain.model;

import java.math.BigDecimal;
import java.util.Set;

public class TravelResponseModel {

    private String username;

    private String startingCountry;

    private int numberOfTours;

    private BigDecimal leftOver;

    private Set<NeighbourResponseModel> visitsNeighbour;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartingCountry() {
        return startingCountry;
    }

    public void setStartingCountry(String startingCountry) {
        this.startingCountry = startingCountry;
    }

    public int getNumberOfTours() {
        return numberOfTours;
    }

    public void setNumberOfTours(int numberOfTours) {
        this.numberOfTours = numberOfTours;
    }

    public BigDecimal getLeftOver() {
        return leftOver;
    }

    public void setLeftOver(BigDecimal leftOver) {
        this.leftOver = leftOver;
    }

    public Set<NeighbourResponseModel> getVisitsNeighbour() {
        return visitsNeighbour;
    }

    public void setVisitsNeighbour(Set<NeighbourResponseModel> visitsNeighbour) {
        this.visitsNeighbour = visitsNeighbour;
    }
}
