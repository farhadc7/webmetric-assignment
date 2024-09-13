package ir.webmetric.adrevenue.repository.queryResult;

public interface AppCountryAdvertiserPerformanceQueryResult {
    int getAppId();
    String getCountryCode();
    int getAdvertiserId();
    int getImpressions();
    int getRevenue();

}
