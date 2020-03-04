package net.sixpointsix.carpo.casedata.relational;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.sdk.Context;
import net.sixpointsix.carpo.test.extension.DatabaseManagementExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith(DatabaseManagementExtension.class)
class RelationalCaseDataEntityRepositoryTest {

//    @BeforeAll
//    static void beforeAll() throws SQLException, LiquibaseException {
//        Connection connection = DriverManager.getConnection("jdbc:h2:mem:carpo", "sa", "sa");
//        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
//        Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
//        Contexts context = null;
//        liquibase.update(context);
//    }

    @Test
    void name() {
        assertEquals(true, true);
    }
}