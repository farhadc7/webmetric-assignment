package ir.webmetric.adrevenue.mapper;

import org.mapstruct.Mapper;

import java.util.List;


public interface GeneralMapper<D,E> {
    D entityToDto(E e);
    E dtoToEntity(D d);
    List<D> entityToDtoList(List<E> eList);
    List<E> dtoToEntityList(List<D> dList);
}
