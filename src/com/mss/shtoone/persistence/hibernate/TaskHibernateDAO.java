
package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Task;
import com.mss.shtoone.persistence.TaskDAO;


/**
 *
 * @author Mao Shijie
 */
@Repository
public class TaskHibernateDAO extends GenericHibernateDAO<Task,Integer>  implements TaskDAO {

}
