package br.com.softdesign.votacaoserver.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController {

    @Autowired
    protected ModelMapper mapper;

    protected List<Object> listToDto(Collection<?> objects, Class classDto){
        return objects.stream()
                .map(o -> toDto(o, classDto))
                .collect(Collectors.toList());
    }

    protected Object toDto(Object object, Class classDto){
        return convert(object, classDto);
    }

    private Object convert(Object object, Class clazz){
        return (Object) mapper.map(object, clazz);
    }

    protected Object dtoTo(Object o, Class entityClazz) {
        return (Object) mapper.map(o,  entityClazz);
    }

}
