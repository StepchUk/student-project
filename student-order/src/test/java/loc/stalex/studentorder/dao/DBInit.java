package loc.stalex.studentorder.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DBInit {

    public static void startUp() throws Exception {
        URL initDb = DictionaryDaoImplTest.class.getClassLoader().getResource("initdb.sql");
        URL insertData = DictionaryDaoImplTest.class.getClassLoader().getResource("insertdata.sql");

        List<String> initDbStr = Files.readAllLines(Paths.get(initDb.toURI()));
        String initDbSql = initDbStr.stream().collect(Collectors.joining());

        List<String> insertDataStr = Files.readAllLines(Paths.get(insertData.toURI()));
        String insertDataSql = insertDataStr.stream().collect(Collectors.joining());

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(initDbSql);
            statement.executeUpdate(insertDataSql);
        }
    }
}
