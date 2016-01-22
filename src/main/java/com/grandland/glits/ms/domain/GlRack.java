package com.grandland.glits.ms.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * GlRack
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "gl_racks")
public class GlRack {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "rack_name", nullable = false, unique = true)
    private String rackName;    //机架名称

    private String  description;    //机架描述,具体物理位置

    @OneToMany(mappedBy = "rack", fetch = FetchType.EAGER)
    private Set<GlHost> hosts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRackName() {
        return rackName;
    }

    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set getHosts() {
        return hosts;
    }

    public void setHosts(Set hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "GlRack{" +
                "id=" + id +
                ", rackName='" + rackName + '\'' +
                ", description='" + description + '\'' +
                ", hosts=" + hosts +
                '}';
    }
}
