package ir.webmetric.adrevenue.mapper.stat;

import ir.webmetric.adrevenue.dto.stat.AppCountryPerformanceDto;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryPerformanceQueryResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCountryPerformanceMapper {
    public AppCountryPerformanceDto sqlResultToDto(AppCountryPerformanceQueryResult queryResult){
        return AppCountryPerformanceDto.builder().appId(queryResult.getAppId())
                .countryCode(queryResult.getCountryCode())
                .impressions(queryResult.getImpressions())
                .clicks(queryResult.getClicks())
                .revenue(queryResult.getRevenue()).build();
    }

    public List<AppCountryPerformanceDto> sqlResultToDtoList(List<AppCountryPerformanceQueryResult> queryResult){
        return queryResult.stream().map(this::sqlResultToDto).toList();
    }
}
