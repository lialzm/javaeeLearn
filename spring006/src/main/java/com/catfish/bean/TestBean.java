package com.catfish.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by A on 2017/4/19.
 */
@Entity
@Table(name = "test")
public class TestBean  implements Serializable{

    @Id
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
