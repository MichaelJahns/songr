package com.example.songr.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Album {
    @Id
    @GeneratedValue
    public long id;

    public String title;
    public String artist;
    public int songCount;
    public int length;
    public String imageUrl;
}
