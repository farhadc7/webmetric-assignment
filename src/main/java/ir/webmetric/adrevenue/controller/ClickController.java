package ir.webmetric.adrevenue.controller;

import ir.webmetric.adrevenue.dto.ClickEventDto;
import ir.webmetric.adrevenue.entity.ClickEvent;
import ir.webmetric.adrevenue.service.ClickService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/click")
public class ClickController {
    private final ClickService clickService;

    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @PostMapping("/v1/save-file")
    public List<ClickEvent> saveClickFile(@RequestParam("click") MultipartFile file) throws IOException {
        return clickService.save(file);
    }

    @PostMapping("/v1/save")
    public void saveClickFile(@Valid @RequestBody ClickEventDto dto) throws IOException {
        clickService.save(dto);
    }

    @PostMapping("/v1/save-all")
    public void saveClickFile(@Valid @RequestBody List<ClickEventDto> dtos) throws IOException {
        clickService.asyncSaveAll(dtos);
    }
}


