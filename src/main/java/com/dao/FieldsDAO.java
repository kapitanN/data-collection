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
import java.util.Map;


/**
 * Created by nikita on 24.03.2017.
 */
public class FieldsDAO {

    private static final Logger LOGGER = Logger.getLogger(FieldsDAO.class);

    public void updateField(int fieldId,String label, String type, boolean required, boolean active){
        LOGGER.info("in setField");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FieldEntity field = getField(fieldId);
        field.setLabel(label);
        field.setType(type);
        field.setRequired(required);
        field.setActive(active);
        session.update(field);
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

    public void editField(FieldEntity field){

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
        List<ResponseEntity> responseList = getResponseByFieldId(field.getId());
        try {
            for (ResponseEntity response : responseList) {
                session.delete(response);
            }
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

    public List<ResponseEntity> getResponseByFieldId(int fieldId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ResponseEntity where field.id = :f_id");
        query.setParameter("f_id",fieldId);
        try {
            List<ResponseEntity> list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't get responses by field id",e);
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

    public UsersEntity getMaxUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query2 = session.createQuery("from  UsersEntity");
        int maxUser = query2.list().size();
        UsersEntity usersEntity = new UsersEntity(maxUser+1);
        return usersEntity;
    }

    public void setUserData(Map<String,String> userData){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from FieldEntity ");
        Query query1 = session.createQuery("from FieldEntity where label = :label");
        UsersEntity usersEntity = getMaxUser();
        try {
            List<FieldEntity> fields = query.list();
            for (Map.Entry item : userData.entrySet()) {
                query1.setParameter("label",item.getKey());
                FieldEntity entity = (FieldEntity) query1.uniqueResult();
                ResponseEntity user = new ResponseEntity();
                user.setId(12);
                user.setField(entity);
                user.setUser(usersEntity);
                user.setValue(item.getValue().toString());
                System.out.println(user.getId() + " " +
                        user.getField().getId() + "" + user.getUser().getId() + "" + user.getValue());
                session.save(usersEntity);
                session.save(user);
                if (!session.getTransaction().isActive()){
                    session.getTransaction().commit();
                }
            }

        } catch (HibernateException e){
            LOGGER.error(e.toString());
            session.getTransaction().rollback();
            throw new HibernateException("Can't set users data",e);
        } finally {
            if (session.isOpen()){session.close();}
        }
    }

}
