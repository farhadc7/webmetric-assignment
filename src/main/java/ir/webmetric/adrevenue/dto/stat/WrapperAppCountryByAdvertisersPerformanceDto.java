package ir.webmetric.adrevenue.dto.stat;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class WrapperAppCountryByAdvertisersPerformanceDto  {
    @JsonProperty("app_id")
    private int appId;
    @JsonProperty("country_code")
    String countryCode;
    @JsonIgnore
    List<AppCountryByAdvertisersPerformanceDto> recommendedAdvertiser = new ArrayList();
    @JsonProperty("recommended_advertiser_ids")
    List<Integer> recommendedAdvertiserIds= new ArrayList();


    public WrapperAppCountryByAdvertisersPerformanceDto(int appId, String countryCode) {
        this.appId = appId;
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperAppCountryByAdvertisersPerformanceDto that = (WrapperAppCountryByAdvertisersPerformanceDto) o;
        return appId == that.appId && countryCode.equals(that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, countryCode);
    }



}
