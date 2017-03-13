package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Attachment;

@Repository
public interface AttachmentDAO extends GenericDAO<Attachment, Integer> {

}



