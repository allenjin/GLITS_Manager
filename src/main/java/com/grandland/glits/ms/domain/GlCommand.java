package com.grandland.glits.ms.domain;

import javax.persistence.*;

/**
 * GlCommand
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "gl_commands")
public class GlCommand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;    //命令名称

    @Column(name = "script_path", nullable = false)
    private String scriptPath;  //命令脚本路径

    private boolean actived = true;    //可用

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CommandType type;   //命令类型

    private String meta;    //命令参数

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private GlRole role;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private GlService service;

    public enum CommandType{
        START,      //启动
        STOP,       //停止
        RESTART,    //重启
        CHECK,      //检测
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GlRole getRole() {
        return role;
    }

    public void setRole(GlRole role) {
        this.role = role;
    }

    public GlService getService() {
        return service;
    }

    public void setService(GlService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "GlCommand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scriptPath='" + scriptPath + '\'' +
                ", actived=" + actived +
                ", type=" + type +
                ", meta='" + meta + '\'' +
                ", role=" + role +
                ", service=" + service +
                '}';
    }
}
