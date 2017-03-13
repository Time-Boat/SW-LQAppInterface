/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Task;


@Repository
public interface TaskDAO extends GenericDAO<Task,Integer> {
	
}
