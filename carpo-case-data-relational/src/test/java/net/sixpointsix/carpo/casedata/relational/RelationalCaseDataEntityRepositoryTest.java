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
import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.test.extension.DatabaseManagementExtension;
import net.sixpointsix.carpo.test.extension.JdbiParameterResolver;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith({DatabaseManagementExtension.class, JdbiParameterResolver.class})
class RelationalCaseDataEntityRepositoryTest {

    private RelationalCaseDataEntityRepository relationalCaseDataEntityRepository;

    @BeforeEach
    void setUp(Jdbi jdbi) {
        relationalCaseDataEntityRepository = new RelationalCaseDataEntityRepository(jdbi);
    }

    @Test
    void insert() {
        UUID id = UUID.randomUUID();
        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);
        builder.addProperty(ImmutableProperty.build("a", "b"));
        builder.addProperty(ImmutableProperty.build("b", 1));
        builder.addProperty(ImmutableProperty.build("c", 2.2));
        builder.addProperty(ImmutableProperty.build("d", true));

        CarpoCase carpoCase = builder.build();
        relationalCaseDataEntityRepository.create(carpoCase);

        Optional<CarpoCase> saved = relationalCaseDataEntityRepository.getById(id.toString());

        assertTrue(saved.isPresent());

        CarpoCase savedCase = saved.get();
        assertEquals(id.toString(), savedCase.getCarpoId());
        assertEquals(4, savedCase.getProperties().size());
        assertEquals("b", savedCase.getProperties().getStringByKey("a").get());
        assertEquals(1, savedCase.getProperties().getLongByKey("b").get());
        assertEquals(2.2, savedCase.getProperties().getDoubleByKey("c").get());
        assertTrue(savedCase.getProperties().getBooleanByKey("d").get());
    }
}