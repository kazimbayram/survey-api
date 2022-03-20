package com.github.kazimbayram.groove.utility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MapperTest {

    @Autowired
    Mapper mapper;

    @Test
    void testMap() {
        var source = new SourceObject(1, "Test1");

        var dest = mapper.map(source, DestinationObject.class);

        assertEquals(dest.getId(), source.getId());
        assertEquals(dest.getName(), source.getName());
    }

    @Test
    void testMapList() {
        var source = new ArrayList<SourceObject>();
        source.add(new SourceObject(1, "Test1"));
        source.add(new SourceObject(2, "Test2"));

        var dest = mapper.map(source, DestinationObject.class);

        assertEquals(dest.size(), 2);
        assertEquals(dest.get(0).getId(), dest.get(0).getId());
        assertEquals(dest.get(0).getName(), source.get(0).getName());
    }
}