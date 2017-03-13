package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.domain.Task;
import com.mss.shtoone.persistence.TaskDAO;



@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class TaskService {

	@Autowired
	private TaskDAO taskDAO;
	
	public void add(Task task) {
		taskDAO.saveOrUpdate(task);
	}

	public void delete(int id) {
		Task task = taskDAO.get(id);
		taskDAO.delete(task);
	}

	public void complete(int id) {
		Task task = taskDAO.get(id);
		task.setComplete(true);
		taskDAO.saveOrUpdate(task);
	}

	public List<Task> list() {
		return taskDAO.loadAll();
	}
}
