package ir.webmetric.adrevenue.service.stat;

import ir.webmetric.adrevenue.dto.stat.AppCountryByAdvertisersPerformanceDto;
import ir.webmetric.adrevenue.dto.stat.AppCountryPerformanceDto;
import ir.webmetric.adrevenue.dto.stat.WrapperAppCountryByAdvertisersPerformanceDto;
import ir.webmetric.adrevenue.mapper.stat.AppCountryAdvertiserRevenueMapper;
import ir.webmetric.adrevenue.mapper.stat.AppCountryPerformanceMapper;
import ir.webmetric.adrevenue.service.ImpressionEventService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticServiceWithStreams implements StatisticService {
    private final ImpressionEventService impressionEventService;
    private final AppCountryAdvertiserRevenueMapper appCountryAdvertiserRevenueMapper;
    private final AppCountryPerformanceMapper appCountryPerformanceMapper;

    public StatisticServiceWithStreams(ImpressionEventService impressionEventService,
                                       AppCountryAdvertiserRevenueMapper mapper, AppCountryPerformanceMapper appCountryPerformanceMapper) {
        this.impressionEventService = impressionEventService;
        this.appCountryAdvertiserRevenueMapper = mapper;
        this.appCountryPerformanceMapper = appCountryPerformanceMapper;
    }

    public List<AppCountryPerformanceDto> calculateAppCountryPerformance(){
       var revRes= impressionEventService.calculateAppCountryPerformance();
       return appCountryPerformanceMapper.sqlResultToDtoList(revRes);
    }

    public List<WrapperAppCountryByAdvertisersPerformanceDto> calculateAppCountryByAdvertisersPerformance(){
        var res= impressionEventService.calculateAppCountryByAdvertisersPerformance();
        List<AppCountryByAdvertisersPerformanceDto> dtoRes= appCountryAdvertiserRevenueMapper.sqlResultToDtoList(res);
        Set<AppCountryByAdvertisersPerformanceDto> uniqSet= new HashSet<>(dtoRes);
        List<WrapperAppCountryByAdvertisersPerformanceDto> sepList= new ArrayList<>();

        int i=0;
        for (var u:uniqSet) {
            sepList.add(new WrapperAppCountryByAdvertisersPerformanceDto(u.getAppId(),u.getCountryCode()));
            for (var d:dtoRes) {
                if(u.equals(d)){
                    sepList.get(i).getRecommendedAdvertiser().add(d);
                }
            }
            i++;
        }


        sepList.stream().peek(s->sortList(s)).forEach(s->{
            var ads=s.getRecommendedAdvertiser().stream().map(a->a.getAdvertiserId()).toList();
            s.setRecommendedAdvertiserIds(ads);
        });


        return sepList;
    }

    public   List<List<AppCountryByAdvertisersPerformanceDto>> calculateAppCountryByAdvertisersPerformanceCheck(){
        var res= impressionEventService.calculateAppCountryByAdvertisersPerformance();
        List<AppCountryByAdvertisersPerformanceDto> dtoRes= appCountryAdvertiserRevenueMapper.sqlResultToDtoList(res);

        Set<AppCountryByAdvertisersPerformanceDto> uniqSet= new HashSet<>(dtoRes);

        List<List<AppCountryByAdvertisersPerformanceDto>> seperatedList= new ArrayList<>();

        int i=0;
        for (var u:uniqSet) {
            seperatedList.add(new ArrayList<>());
            for (var d:dtoRes) {
                if(u.equals(d)){
                    seperatedList.get(i).add(d);
                }
            }
            i++;
        }

        List<List<AppCountryByAdvertisersPerformanceDto>> finalList;
        finalList =seperatedList.stream().peek(Collections::sort).map(l->l.stream().limit(5).toList()).toList();
        return finalList;
    }


    private void sortList(WrapperAppCountryByAdvertisersPerformanceDto w) {
        Collections.sort(w.getRecommendedAdvertiser());
        w.setRecommendedAdvertiser(w.getRecommendedAdvertiser().stream().limit(5).toList());

    }
}
