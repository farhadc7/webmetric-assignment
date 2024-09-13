package ir.webmetric.adrevenue.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpressionEvent extends BaseEntity{
    @NotNull(message = "app id should be filled.")
    private int appId;
    @NotNull(message = "country code should be filled.")
    @Pattern(regexp="([A-Za-z]{2})")
    private String countryCode;
    @NotNull(message = "advertiser id should be filled.")
    private int advertiserId;
}
