package fr.stayze.database.tables;

import java.util.HashMap;

public class USER extends Table {

    public USER(HashMap<String, Object> rows) {
        super(rows);
    }

    public int getID() { return (int) this.rows.get("ID"); }
    public String getUSERNAME() { return (String) this.rows.get("USERNAME"); }
    public String getEMAIL() { return (String) this.rows.get("EMAIL"); }
    public String getPASSWORD() { return (String) this.rows.get("PASSWORD"); }
    public int getENABLED() { return (int) this.rows.get("ENABLED"); }
    public String getCREATED() { return (String) this.rows.get("CREATED"); }

    public void setID(int ID) { this.rows.put("ID", ID); }
    public void setUSERNAME(String USERNAME) { this.rows.put("USERNAME", USERNAME); }
    public void setEMAIL(String EMAIL) { this.rows.put("EMAIL", EMAIL); }
    public void setPASSWORD(String PASSWORD) { this.rows.put("PASSWORD", PASSWORD); }
    public void setENABLED(int ENABLED) { this.rows.put("ENABLED", ENABLED); }
    public void setCREATED(String CREATED) { this.rows.put("CREATED", CREATED); }

}
