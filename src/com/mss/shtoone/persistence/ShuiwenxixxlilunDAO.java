package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shuiwenxixxlilun;



@Repository
public interface ShuiwenxixxlilunDAO extends GenericDAO<Shuiwenxixxlilun, Integer> {
	public Integer swgetlilunid(String gprsbh, String sjysb, String persjg1, String persjg2, double lowsjysb, double highsjysb, double lowsjg1, double highsjg1);
    public List<Shuiwenxixxlilun> getLilunlist(String llbuwei, String llshebeibianhao);
    /**
     * 根据设备编号和输入日期获取理论配比
     * @param llshebeibianhao
     * @param nowDate
     * @return
     */
    public List<Shuiwenxixxlilun> getLilunBySbbhAndTime(String llshebeibianhao,String nowDate);
}



