package com.coronavirustracker.controller;

import com.coronavirustracker.model.LocationStats;
import com.coronavirustracker.service.CoronaVirusDataService;
import com.coronavirustracker.service.CoronaVirusDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoronaVirusTrackerController {

    @Autowired
    CoronaVirusDataServiceImpl coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats ();
        int totalReportedCases = allStats.stream ().mapToInt ( LocationStats::getLatestTotalCases ).sum ();
        model.addAttribute ( "locationStats", allStats);
        model.addAttribute ( "totalReportedCases", totalReportedCases );
        model.addAttribute ( "differenceFromPreviousDay", allStats.stream ().mapToInt ( LocationStats::getDifferenceFromPreviousDay ) );
        return "home";
    }
}
