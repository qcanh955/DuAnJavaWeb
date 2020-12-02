package com.javaweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.entities.NguoiDung;

@Repository
@Transactional
public class NguoiDungDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public NguoiDung findUserAccount(String tennguoidung) {
        try {
            String sql = "Select e from " + NguoiDung.class.getName() + " e " //
                    + " Where e.tennguoidung = :tennguoidung ";
 
            Query query = entityManager.createQuery(sql, NguoiDung.class);
            query.setParameter("tennguoidung", tennguoidung);
 
            return (NguoiDung) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
