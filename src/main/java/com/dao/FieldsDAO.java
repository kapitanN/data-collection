package com.dao;

import com.Entities.*;
import com.beans.FieldBean;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


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

    public FieldEntity getFieldByLabel(String label){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from FieldEntity where label = :label");
        query.setParameter("label",label);
        try {
            FieldEntity field = (FieldEntity) query.uniqueResult();
            session.getTransaction().commit();
            return field;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get field by label",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

    public List<FieldEntity> getAllFields(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from FieldEntity ");
        List<FieldEntity> allFields = query.list();
        session.getTransaction().commit();
        return allFields;
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

    public List<ResponseEntity> getResponse(int userId,int fieldId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from ResponseEntity where user.id = :u_id and field.id = :f_id");
        query.setParameter("u_id",userId);
        query.setParameter("f_id",fieldId);
        try {
            List<ResponseEntity> list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get responses",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

    public List<TypesEntity> getAllTypes(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from TypesEntity");
        try {
            List<TypesEntity> allTypes = query.list();
            session.getTransaction().commit();
            return allTypes;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get types",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

    public List<TypesOptionsEntity> getAllTypesOptions(int fieldId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from TypesOptionsEntity where fId = :f_id");
        query.setParameter("f_id",fieldId);
        try {
            List<TypesOptionsEntity> allTypesOptions = query.list();
            session.getTransaction().commit();
            return allTypesOptions;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get types options",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

    public void setTypesOptions(int fieldId, String value){
        LOGGER.info("in setTypesOptions");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        TypesOptionsEntity optionsEntity = new TypesOptionsEntity(fieldId,value);
        session.save(optionsEntity);
        session.getTransaction().commit();
    }

    public List<UsersEntity> getUsers(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UsersEntity ");
        try {
            List<UsersEntity> users = query.list();
            session.getTransaction().commit();
            return users;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get users",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

}
