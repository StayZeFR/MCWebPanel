package fr.stayze.database.tables;

import java.util.HashMap;

public class PERMISSION extends Table {

    public PERMISSION(HashMap<String, Object> rows) {
        super(rows);
    }

    public int getID() { return (int) this.rows.get("ID"); }
    public String getName() { return (String) this.rows.get("NAME"); }

    public void setID(int ID) { this.rows.put("ID", ID); }
    public void setName(String NAME) { this.rows.put("NAME", NAME); }

}
