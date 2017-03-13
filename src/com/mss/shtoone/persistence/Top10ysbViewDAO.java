package com.mss.shtoone.persistence;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Top10ysbView;

@Repository
public interface Top10ysbViewDAO extends GenericDAO<Top10ysbView, Integer> {
	public List<Top10ysbView> findByProperty(String propertyName, Object value);
	public Top10ysbView findByGprsbianhao(String gprsbh);
}



