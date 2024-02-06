package com.jmv.pricesapp.priceinfraestructure.sharedservices;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Mappers {

    public static <T> List<T> toList (Iterable<T> element){

        return StreamSupport.stream(element.spliterator(), false)
                .collect(Collectors.toList());
    }
}
