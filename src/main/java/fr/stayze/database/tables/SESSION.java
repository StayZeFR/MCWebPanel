package fr.stayze.database.tables;

import java.util.HashMap;

public class SESSION extends Table {

    public SESSION(HashMap<String, Object> rows) {
        super(rows);
    }

    public int getIdUser() { return (int) this.rows.get("ID_USER"); }
    public String getToken() { return (String) this.rows.get("TOKEN"); }
    public String getExpirationDate() { return (String) this.rows.get("EXPIRATION_DATE"); }

    public void setIdUser(int ID_USER) { this.rows.put("ID_USER", ID_USER); }
    public void setToken(String TOKEN) { this.rows.put("TOKEN", TOKEN); }
    public void setExpirationDate(String EXPIRATION_DATE) { this.rows.put("EXPIRATION_DATE", EXPIRATION_DATE); }

}
