package com.patient.Service;

import java.util.List;

import com.patient.Entity.QuickLink;

public interface QuickLinkService {
	
	public List<QuickLink> getAllQuickLink();
	
	public QuickLink addQuickLink(QuickLink quickLink);
	
	public String deleteQuickLink(int id);
	
	public QuickLink updateQuickLink(QuickLink quickLink);
}
