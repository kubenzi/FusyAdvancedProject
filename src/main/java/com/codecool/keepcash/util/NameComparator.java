package com.codecool.keepcash.util;

import com.codecool.keepcash.Entity.AccountType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

@Component
public class NameComparator implements Comparator<String> {

    @Override
    public int compare(String name1, String name2) {
        return name1.compareTo(name2);
    }
}

