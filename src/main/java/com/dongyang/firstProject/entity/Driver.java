package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String carModel;

    // 생성자
    public Driver(String name, String carModel) {
        this.name = name;
        this.carModel = carModel;
    }
}