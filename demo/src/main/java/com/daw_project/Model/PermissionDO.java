public class PermissionDO {

    private int idPer;
    private int fkPro;
    private int fkU;
    private int per;

    public PermissionDO() {}

    public PermissionDO(int idPer, int fkPro, int fkU, int per) {
        this.idPer = idPer;
        this.fkPro = fkPro;
        this.fkU = fkU;
        this.per = per;
    }

    // Getters
    public int getIdPer() { return idPer; }
    public int getFkPro() { return fkPro; }
    public int getFkU() { return fkU; }
    public int getPer() { return per; }

    // Setters
    public void setIdPer(int idPer) { this.idPer = idPer; }
    public void setFkPro(int fkPro) { this.fkPro = fkPro; }
    public void setFkU(int fkU) { this.fkU = fkU; }
    public void setPer(int per) { this.per = per; }

    @Override
    public String toString() {
        return "PermissionDO{" +
                "idPer=" + idPer +
                ", fkPro=" + fkPro +
                ", fkU=" + fkU +
                ", per=" + per +
                '}';
    }
}