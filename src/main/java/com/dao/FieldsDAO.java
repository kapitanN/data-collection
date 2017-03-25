package com.dao;

import com.Entities.FieldEntity;
import com.beans.FieldBean;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * Created by nikita on 24.03.2017.
 */
public class FieldsDAO {

    private static final Logger LOGGER = Logger.getLogger(FieldsDAO.class);

    public void setField(FieldBean field){
        LOGGER.info("in setField");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setLabel(field.getLabel());
        fieldEntity.setType(field.getType());
        fieldEntity.setRequired(field.getRequired());
        fieldEntity.setActive(field.getActive());
        session.save(fieldEntity);
        session.getTransaction().commit();
    }
    public void setField(String label, String type, boolean required, boolean active){
        LOGGER.info("in setField");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setLabel(label);
        fieldEntity.setType(type);
        fieldEntity.setRequired(required);
        fieldEntity.setActive(active);
        session.save(fieldEntity);
        session.getTransaction().commit();
    }


    public FieldEntity getField(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from FieldEntity where id = :id");
        query.setParameter("id",id);
        FieldEntity fieldEntity = (FieldEntity) query.uniqueResult();
        session.getTransaction().commit();
        return fieldEntity;
    }

    public void deleteField(FieldEntity field){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.delete(field);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error(e);
            session.getTransaction().rollback();
            throw new HibernateException("Can't be deleted"+field.getLabel(),e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
