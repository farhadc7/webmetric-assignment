package ir.webmetric.adrevenue.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickEventDto {
    @NotNull
    private UUID impressionId;
    private double revenue;
}
