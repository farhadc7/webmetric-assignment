package ir.webmetric.adrevenue.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AppCountryByAdvertisersPerformanceDto implements Comparable{
    @JsonProperty("app_id")
    private int appId;
    @JsonProperty("country_code")
    String countryCode;
    @JsonProperty("advertiser_id")
    int advertiserId;
    @JsonProperty("impression")
    int impression;
    @JsonProperty("revenue")
    int revenue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppCountryByAdvertisersPerformanceDto that = (AppCountryByAdvertisersPerformanceDto) o;
        return appId == that.appId && countryCode.equals(that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, countryCode);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof AppCountryByAdvertisersPerformanceDto){
            var oa= (AppCountryByAdvertisersPerformanceDto)o;
            return   (oa.getRevenue()/oa.getImpression()) - (this.getRevenue()/this.getImpression());
        }
        return 0;
    }

}
