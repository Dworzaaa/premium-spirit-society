package com.premium.spirit.society.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionConverter<T> {

    public List<T> toList(Set<T> set) {
        if (set == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    public Set<T> toSet(List<T> list) {
        return new HashSet<>(list);
    }
}
