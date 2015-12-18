package org.jboss.as.quickstarts.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@XmlRootElement
public class Mountain implements Serializable {

    @Id
    @NotNull
    //@XmlAttribute(name = "xmlName",  required = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mountain", fetch=FetchType.EAGER)
    private List<Summit> summits = new ArrayList<>();

    public Mountain(String name) {
        this.name = name;
    }

    public Mountain() {
    }
}
