public class ProjectDO {

    private int idPro;
    private String title;
    private String descr;
    private String url;

    public ProjectDO() {}

    public ProjectDO(int idPro, String title, String descr, String url) {
        this.idPro = idPro;
        this.title = title;
        this.descr = descr;
        this.url = url;
    }

    // Getters
    public int getIdPro() { return idPro; }
    public String getTitle() { return title; }
    public String getDescr() { return descr; }
    public String getUrl() { return url; }

    // Setters
    public void setIdPro(int idPro) { this.idPro = idPro; }
    public void setTitle(String title) { this.title = title; }
    public void setDescr(String descr) { this.descr = descr; }
    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "ProjectDO{" +
                "idPro=" + idPro +
                ", title='" + title + '\'' +
                ", descr='" + descr + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}