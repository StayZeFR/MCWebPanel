package fr.stayze.database.tables;

import java.util.HashMap;

public class Table {

    protected HashMap<String, Object> rows;

    public Table(HashMap<String, Object> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName().toUpperCase() + " : ");
        for (String key : this.rows.keySet()) {
            str.append("\n   ").append(key).append(" : ").append(this.rows.get(key));
        }
        return str.toString();
    }

}
