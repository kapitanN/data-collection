package com.beans;


import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * Created by nikita on 24.03.2017.
 */

@Entity
@Table(name = "fields", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class FieldsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;


    @Column(name = "label", unique = false, nullable = true)
    private String label;

    @Column(name = "type", unique = false, nullable = true)
    private String type;

    @Column(name = "required")
    private boolean required;

    @Column(name = "is_active")
    private boolean isActive;
    public Integer getFieldId() {
        return id;
    }

    public void setFieldId(Integer id) {
        this.id = id;
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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



}
