package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.AutoReorder;

public interface AutoReorderRepo extends JpaRepository<AutoReorder, String> {

}
