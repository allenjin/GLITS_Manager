package com.grandland.glits.ms.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * GlRole
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "gl_roles")
public class GlRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;    //机器角色名称

    @OneToMany(mappedBy = "role")
    private Set<GlCommand> commands;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private GlService service;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch= FetchType.LAZY)
    @JoinTable(name = "gl_host_role",
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "host_id", referencedColumnName = "id")})
    private Set<GlHost> hosts;

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

    public Set getCommands() {
        return commands;
    }

    public void setCommands(Set commands) {
        this.commands = commands;
    }

    public GlService getService() {
        return service;
    }

    public void setService(GlService service) {
        this.service = service;
    }

    public Set getHosts() {
        return hosts;
    }

    public void setHosts(Set hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "GlRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commands=" + commands +
                ", service=" + service +
                ", hosts=" + hosts +
                '}';
    }
}
