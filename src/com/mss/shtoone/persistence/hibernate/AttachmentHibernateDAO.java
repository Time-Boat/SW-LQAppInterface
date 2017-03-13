package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Attachment;
import com.mss.shtoone.persistence.AttachmentDAO;


@Repository
public class AttachmentHibernateDAO extends GenericHibernateDAO<Attachment, Integer> implements
AttachmentDAO {
	
	
}



