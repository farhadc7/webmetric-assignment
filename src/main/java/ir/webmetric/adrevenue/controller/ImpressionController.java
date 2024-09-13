package ir.webmetric.adrevenue.controller;

import ir.webmetric.adrevenue.dto.ImpressionEventDto;
import ir.webmetric.adrevenue.entity.ImpressionEvent;
import ir.webmetric.adrevenue.service.ImpressionEventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/impression")
public class ImpressionController {
    private final ImpressionEventService service;

    public ImpressionController(ImpressionEventService service) {
        this.service = service;
    }

    @PostMapping("/v1/save-file")
    public List<ImpressionEvent>  saveImpressionFile(@RequestParam("impression") MultipartFile file) throws IOException {
       return service.save(file);
    }

    @PostMapping("/v1/save")
    public void saveImpression(@Valid @RequestBody ImpressionEventDto dto)   {
        service.save(dto);
    }

    @PostMapping("/v1/save-all")
    public void saveAllImpressions(@Valid @RequestBody List<ImpressionEventDto> dtoList)   {
        service.asyncSaveAll(dtoList);
    }
}
