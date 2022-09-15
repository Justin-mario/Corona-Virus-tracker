package com.coronavirustracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LocationStats {

    private String state;

    private String country;

    private int latestTotalCases;

    private int differenceFromPreviousDay;

    @Override
    public String toString() {
        return
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases ;
    }
}
