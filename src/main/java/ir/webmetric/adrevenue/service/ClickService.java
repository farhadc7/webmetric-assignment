package ir.webmetric.adrevenue.service;

import com.fasterxml.jackson.core.type.TypeReference;
import ir.webmetric.adrevenue.dto.ClickEventDto;
import ir.webmetric.adrevenue.entity.ClickEvent;
import ir.webmetric.adrevenue.entity.ImpressionEvent;
import ir.webmetric.adrevenue.mapper.ClickMapper;
import ir.webmetric.adrevenue.repository.ClickRepository;
import ir.webmetric.adrevenue.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClickService {
    private final FileUtil<ClickEventDto> fileUtil;
    private final ClickRepository clickRepository;
    private final ClickMapper mapper;
    private final ImpressionEventService impressionEventService;

    public ClickService(FileUtil<ClickEventDto> fileToDtoUtil, ClickRepository clickRepository, ClickMapper mapper, ImpressionEventService impressionEventService) {
        this.fileUtil = fileToDtoUtil;
        this.clickRepository = clickRepository;
        this.mapper = mapper;
        this.impressionEventService = impressionEventService;
    }

    public List<ClickEvent> save(MultipartFile file) throws IOException {
        fileUtil.validateFile(file);
        List<ClickEventDto> list;
        list =fileUtil.getMapper().readValue(file.getInputStream(), new TypeReference<>() {
        });
        return asyncSaveAll(list);
    }

    public List<ClickEvent> asyncSaveAll(List<ClickEventDto> dtos){
        return dtos.parallelStream().map(this::validatedSave).toList();

    }

    public ClickEvent save(ClickEventDto dto){
       return clickRepository.save(convertToEntity(dto));
    }

    private ClickEvent convertToEntity(ClickEventDto dto) {
        var entity= mapper.dtoToEntity(dto);
        ImpressionEvent impEnt= impressionEventService.getById(dto.getImpressionId());
        entity.setImpression(impEnt);
        return entity;
    }

    private ClickEvent validatedSave(ClickEventDto dto){
        validate(dto);
       return  save(dto);
    }

    private void validate(ClickEventDto clickEventDto) {
        if(clickEventDto.getRevenue()== 0 || clickEventDto.getImpressionId()== null ){
            throw new RuntimeException("input values should not be empty.");
        }
    }
}
