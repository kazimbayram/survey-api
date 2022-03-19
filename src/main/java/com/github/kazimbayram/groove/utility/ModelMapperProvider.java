package com.github.kazimbayram.groove.utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperProvider implements Mapper {

    private final ModelMapper modelMapper;

    public ModelMapperProvider(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, K> T map(K source, Class<T> clazz) {
        return modelMapper.<T>map(source, clazz);
    }

    @Override
    public <T, K> List<T> map(List<K> source, Class<T> clazz) {
        return source.stream().map(s -> map(s, clazz)).collect(Collectors.toList());
    }
}
