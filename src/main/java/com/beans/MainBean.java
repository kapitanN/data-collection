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

    public List<FieldEntity> getFields(){
        List<FieldEntity> fields = new ArrayList<FieldEntity>();
        fields.add(bean);
        fields.add(bean1);
        fields.add(bean2);
        fields.add(bean3);
        return fields;
    }

    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage("Заголовок", "Частичное обновление страницы");
        message.setSeverity(FacesMessage.SEVERITY_INFO); //как выглядит окошко с сообщением
        FacesContext.getCurrentInstance().addMessage(null, message);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Всплывашка", "GrowlMessage"));

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Значение", inputText));
    }
}
