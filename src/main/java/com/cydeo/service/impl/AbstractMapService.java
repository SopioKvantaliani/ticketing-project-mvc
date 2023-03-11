package com.cydeo.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T, ID>{ //As implementation will be same for all CrudService methods, we create abstract class

    public Map<ID, T> map = new HashMap<>(); //Data Base
    T save(ID id, T object) {
        map.put(id, object);
        return object;
    }

    List<T> findAll() {
        return new ArrayList<>(map.values()); //it will return List. We can replace this with traditional way to create list.
    }

    T findById (ID id) {
        return map.get(id);
    }

    void deleteById (ID id){
        map.remove(id);
    }

}

/*
We are on Service Impl stage now.
 */
