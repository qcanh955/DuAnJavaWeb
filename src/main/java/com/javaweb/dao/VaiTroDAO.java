package com.javaweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.entities.NguoiDung;



@Repository
@Transactional
public class VaiTroDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public List<String> getRoleNames(String id) {
//        String sql = "Select ndvvt.vaiTro.tenvaitro from qtht_nguoidungvavaitro ndvvt " //
//                + " where ndvvt.nguoiDung.id = :id ";
    	 String sql = "Select nd.vaitro.tenvaitro from " + NguoiDung.class.getName() + "nd " //
                 + " where nd.nguoidung.id = :id ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
