package com.dao;

import com.beans.FieldsBean;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by nikita on 24.03.2017.
 */
public class FieldsDAO {

    public void setField(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FieldsBean fieldsBean = new FieldsBean();
        fieldsBean.setLabel(name);
        fieldsBean.setType("smth");
        fieldsBean.setRequired(true);
        fieldsBean.setActive(true);
        session.save(fieldsBean);
        session.getTransaction().commit();
    }

    public FieldsBean getField(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from FieldsBean where id = :id");
        query.setParameter("id",id);
        FieldsBean fieldsBean = (FieldsBean) query.uniqueResult();
        session.getTransaction().commit();
        return fieldsBean;
    }
}
