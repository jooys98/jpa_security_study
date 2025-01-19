package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {



    List<Buy> findByUserId(Long buyUserId);
}
