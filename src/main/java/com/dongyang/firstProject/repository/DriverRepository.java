package com.dongyang.firstProject.repository;

import com.dongyang.firstProject.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}