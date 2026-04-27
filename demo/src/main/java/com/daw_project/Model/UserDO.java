public class UserDO {

    private int idU;
    private String nameTag;
    private String name;
    private String mail;
    private String pass;

    public UserDO() {}

    public UserDO(int idU, String nameTag, String name, String mail, String pass) {
        this.idU = idU;
        this.nameTag = nameTag;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    // Getters
    public int getIdU() { return idU; }
    public String getNameTag() { return nameTag; }
    public String getName() { return name; }
    public String getMail() { return mail; }
    public String getPass() { return pass; }

    // Setters
    public void setIdU(int idU) { this.idU = idU; }
    public void setNameTag(String nameTag) { this.nameTag = nameTag; }
    public void setName(String name) { this.name = name; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPass(String pass) { this.pass = pass; }

    @Override
    public String toString() {
        return "UserDO{" +
                "idU=" + idU +
                ", nameTag='" + nameTag + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}