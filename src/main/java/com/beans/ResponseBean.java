package com.beans;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 26.03.2017.
 */

@ManagedBean(name = "responseBean")
public class ResponseBean {

    private String sex;
    private List<String> sexs;
    private String option;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSexs(List<String> sexs) {
        this.sexs = sexs;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getSexs(){
        sexs = new ArrayList<String>();
        sexs.add("Male");
        sexs.add("Female");
        return sexs;
    }
}
