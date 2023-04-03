package com.patient.Service;

import java.util.List;

import com.patient.Entity.AutoReorder;

public interface AutoReorderService {
	
	public List<AutoReorder> getAll();
	
	public List<AutoReorder> updateReorder(List<AutoReorder> list);
}
