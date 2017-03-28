package com.Entities;

import javax.persistence.*;

/**
 * Created by nikita on 28.03.2017.
 */

@Entity
@Table(name = "responses", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class ResponseEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "f_id")
    private Integer f_id;

    @Column(name = "u_id")
    private Integer u_id;

    @Column(name = "value")
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
