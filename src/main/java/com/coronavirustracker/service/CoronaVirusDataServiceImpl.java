package com.coronavirustracker.service;

import com.coronavirustracker.model.LocationStats;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class CoronaVirusDataServiceImpl implements CoronaVirusDataService{
    private List<LocationStats> allStats = new ArrayList<> ();

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *", zone = "UTC")  //second, minute, hour, day of month, month, day(s) of week
    @Override
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<> ();
        HttpClient client = HttpClient.newHttpClient ();
        HttpRequest request = HttpRequest.newBuilder()
                .uri ( URI.create ( VIRUS_DATA_URL ) )
                .build();
        HttpResponse<String> response = client.send ( request, HttpResponse.BodyHandlers.ofString () );

        StringReader csvBodyReader = new StringReader ( response.body () );
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats ();
            locationStats.setState ( record.get("Province/State") );
            locationStats.setCountry (record.get("Country/Region"));
            int latestCases = Integer.parseInt ( record.get(record.size () - 1) );
            int previousDayCases = Integer.parseInt ( record.get(record.size () - 2) );
            locationStats.setLatestTotalCases ( latestCases);
            locationStats.setDifferenceFromPreviousDay ( latestCases - previousDayCases );
            newStats.add ( locationStats );


        }
           allStats = newStats;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }
}
