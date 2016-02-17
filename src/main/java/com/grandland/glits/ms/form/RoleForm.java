package com.grandland.glits.ms.form;

import com.grandland.glits.ms.domain.GlRole;

/**
 * RoleForm
 *
 * @author Allen Jin
 * @date 2016/02/17
 */
public class RoleForm {
    private boolean updated = false;
    private int id;
    private String name;
    private String displayName;
    private String description;
    private String script;
    private GlRole.PsCategory psCategory;
    private int serviceId;
    private boolean running = true;
    private boolean autoRestart = false;

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public GlRole.PsCategory getPsCategory() {
        return psCategory;
    }

    public void setPsCategory(GlRole.PsCategory psCategory) {
        this.psCategory = psCategory;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isAutoRestart() {
        return autoRestart;
    }

    public void setAutoRestart(boolean autoRestart) {
        this.autoRestart = autoRestart;
    }

    @Override
    public String toString() {
        return "RoleForm{" +
                "updated=" + updated +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", script='" + script + '\'' +
                ", psCategory=" + psCategory +
                ", serviceId=" + serviceId +
                ", running=" + running +
                ", autoRestart=" + autoRestart +
                '}';
    }
}
