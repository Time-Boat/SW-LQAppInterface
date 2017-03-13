package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.persistence.XiangmubuDAO;


@Repository
public class XiangmubuHibernateDAO extends GenericHibernateDAO<Xiangmubuxinxi, Integer> implements
XiangmubuDAO {

}



