package com.Entities;

import javax.persistence.*;

/**
 * Created by nikita on 30.03.2017.
 */
@Entity
@Table(name = "types_options", schema = "public", catalog = "data_collection")
public class TypesOptionsEntity {
    private int id;
    private int fId;
    private String value;

    public TypesOptionsEntity() {
    }

    public TypesOptionsEntity(int fId, String value) {
        this.fId = fId;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "f_id", nullable = false)
    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    @Basic
    @Column(name = "value", nullable = false, length = -1)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypesOptionsEntity that = (TypesOptionsEntity) o;

        if (id != that.id) return false;
        if (fId != that.fId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
