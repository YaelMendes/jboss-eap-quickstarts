package org.jboss.as.quickstarts.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@NamedQueries({
        @NamedQuery(name = Mountain.FIND_ALL, query = "select m from Mountain m")
})
public class Mountain implements Serializable {

    public static final String FIND_ALL = "allMountainsSQL";

    @Id
    @NotNull
    //@XmlAttribute(name = "xmlName",  required = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mountain", fetch=FetchType.EAGER, orphanRemoval=true)
    private List<Summit> summits = new ArrayList<>();

    @ManyToMany(mappedBy = "mountains")
    private List<Country> countries;

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
