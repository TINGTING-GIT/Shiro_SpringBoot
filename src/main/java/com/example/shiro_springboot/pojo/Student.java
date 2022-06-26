package com.example.shiro_springboot.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {
    private int id;
    private String name;
    private int age;
    private int score;
}
