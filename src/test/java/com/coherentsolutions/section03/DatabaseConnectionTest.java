package com.coherentsolutions.section03;

import com.coherentsolutions.chapter1.section03.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AppConfig.class)
public class DatabaseConnectionTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        assertNotNull(dataSource);
        try (Connection connection = dataSource.getConnection()) {
            assertFalse(connection.isClosed());
        } catch (SQLException ex) {
            fail("Should have connected to the database", ex);
        }
    }
}
