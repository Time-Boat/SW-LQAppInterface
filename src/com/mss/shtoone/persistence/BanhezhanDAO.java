package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Banhezhanxinxi;

@Repository
public interface BanhezhanDAO extends GenericDAO<Banhezhanxinxi, Integer> {
   public Banhezhanxinxi getBhzbybianhao(String shebeibianhao);
}



