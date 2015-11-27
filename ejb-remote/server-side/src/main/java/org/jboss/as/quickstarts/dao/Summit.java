package org.jboss.as.quickstarts.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Summit {

    @Id
    @Basic
    private String name;

    @Basic
    private Integer height;

    @ManyToOne
    private Mountain mountain;

}
