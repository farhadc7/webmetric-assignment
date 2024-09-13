package ir.webmetric.adrevenue.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpressionEventDto {
    private Integer appId;
    private String countryCode;
    private Integer advertiserId;

}

