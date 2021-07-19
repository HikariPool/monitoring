/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.model.entity.util;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static List convert(List sources, Class target) {
        ModelMapper mapper = new ModelMapper();
        List objects = new ArrayList();
        for (Object source : sources) {
            objects.add(mapper.map(source, target));
        }
        return objects;
    }
}
