package com.example.ram.domain;

import lombok.Data;

import java.util.Comparator;

/**
 * Эпизоды
 */
@Data
public class Episode implements Comparable<Episode> {
    private Integer id;
    private String name;
    private String air_date;
    private String episode;

    @Override
    public int compareTo(Episode o) {
        if (this.id > o.id)
            return 1;
        else return -1;
    }
}
