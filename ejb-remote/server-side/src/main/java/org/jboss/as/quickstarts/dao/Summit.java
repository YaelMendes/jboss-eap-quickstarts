package org.jboss.as.quickstarts.dao;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Getter
@Setter
@Entity
@ToString
public class Summit implements Comparable<Summit>, Serializable {

    public Summit() {}

    public Summit(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    @Id
   // @Basic
    private String name;

    @Basic
    private Integer height;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Mountain mountain;

    @Override
    public int compareTo(Summit s) {
        return height.compareTo(s.getHeight());
    }
}
