package ir.webmetric.adrevenue.mapper;

import ir.webmetric.adrevenue.dto.ImpressionEventDto;
import ir.webmetric.adrevenue.entity.ImpressionEvent;
import org.mapstruct.Mapper;

@Mapper
public interface ImpressionMapper extends GeneralMapper<ImpressionEventDto, ImpressionEvent>{

}
