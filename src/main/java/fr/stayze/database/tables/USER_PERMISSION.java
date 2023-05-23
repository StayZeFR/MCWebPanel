package fr.stayze.database.tables;

import java.util.HashMap;

public class USER_PERMISSION extends Table {

    public USER_PERMISSION(HashMap<String, Object> rows) {
        super(rows);
    }

    public int getIdUser() { return (int) this.rows.get("ID_USER"); }
    public int getIdPerm() { return (int) this.rows.get("ID_PERM"); }

    public void setIdUser(int ID_USER) { this.rows.put("ID_USER", ID_USER); }
    public void setIdPerm(int ID_PERM) { this.rows.put("ID_PERM", ID_PERM); }

}
