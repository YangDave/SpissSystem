package com.ss.spiss.daoImpl;

import java.util.List;

import org.hibernate.Query;

import com.ss.common.BaseDaoImpl;
import com.ss.spiss.dao.PlateDao;
import com.ss.spiss.entity.Plate;
import com.ss.spiss.entity.User;

public class PlateDaoImpl extends BaseDaoImpl<Plate> implements PlateDao{

	@Override
	public List<String> findAllPlateName() {

		String hql = "select plate_name from Plate";

		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> l = (List<String>)query.list();
		if(!l.isEmpty()){
			return l;

		}
		return null;
	}

	@Override
	public boolean isPlateExist(Object obj) {
		if(obj instanceof Integer){

			return isIdExist(obj);

		}

		if(obj instanceof String){
			return isNameExist(obj);
		}

		if(obj instanceof Plate){
			Plate p = (Plate) obj;
			if(p.getPlate_id() != 0){
				return isIdExist(p.getPlate_id());
			}
			if(p.getPlate_name()!= null){
				return isNameExist(p.getPlate_name());
			}
		}

		return false;
	}

	private boolean isNameExist(Object obj) {
		Plate p = (Plate) getSessionFactory().getCurrentSession()
				.createQuery("select p from Plate p where p.plate_name = ?0").setParameter("0", obj).uniqueResult();
		if(p == null){
			return false;
		}
		return true;
	}

	private boolean isIdExist(Object obj) {
		Plate p = get(Plate.class, (Integer)obj);
		if(p == null){
			return false;
		}else{
			return true;
		}
	}

}
