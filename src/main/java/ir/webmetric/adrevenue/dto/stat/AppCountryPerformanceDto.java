package ir.webmetric.adrevenue.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppCountryPerformanceDto {
    @JsonProperty("app_id")
    private int appId;
    @JsonProperty("country_code")
    private String countryCode;
    private int clicks;
    @JsonProperty("impressions")
    private int impressions;
    @JsonProperty("revenue")
    private double revenue;

}
