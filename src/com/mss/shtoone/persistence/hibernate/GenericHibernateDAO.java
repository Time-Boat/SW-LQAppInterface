/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.shtoone.persistence.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.persistence.GenericDAO;

@Repository
@Transactional
public class GenericHibernateDAO<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK>{
	
	@Autowired
	private HibernateTemplate template;

	 // 实体类类型(由构造方法自动赋值)
    private Class<T> entityClass;

    // 构造方法，根据实例类自动获取实体类类型
    public GenericHibernateDAO()
    {
        this.entityClass = null;
        Class c = getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType)
        {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
    }

    // -------------------- 基本检索、增加、修改、删除操作 --------------------

    // 根据主键获取实体。如果没有相应的实体，返回 null。
    public T get(PK id)
    {
    	return (T) getTemplate().get(entityClass, id);
    }

    // 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
    public T getWithLock(PK id, LockMode lock)
    {
        T t = (T) getTemplate().get(entityClass, id, lock);
        if (t != null)
        {
            this.flush();   // 立即刷新，否则锁不会生效。
        }
        return t;
    }

    // 根据主键获取实体。如果没有相应的实体，抛出异常。
    public T load(PK id)
    {
        return (T) getTemplate().load(entityClass, id);
    }

    // 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
    public T loadWithLock(PK id, LockMode lock)
    {
        T t = (T) getTemplate().load(entityClass, id, lock);
        if (t != null)
        {
            this.flush();   // 立即刷新，否则锁不会生效。
        }
        return t;
    }

    // 获取全部实体。
    public List<T> loadAll()
    {
        return (List<T>) getTemplate().loadAll(entityClass);
    }

    // loadAllWithLock() ?
    
    // 更新实体
    public void update(T entity)
    {
        getTemplate().update(entity);
    }

    // 更新实体并加锁
    public void updateWithLock(T entity, LockMode lock)
    {
        getTemplate().update(entity, lock);
        this.flush();   // 立即刷新，否则锁不会生效。
    }

    // 存储实体到数据库
    public void save(T entity)
    {
        getTemplate().save(entity);
    }

    // saveWithLock()？

    // 增加或更新实体
    public void saveOrUpdate(T entity)
    {
        getTemplate().saveOrUpdate(entity);
    }

    // 增加或更新集合中的全部实体
    public void saveOrUpdateAll(Collection<T> entities)
    {
        getTemplate().saveOrUpdateAll(entities);
    }

    // 删除指定的实体
    public void delete(T entity)
    {
        getTemplate().delete(entity);
    }

    // 加锁并删除指定的实体
    public void deleteWithLock(T entity, LockMode lock)
    {
        getTemplate().delete(entity, lock);
        this.flush();   // 立即刷新，否则锁不会生效。
    }

    // 根据主键删除指定实体
    public void deleteByKey(PK id)
    {
        this.delete(this.load(id));
    }

    // 根据主键加锁并删除指定的实体
    public void deleteByKeyWithLock(PK id, LockMode lock)
    {
        this.deleteWithLock(this.load(id), lock);
    }

    // 删除集合中的全部实体
    public void deleteAll(Collection<T> entities)
    {
        getTemplate().deleteAll(entities);
    }

    // -------------------- HSQL ----------------------------------------------
    
    // 使用HSQL语句直接增加、更新、删除实体
    public int bulkUpdate(String queryString)
    {
        return getTemplate().bulkUpdate(queryString);
    }

    // 使用带参数的HSQL语句增加、更新、删除实体
    public int bulkUpdate(String queryString, Object... values)
    {
        return getTemplate().bulkUpdate(queryString, values);
    }

    // 使用HSQL语句检索数据
    public List find(String queryString)
    {
        return getTemplate().find(queryString);
    }

    // 使用带参数的HSQL语句检索数据
    public List find(String queryString, Object... values)
    {
        return getTemplate().find(queryString, values);
    }

    // 使用带命名的参数的HSQL语句检索数据
    public List findByNamedParam(String queryString, String[] paramNames, Object... values)
    {
        return getTemplate().findByNamedParam(queryString, paramNames, values);
    }

    // 使用命名的HSQL语句检索数据
    public List findByNamedQuery(String queryName)
    {
        return getTemplate().findByNamedQuery(queryName);
    }

    // 使用带参数的命名HSQL语句检索数据
    public List findByNamedQuery(String queryName, Object... values)
    {
        return getTemplate().findByNamedQuery(queryName, values);
    }

    // 使用带命名参数的命名HSQL语句检索数据
    public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object... values)
    {
        return getTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
    }

    // 使用HSQL语句检索数据，返回 Iterator
    public Iterator iterate(String queryString)
    {
        return getTemplate().iterate(queryString);
    }

    // 使用带参数HSQL语句检索数据，返回 Iterator
    public Iterator iterate(String queryString, Object... values)
    {
        return getTemplate().iterate(queryString, values);
    }

    // 关闭检索返回的 Iterator
    public void closeIterator(Iterator it)
    {
        getTemplate().closeIterator(it);
    }

    // -------------------------------- Criteria ------------------------------
    
    // 创建与会话无关的检索标准
    public DetachedCriteria createDetachedCriteria()
    {
        return DetachedCriteria.forClass(this.entityClass);
    }
    
    // 创建与会话绑定的检索标准
    public Criteria createCriteria()
    {
        return this.createDetachedCriteria().getExecutableCriteria(getTemplate().getSessionFactory().openSession());
    }

    // 检索满足标准的数据
    public List findByCriteria(DetachedCriteria criteria)
    {
        return getTemplate().findByCriteria(criteria);
    }
    
    // 检索满足标准的数据，返回指定范围的记录
    public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults)
    {
        return getTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

    // 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
    public List<T> findEqualByEntity(T entity, String [] propertyNames) 
    {
        Criteria criteria = this.createCriteria();
        Example exam = Example.create(entity);
        exam.excludeZeroes();
        String [] defPropertys = getTemplate().getSessionFactory().getClassMetadata(entityClass).getPropertyNames();
        for(String defProperty : defPropertys)
        {
            int ii = 0;
            for(ii = 0; ii < propertyNames.length; ++ii)
            {
                if(defProperty.equals(propertyNames[ii]))
                {
                    criteria.addOrder(Order.asc(defProperty));
                    break;
                }
            }
            if(ii == propertyNames.length)
            {
                exam.excludeProperty(defProperty);
            }
        }
        criteria.add(exam);
        return (List<T>) criteria.list();
    }
    
    // 使用指定的实体及属性检索（满足属性 like 串实体值）数据
    public List<T> findLikeByEntity(T entity, String [] propertyNames)
    {
        Criteria criteria = this.createCriteria();
        for(String property : propertyNames)
        {
          try
         {
                Object value = PropertyUtils.getProperty(entity, property);
                if(value instanceof String)
                {
                    criteria.add(Restrictions.like(property, (String) value, MatchMode.ANYWHERE));
                    criteria.addOrder(Order.asc(property));
                }
                else
                {
                    criteria.add(Restrictions.eq(property, value));
                    criteria.addOrder(Order.asc(property));
                }
            }
            catch(Exception ex)
            {
                // 忽略无效的检索参考数据。
            }
        }
        return (List<T>) criteria.list();
    }
    
    // 使用指定的检索标准获取满足标准的记录数
    public Integer getRowCount(DetachedCriteria criteria)
    {
        criteria.setProjection(Projections.rowCount());
        List list = this.findByCriteria(criteria, 0, 1);
        return (Integer) list.get(0);
    }

    // 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
    public Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName)
    {
        if     (StatName.toLowerCase().equals("max")) 
            criteria.setProjection(Projections.max(propertyName));
        else if(StatName.toLowerCase().equals("min"))
            criteria.setProjection(Projections.min(propertyName));
        else if(StatName.toLowerCase().equals("avg"))
            criteria.setProjection(Projections.avg(propertyName));
        else if(StatName.toLowerCase().equals("sum"))
            criteria.setProjection(Projections.sum(propertyName));
        else return null;
        List list = this.findByCriteria(criteria, 0, 1);
        return list.get(0);        
    }
    
    // -------------------------------- Others --------------------------------
    
    // 加锁指定的实体
    public void lock(T entity, LockMode lock)
    {
        getTemplate().lock(entity, lock);
    }

    // 强制初始化指定的实体
    public void initialize(Object proxy)
    {
        getTemplate().initialize(proxy);
    }

    // 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
    public void flush()
    {
        getTemplate().flush();
    }

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
}
