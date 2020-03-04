package net.sixpointsix.carpo.test.extension;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class JdbiParameterResolver implements ParameterResolver {
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
        return parameterContext.getParameter().getType().equals(Jdbi.class);
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
            Jdbi jdbi = Jdbi.create(DatabaseManagementExtension.getConnection());
            jdbi.installPlugin(new SqlObjectPlugin());

            return jdbi;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
