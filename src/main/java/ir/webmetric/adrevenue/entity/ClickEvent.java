package ir.webmetric.adrevenue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickEvent extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "impression_id")
    private ImpressionEvent impression;
    private double revenue=0d;
}
