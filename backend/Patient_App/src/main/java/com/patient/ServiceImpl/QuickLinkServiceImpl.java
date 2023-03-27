package com.patient.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.QuickLink;
import com.patient.Repo.QuickLinkRepo;
import com.patient.Service.QuickLinkService;

@Service
public class QuickLinkServiceImpl implements QuickLinkService {
	
	@Autowired
	QuickLinkRepo quickLinkRepo;
	
	@Override
	public List<QuickLink> getAllQuickLink() {
		// TODO Auto-generated method stub
		List<QuickLink> list = quickLinkRepo.findAll();
		return list;
	}

	@Override
	public QuickLink addQuickLink(QuickLink quickLink) {
		// TODO Auto-generated method stub
//		Date d = new Date();
//		d = Date(System.currentTimeMillis());
////		SimpleDateFormat format = new SimpleDateFormat("yyyy:mm:dd")
//		quickLink.setCreatedOn(d);
		Date d = new Date(System.currentTimeMillis());
		quickLink.setCreatedOn(d);
		quickLinkRepo.save(quickLink);
		
		return quickLink;
	}

	@Override
	public String deleteQuickLink(int id) {
		// TODO Auto-generated method stub
		quickLinkRepo.deleteById(id);
		return "Link Deleted Successfully";
	}

	@Override
	public QuickLink updateQuickLink(QuickLink quickLink) {
		// TODO Auto-generated method stub
		QuickLink link = quickLinkRepo.findById(quickLink.getId());
		link.setCreatedOn(quickLink.getCreatedOn());
		link.setTitle(quickLink.getTitle());
		link.setUrl(quickLink.getUrl());
		quickLinkRepo.save(link);
		return link;
	}

	@Override
	public QuickLink getQuickLink(int id) {
		// TODO Auto-generated method stub
		QuickLink q = quickLinkRepo.findById(id);
		return q;
	}

}
