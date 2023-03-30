package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.QuickLink;

public interface QuickLinkRepo extends JpaRepository<QuickLink, Integer> {
	 
	public QuickLink findById(long id);
}
