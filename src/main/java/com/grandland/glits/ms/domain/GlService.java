package com.grandland.glits.ms.domain;

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

    @OneToMany(mappedBy = "service")
    private Set<GlRole> roles;

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

    @Override
    public String toString() {
        return "GlService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", commands=" + commands +
                '}';
    }
}
