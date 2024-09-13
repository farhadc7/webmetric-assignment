package ir.webmetric.adrevenue.service;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.webmetric.adrevenue.dto.ClickEventDto;
import ir.webmetric.adrevenue.dto.ImpressionEventDto;
import ir.webmetric.adrevenue.entity.ClickEvent;
import ir.webmetric.adrevenue.entity.ImpressionEvent;
import ir.webmetric.adrevenue.mapper.ImpressionMapper;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryAdvertiserPerformanceQueryResult;
import ir.webmetric.adrevenue.repository.ImpressionEventRepository;
import ir.webmetric.adrevenue.repository.queryResult.AppCountryPerformanceQueryResult;
import ir.webmetric.adrevenue.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImpressionEventService {
    private final FileUtil<ImpressionEventDto> fileUtil;
    private final ImpressionEventRepository repository;
    private final ImpressionMapper mapper;


    public ImpressionEventService(FileUtil<ImpressionEventDto> fileUtil, ImpressionEventRepository repository, ImpressionMapper mapper) {
        this.fileUtil = fileUtil;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ImpressionEvent>  save(MultipartFile file) throws IOException {
        fileUtil.validateFile(file);
        List<ImpressionEventDto> list;
        list = fileUtil.getMapper().readValue(file.getInputStream(), new TypeReference<>() {
        });
        return asyncSaveAll(list);
    }

    public List<ImpressionEvent> asyncSaveAll(List<ImpressionEventDto> dtos) {
        return dtos.parallelStream().map(this::validatedSave).toList();
    }

    public ImpressionEvent save(ImpressionEventDto dto) {
        var ent = mapper.dtoToEntity(dto);
        return repository.save(ent);
    }

    private ImpressionEvent validatedSave(ImpressionEventDto dto){
        validate(dto);
        return  save(dto);
    }

    private void validate(ImpressionEventDto clickEventDto) {
        if(clickEventDto.getAdvertiserId()== null || clickEventDto.getAppId()== null || clickEventDto.getCountryCode()== null){
            throw new RuntimeException("input values should not be empty.");
        }
    }

    public List<AppCountryPerformanceQueryResult> calculateAppCountryPerformance() {
        return repository.getAppCountryPerformance();
    }

    public List<AppCountryAdvertiserPerformanceQueryResult> calculateAppCountryByAdvertisersPerformance() {
        return repository.calculateAppCountryByAdvertisersPerformance();
    }

    public ImpressionEvent getById(UUID impressionId) {
        return repository.findById(impressionId).orElseThrow(() -> new RuntimeException("impression not found"));
    }
}
