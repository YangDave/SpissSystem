package com.ss.spiss.dao;

import java.util.List;

import com.ss.common.BaseDao;
import com.ss.spiss.entity.Plate;

public interface PlateDao extends BaseDao<Plate>{
	
	List<String> findAllPlateName();
	boolean isPlateExist(Object obj);

}
