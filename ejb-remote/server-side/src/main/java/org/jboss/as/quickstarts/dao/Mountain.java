package org.jboss.as.quickstarts.dao;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mountain {

    @Id
    @NotNull
    private String name;

    @OneToMany
    private List<Summit> summits = new ArrayList<>();

}
