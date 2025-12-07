package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.entity.Driver;
import com.dongyang.firstProject.repository.DriverRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // '데이터(JSON)'
@RequestMapping("/api/drivers") // localhost:8081/api/drivers
@CrossOrigin(origins = "*") // 리액트(3000번 포트)의 접속을 허용
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // 1. 목록 조회 (GET)
    @GetMapping
    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    // 2. 데이터 추가 (POST)
    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }
}
