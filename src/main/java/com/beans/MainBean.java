package com.beans;

import com.Entities.FieldEntity;
import com.dao.FieldsDAO;
import com.service.ResponsesService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean {
    public String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<FieldEntity> getFields(){
        FieldsDAO fieldsDAO = new FieldsDAO();
        List<FieldEntity> fields = fieldsDAO.getAllFields();
        return fields;
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

    public void setAllValues(){
        System.out.println("To out-put All the request-attributes received from request - ");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Enumeration enAttr = request.getAttributeNames();
        while(enAttr.hasMoreElements()){
            String attributeName = (String)enAttr.nextElement();
            System.out.println("Attribute Name - "+attributeName+", Value - "+(request.getAttribute(attributeName)).toString());
        }

        System.out.println("To out-put All the request parameters received from request - ");

        Enumeration enParams = request.getParameterNames();
        while(enParams.hasMoreElements()){
            String paramName = (String)enParams.nextElement();
            System.out.println("Attribute Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
        System.out.println("TEST: " + test);
    }
}
