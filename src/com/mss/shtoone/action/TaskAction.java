package com.mss.shtoone.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.mss.shtoone.domain.Task;
import com.mss.shtoone.service.TaskService;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/task")
@Results(@Result(name = "list", type = "redirect", location = "list.action"))
public class TaskAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Task> tasks;
	private Task task;
	private int taskId;
	@Autowired
	private TaskService taskService;

	@Action("add")
	public String add() {
		task.setComplete(false);
		taskService.add(task);
		return "list";
	}

	@Action(value = "complete", results = @Result(name = "input", type = "redirect", location = "list.action"))
	public String complete() {
		taskService.complete(taskId);
		return "list";
	}

	@Action(value = "delete", results = @Result(name = "input", type = "redirect", location = "list.action"))
	public String delete() {
		//System.out.println(taskId);
		taskService.delete(taskId);
		return "list";
	}

	@Action("list")
	public String list() {
		tasks = taskService.list();
		return SUCCESS;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
