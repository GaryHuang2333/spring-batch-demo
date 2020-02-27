package com.example.batch.common.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    private int id;
    private int age;
    private String department;
    private String gender;
    private String name;
    private String comment;
    private String staffNo;
}
