package com.grandland.glits.ms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * GlService
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "gl_services")
public class GlService {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "display_name", nullable = false)
    private String displayName; //显示名称

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<GlRole> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<GlCommand> commands;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public Set getCommands() {
        return commands;
    }

    public void setCommands(Set commands) {
        this.commands = commands;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GlService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName=" + displayName +
                ", description=" + description +
                '}';
    }
}
