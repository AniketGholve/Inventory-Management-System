package com.patient.Service;

import java.util.List;

import com.patient.Entity.Serial;

public interface SerialService {

	public Serial createSerial(Serial serial);
	
	public List<Serial> getSerialByLocationId(Integer locationId);
}
