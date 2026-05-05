package com.daw_project.Model;
public class ProjectDO {

    private int idPro;
    private String title;
    private String descr;
    private String url;
    private int dif;
    private String theme;
    private boolean updated;

    public ProjectDO() {}

    public ProjectDO(int idPro, String title, String descr, String url, int dif, String theme, boolean updated) {
        this.idPro = idPro;
        this.title = title;
        this.descr = descr;
        this.url = url;
        this.dif = dif;
        this.theme = theme;
        this.updated = updated;
    }

    // Getters
    public int getIdPro() { return idPro; }
    public String getTitle() { return title; }
    public String getDescr() { return descr; }
    public String getUrl() { return url; }
    public int getDif() { return dif; }
    public String getTheme() { return theme; }
    public Boolean getUpdated() { return updated; }

    // Setters
    public void setIdPro(int idPro) { this.idPro = idPro; }
    public void setTitle(String title) { this.title = title; }
    public void setDescr(String descr) { this.descr = descr; }
    public void setUrl(String url) { this.url = url; }
    public void setDif(int dif) { this.dif = dif; }
    public void setTheme(String theme) { this.theme = theme; }
    public void setUpdated(Boolean updated) { this.updated = updated; }
    
    @Override
    public String toString() {
        return "ProjectDO{" +
                "idPro=" + idPro +
                ", title='" + title + '\'' +
                ", descr='" + descr + '\'' +
                ", url='" + url + '\'' +
                ", theme='" + theme + '\'' +
                ", dif='" + dif + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}