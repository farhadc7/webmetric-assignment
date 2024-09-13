package ir.webmetric.adrevenue.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import ir.webmetric.adrevenue.dto.stat.AppCountryPerformanceDto;
import ir.webmetric.adrevenue.dto.stat.WrapperAppCountryByAdvertisersPerformanceDto;
import ir.webmetric.adrevenue.service.stat.StatisticService;
import ir.webmetric.adrevenue.util.FileUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {
    private final StatisticService service;
    private final FileUtil fileUtil;


    public StatController(@Qualifier("statisticServiceWithStreams") StatisticService service, FileUtil fileUtil) {
        this.service = service;
        this.fileUtil = fileUtil;
    }


    @GetMapping("/v1/app-country-performance")
    public List<AppCountryPerformanceDto> appCountryPerformanceStat()   {
        return service.calculateAppCountryPerformance();
    }

    @GetMapping("/v1/app-country-performance-file")
    public ResponseEntity<ByteArrayResource> appCountryPerformanceStatFile() throws JsonProcessingException {
        var res = service.calculateAppCountryPerformance();
        return fileUtil.createJsonResponse("appCountryPerformance",res);
    }

    @GetMapping("/v1/top-advertisers")
    public   List<WrapperAppCountryByAdvertisersPerformanceDto> getTopAdvertisers()   {
        return service.calculateAppCountryByAdvertisersPerformance();
    }

    @GetMapping("/v1/top-advertisers-file")
    public   ResponseEntity<ByteArrayResource> getTopAdvertisersFile() throws JsonProcessingException {
        var res= service.calculateAppCountryByAdvertisersPerformance();
        return fileUtil.createJsonResponse("top-advisors",res);
    }


    @GetMapping("/v1/top-advertisers-check")
    public ResponseEntity<ByteArrayResource> getTopAdvertisersCheck() throws JsonProcessingException {
        var res= service.calculateAppCountryByAdvertisersPerformanceCheck();

        return fileUtil.createJsonResponse( "check-add", res);
    }

    @GetMapping("/v1/top-advertisers-check-file")
    public   ResponseEntity<ByteArrayResource> getTopAdvertisersCheckFile() throws JsonProcessingException {
        var res= service.calculateAppCountryByAdvertisersPerformanceCheck();

        return fileUtil.createJsonResponse( "check-add", res);
    }
}
