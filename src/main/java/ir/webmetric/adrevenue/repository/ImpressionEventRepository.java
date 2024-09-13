package ir.webmetric.adrevenue.repository;

import ir.webmetric.adrevenue.entity.ImpressionEvent;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryAdvertiserPerformanceQueryResult;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryPerformanceQueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ImpressionEventRepository extends JpaRepository<ImpressionEvent, UUID> {


    @Query(value ="select im.app_id , im.country_code , count(im.app_id) from impression_event im where im.app_id=:appId and im.country_code=:countryCode " +
            "group by im.app_id, im.country_code" ,nativeQuery = true)
    AppCountryPerformanceQueryResult getImpressionSum(@Param("appId") int appId, @Param("countryCode") String countryCode);

    @Query(value ="select im.app_id appId , im.country_code countryCode , count(im.app_id) impressions from impression_event im  " +
            "group by im.app_id, im.country_code" ,nativeQuery = true)
    List<AppCountryPerformanceQueryResult> getAllImpressionSum();

    @Query(value ="select im.app_id appId , im.country_code countryCode ," +
            " count(im.app_id) impressions,count(ce.revenue) clicks,sum(ce.revenue) revenue from impression_event im " +
            "left join click_event ce on im.id= ce.impression_id " +
            "group by im.app_id, im.country_code" ,nativeQuery = true)
    List<AppCountryPerformanceQueryResult> getAppCountryPerformance();

    @Query(value ="select im.app_id appId , im.country_code countryCode ,im.advertiser_id," +
            " count(im.app_id) impressions,sum(ce.revenue) revenue from impression_event im " +
            "left join click_event ce on im.id= ce.impression_id " +
            "group by im.app_id, im.country_code ,  im.advertiser_id" ,nativeQuery = true)
    List<AppCountryAdvertiserPerformanceQueryResult> calculateAppCountryByAdvertisersPerformance();



}
