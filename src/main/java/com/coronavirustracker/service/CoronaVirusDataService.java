package com.coronavirustracker.service;

import java.io.IOException;

public interface CoronaVirusDataService {
    void fetchVirusData() throws IOException, InterruptedException;
}
