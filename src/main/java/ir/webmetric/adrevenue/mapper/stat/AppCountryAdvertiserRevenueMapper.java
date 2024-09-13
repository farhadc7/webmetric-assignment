package ir.webmetric.adrevenue.mapper.stat;

import ir.webmetric.adrevenue.dto.stat.AppCountryByAdvertisersPerformanceDto;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryAdvertiserPerformanceQueryResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCountryAdvertiserRevenueMapper {
    public AppCountryByAdvertisersPerformanceDto sqlResultToDto(AppCountryAdvertiserPerformanceQueryResult queryResult){
        return AppCountryByAdvertisersPerformanceDto.builder().appId(queryResult.getAppId())
                .countryCode(queryResult.getCountryCode())
                .advertiserId(queryResult.getAdvertiserId())
                .revenue(queryResult.getRevenue())
                .impression(queryResult.getImpressions()).build();
    }

    public List<AppCountryByAdvertisersPerformanceDto> sqlResultToDtoList(List<AppCountryAdvertiserPerformanceQueryResult> queryResult){
        return queryResult.stream().map(this::sqlResultToDto).toList();
    }
}
