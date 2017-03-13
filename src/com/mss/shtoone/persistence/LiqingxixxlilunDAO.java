package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Liqingxixxlilun;



@Repository
public interface LiqingxixxlilunDAO extends GenericDAO<Liqingxixxlilun, Integer> {
    public List<Liqingxixxlilun> getLilunlist(String llbuwei, String llshebeibianhao);
    public int lqgetlilunid(String gprsbh, String sjysb, double lowsjysb, double highsjysb);
}



