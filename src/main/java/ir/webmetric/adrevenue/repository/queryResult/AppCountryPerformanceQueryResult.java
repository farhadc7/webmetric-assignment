package ir.webmetric.adrevenue.repository.queryResult;

public interface AppCountryPerformanceQueryResult {
    Integer getAppId();
    String getCountryCode();
    Integer getImpressions();
    Double getRevenue();
    Integer getClicks();

}
