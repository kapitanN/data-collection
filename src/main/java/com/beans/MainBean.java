package com.beans;

import com.Entities.FieldEntity;
import com.Entities.ResponseEntity;
import com.Entities.TypesEntity;
import com.dao.FieldsDAO;
import com.service.ResponsesService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean {



    public List<FieldEntity> getFields(){
        FieldsDAO fieldsDAO = new FieldsDAO();
        List<FieldEntity> fields = fieldsDAO.getAllFields();
        return fields;
    }

    public List<TypesEntity> getTypes(){
        FieldsDAO fieldsDAO = new FieldsDAO();
        List<TypesEntity> types = fieldsDAO.getAllTypes();
        return types;
    }



    public List<List<String>> getResponses(){
        FieldsDAO fieldsDAO = new FieldsDAO();
        ResponsesService responsesService = new ResponsesService();
        List<List<String>> responses =  new ArrayList<List<String>>();
        int usersCount = fieldsDAO.getUsers().size();
        for (int i = 0; i<usersCount;i++){
            responses.add(responsesService.getLabels(i));
        }
        return responses;
    }

    public void deleteField(FieldEntity field){
        FieldsDAO fieldsDAO = new FieldsDAO();
        fieldsDAO.deleteField(field);
    }
}
