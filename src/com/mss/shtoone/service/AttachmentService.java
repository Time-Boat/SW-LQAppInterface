package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Attachment;
import com.mss.shtoone.persistence.AttachmentDAO;

@Service
public class AttachmentService {
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	public List<Attachment> getAttachmentList(String where){
		return attachmentDAO.find("from Attachment "+where);
	}
	
	public void delAttachment(int id){
		attachmentDAO.deleteByKey(id);
	}
	
	
	public Attachment getBean(int id){
		return attachmentDAO.get(id);
	}
	
	public void addAttachment(Attachment att){
		attachmentDAO.save(att);
	}

}
