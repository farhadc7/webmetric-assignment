package ir.webmetric.adrevenue.service.stat;

import ir.webmetric.adrevenue.dto.stat.AppCountryByAdvertisersPerformanceDto;
import ir.webmetric.adrevenue.dto.stat.AppCountryPerformanceDto;
import ir.webmetric.adrevenue.dto.stat.WrapperAppCountryByAdvertisersPerformanceDto;

import java.util.*;

public interface StatisticService {

    List<AppCountryPerformanceDto> calculateAppCountryPerformance();

    List<WrapperAppCountryByAdvertisersPerformanceDto> calculateAppCountryByAdvertisersPerformance();

    List<List<AppCountryByAdvertisersPerformanceDto>> calculateAppCountryByAdvertisersPerformanceCheck();
}
