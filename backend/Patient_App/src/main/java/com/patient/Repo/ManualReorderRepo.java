package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.ManualReorder;

public interface ManualReorderRepo extends JpaRepository<ManualReorder, String> {

}
