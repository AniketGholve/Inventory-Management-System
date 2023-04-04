package com.patient.Service;

import java.util.List;

import com.patient.Entity.ManualReorder;

public interface ManualReorderService {
	
	public List<ManualReorder> update(List<ManualReorder> list);
	
	public List<ManualReorder> getAll();
}
