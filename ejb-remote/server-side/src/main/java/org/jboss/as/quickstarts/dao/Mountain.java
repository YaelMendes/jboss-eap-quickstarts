package org.jboss.as.quickstarts.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Mountain {

    @Id
    @NotNull
    private String name;

    @OneToMany
    private List<Summit> summits = new ArrayList<>();

    public Mountain(String name) {
        this.name = name;
    }

    public Mountain() {
    }
}
