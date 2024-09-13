package ir.webmetric.adrevenue.mapper;

import ir.webmetric.adrevenue.dto.ClickEventDto;
import ir.webmetric.adrevenue.entity.ClickEvent;
import org.mapstruct.Mapper;

@Mapper
public interface ClickMapper extends GeneralMapper<ClickEventDto, ClickEvent>{

}
