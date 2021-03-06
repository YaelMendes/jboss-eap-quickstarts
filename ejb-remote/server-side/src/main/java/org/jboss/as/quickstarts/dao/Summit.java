package org.jboss.as.quickstarts.dao;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString(exclude="mountain")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Summit implements Comparable<Summit>, Serializable {

    public Summit() {}

    public Summit(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    @Id
    @NotNull
    private String name;

    @Basic
    private Integer height;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "mountain_fk", nullable = false)
    @NotNull
    @XmlTransient
    private Mountain mountain;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long version = 0L;

    @Override
    public int compareTo(Summit s) {
        return height.compareTo(s.getHeight());
    }
}
