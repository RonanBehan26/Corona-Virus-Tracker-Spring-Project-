package com.ronan.coronavirustracker.controllers;

import com.ronan.coronavirustracker.models.LocationStats;
import com.ronan.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired //this allows us access to the coronaVirusDataService class
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){ //you can put whatever you want in the model and it's accessible via the html
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        //take the stream and sum it up
        //name/attribute and the value(string), you can access it in the html with Thymeleaf
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
