package fr.stayze.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityManager {

    private final Connection database;

    public EntityManager(Connection connection) {
        this.database = connection;
    }

    public <T> List<T> getAll(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(HashMap.class);
            List<T> list = new ArrayList<T>();
            String sql = "SELECT * FROM " + clazz.getSimpleName().toUpperCase() + ";";
            ResultSet data = this.database.createStatement().executeQuery(sql);
            while (data.next()) {
                list.add(constructor.newInstance(this.convertToHashMap(data)));
            }
            return list;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Object> convertToHashMap(ResultSet resultSet) throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String name = metaData.getColumnName(i);
            Object value = resultSet.getObject(i);
            result.put(name, value);
        }
        return result;
    }

}
