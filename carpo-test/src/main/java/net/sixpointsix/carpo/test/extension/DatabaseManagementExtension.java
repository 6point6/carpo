package net.sixpointsix.carpo.test.extension;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database extension to create the connection and interact with the database
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class DatabaseManagementExtension implements BeforeAllCallback, AfterAllCallback, ParameterResolver {

    private static Connection connection;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(getConnection()));
        Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
        Contexts context = null;
        liquibase.update(context);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Determine if this resolver supports resolution of an argument for the
     * {@link Parameter} in the supplied {@link ParameterContext} for the supplied
     * {@link ExtensionContext}.
     *
     * <p>The {@link Method} or {@link Constructor}
     * in which the parameter is declared can be retrieved via
     * {@link ParameterContext#getDeclaringExecutable()}.
     *
     * @param parameterContext the context for the parameter for which an argument should
     *                         be resolved; never {@code null}
     * @param extensionContext the extension context for the {@code Executable}
     *                         about to be invoked; never {@code null}
     * @return {@code true} if this resolver can resolve an argument for the parameter
     * @see #resolveParameter
     * @see ParameterContext
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Connection.class);
    }

    /**
     * Resolve an argument for the {@link Parameter} in the supplied {@link ParameterContext}
     * for the supplied {@link ExtensionContext}.
     *
     * <p>This method is only called by the framework if {@link #supportsParameter}
     * previously returned {@code true} for the same {@link ParameterContext}
     * and {@link ExtensionContext}.
     *
     * <p>The {@link Method} or {@link Constructor}
     * in which the parameter is declared can be retrieved via
     * {@link ParameterContext#getDeclaringExecutable()}.
     *
     * @param parameterContext the context for the parameter for which an argument should
     *                         be resolved; never {@code null}
     * @param extensionContext the extension context for the {@code Executable}
     *                         about to be invoked; never {@code null}
     * @return the resolved argument for the parameter; may only be {@code null} if the
     * parameter type is not a primitive
     * @see #supportsParameter
     * @see ParameterContext
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        try {
            return getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the database connection
     *
     * @return connection object
     * @throws SQLException on database error
     */
    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            final String jdbcUrl = System.getProperty("jdbcUrl");
            final String jdbcUsername = System.getProperty("jdbcUsername");
            final String jdbcPassword = System.getProperty("jdbcPassword");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        }

        return connection;
    }
}
