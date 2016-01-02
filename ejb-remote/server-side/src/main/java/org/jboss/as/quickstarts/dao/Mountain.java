package org.jboss.as.quickstarts.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
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

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long version = 0L;

    public Mountain(String name) {
        this.name = name;
    }

    public Mountain() {
    }
}
