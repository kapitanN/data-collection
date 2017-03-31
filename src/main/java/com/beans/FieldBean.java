package com.beans;

import com.Entities.FieldEntity;
import com.Entities.TypesOptionsEntity;
import com.dao.FieldsDAO;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 25.03.2017.
 */

@ManagedBean(name = "fieldBean")
@SessionScoped
public class FieldBean {
    String label;
    String type;
    String option;
    boolean required;
    boolean active;
    int fieldId;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }





    private static final Logger LOGGER = Logger.getLogger(FieldBean.class);

    public FieldBean(){}
    public FieldBean(String label, String type, boolean required, boolean active) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.active = active;
    }

    public List<String> getTypesOption(String fieldLabel){
        FieldsDAO fieldsDAO = new FieldsDAO();
        List<String> optionsList = new ArrayList<String>();
        int fieldId = fieldsDAO.getFieldByLabel(fieldLabel).getId();
        List<TypesOptionsEntity> options = fieldsDAO.getAllTypesOptions(fieldId);
        for (TypesOptionsEntity item : options) {
            optionsList.add(item.getValue());
        }
        return optionsList;
    }
    public static FieldEntity getField(int id){
        FieldsDAO fieldsDAO = new FieldsDAO();
        FieldEntity fieldEntity = fieldsDAO.getField(id);
        return fieldEntity;
    }

    public String addField(){
        LOGGER.info("in addField");
        FieldsDAO fieldsDAO = new FieldsDAO();
        fieldsDAO.setField(label,type,required,active);
        if (option != null){
            List<String> options = parseOption();
            int fieldId = fieldsDAO.getFieldByLabel(label).getId();
            for (String item : options){
                setTypesOptions(fieldId,item);
            }
        }
        return "fields?faces-redirect=true";
    }

    public String showAddField(int fieldId) {
        this.fieldId = fieldId;
        System.out.println(fieldId);
        return "addField?faces-redirect=true&fieldId=" + fieldId;
    }

    public String updateField(){
        LOGGER.info("in updateField" + fieldId);
        FieldsDAO fieldsDAO = new FieldsDAO();
        fieldsDAO.updateField(fieldId,label,type,required,active);
        if (option != null){
            List<String> options = parseOption();
            int fieldId = fieldsDAO.getFieldByLabel(label).getId();
            for (String item : options){
                setTypesOptions(fieldId,item);
            }
        }
        return "fields?faces-redirect=true";
    }

    public List<String> parseOption(){
        String[] strings = option.trim().split("\\n");
        List<String> parseList = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            parseList.add(i,strings[i]);
        }
        for (String item : parseList) {
            System.out.println(item);
        }
        return parseList;
    }

    public void setTypesOptions(int fieldId, String value){
        FieldsDAO fieldsDAO = new FieldsDAO();
        fieldsDAO.setTypesOptions(fieldId,value);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
