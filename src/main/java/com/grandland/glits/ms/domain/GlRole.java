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
    private String name;    //机器角色名称,相当于进程名称

    @Column(name = "display_name")
    private String displayName; //显示名称

    private boolean running = true; //是否运行

    @Column(name = "auto_start")
    private boolean autoRetart = false; //是否自动重启

    private String script;  //程序主脚本,用于启动,停止,重启任务

    @Enumerated(EnumType.STRING)
    private PsCategory category;

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

    //通过进程类别来决定获取PID的方法
    public enum PsCategory{
        SYS("系统程序"),    //系统安装程序
        JAVA("Java程序"),   //平台编写Java程序
        AGENT("监控程序");  //客户端代理进程

        String name;
        PsCategory(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isAutoRetart() {
        return autoRetart;
    }

    public void setAutoRetart(boolean autoRetart) {
        this.autoRetart = autoRetart;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public PsCategory getCategory() {
        return category;
    }

    public void setCategory(PsCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "GlRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", running=" + running +
                ", autoRetart=" + autoRetart +
                ", script='" + script + '\'' +
                ", category=" + category +
                '}';
    }
}
