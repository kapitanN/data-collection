package com.beans;

import com.Entities.FieldEntity;
import com.dao.FieldsDAO;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

/**
 * Created by nikita on 25.03.2017.
 */

@ManagedBean(name = "fieldBean")
@SessionScoped
public class FieldBean {
    String label;
    String type;
    boolean required;
    boolean active;

    private static final Logger LOGGER = Logger.getLogger(FieldBean.class);

    public FieldBean(){}
    public FieldBean(String label, String type, boolean required, boolean active) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.active = active;
    }

    public static FieldEntity getField(int id){
        FieldsDAO fieldsDAO = new FieldsDAO();
        FieldEntity fieldEntity = fieldsDAO.getField(id);
        return fieldEntity;
    }

    public void addField(){
        LOGGER.info("in addField");
        FieldsDAO fieldsDAO = new FieldsDAO();
        fieldsDAO.setField(label,type,required,active);
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
