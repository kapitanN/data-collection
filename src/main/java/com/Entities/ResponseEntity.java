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

    @ManyToOne
    @JoinColumn(name = "f_id")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private UsersEntity user;

    @Column(name = "value")
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FieldEntity getField() {
        return field;
    }

    public void setField(FieldEntity field) {
        this.field = field;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
