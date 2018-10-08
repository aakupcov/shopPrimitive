package by.anjei.shop.db.util.helpers;

import by.anjei.shop.db.util.DBManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class DBSQLQueries {

    private DBSQLQueries () {

    }
	
	public static void createDefaultTables(DBManager dbManager) throws SQLException {
        File file = new File("src/main/resources/sql/default_tables.sql");
        List<String> queries = getQueries(file);
        PreparedStatement ps = null;
        for (String query : queries) {
            ps = dbManager.executeQuery(query);
            ps.executeUpdate();
        }
	}

    private static List<String> getQueries(File file) {
        List<String> queries = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                queries.add(scanner.next() + ";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return queries;
    }


}
