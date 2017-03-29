package com.beans;

import com.Entities.FieldEntity;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean {

    FieldEntity bean = FieldBean.getField(1);
    FieldEntity bean1 = FieldBean.getField(2);
    FieldEntity bean2 = FieldBean.getField(3);
    FieldEntity bean3 = FieldBean.getField(4);
    FieldEntity bean4 = FieldBean.getField(5);

    public List<FieldEntity> getFields(){
        List<FieldEntity> fields = new ArrayList<FieldEntity>();
        fields.add(bean);
        fields.add(bean1);
        fields.add(bean2);
        fields.add(bean3);
        return fields;
    }

    public String getLabel(int i){
        List<String> labels = getLabels();
        return labels.get(i);
    }

    public List<String> getLabels(){
        List<String> labels = new ArrayList<String>();
        labels.add("Nikita");
        labels.add("nikita@gmail.com");
        labels.add("Shapovalov");
        labels.add("Male");
        return labels;
    }

    public List<List<String>> getResponses(){
        List<List<String>> response =  new ArrayList<List<String>>();
        List<String> labels0 = new ArrayList<String>();
        labels0.add("Nikita");
        labels0.add("nikita@gmail.com");
        labels0.add("Male");
        labels0.add("Shapovalov");
        List<String> labels1 = new ArrayList<String>();
        labels1.add("Rodion");
        labels1.add("rodion@gmail.com");
        labels1.add("Male");
        labels1.add("Kolomoiets");
        List<String> labels2 = new ArrayList<String>();
        labels2.add("Valentina");
        labels2.add("valentina@gmail.com");
        labels2.add("Female");
        labels2.add("Antonyk");
        List<String> labels3 = new ArrayList<String>();
        labels3.add("Nikita");
        labels3.add("nikita@gmail.com");
        labels3.add("Male");
        labels3.add("Shapovalov");

        response.add(labels0);
        response.add(labels1);
        response.add(labels2);
        response.add(labels3);
        return response;
    }

}
