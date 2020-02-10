package net.sixpointsix.carpo.common.model;

import net.sixpointsix.carpo.common.model.immutable.AbstractImmutableCarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPropertyEntityBuilderTest {

    private static class ExampleEntity extends AbstractImmutableCarpoPropertyEntity {

        public ExampleEntity(String carpoId, Timestamp timestamp, PropertyCollection properties) {
            super(carpoId, timestamp, properties);
        }
    }

    private static class ExampleBuilder extends AbstractPropertyEntityBuilder<ExampleEntity> {

        @Override
        public ExampleEntity build() {
            return new ExampleEntity(carpoId, timestamp, properties);
        }
    }

    @Test
    void buildWithNoValues() {
        ExampleBuilder builder = new ExampleBuilder();

        ExampleEntity exampleEntity = builder.build();

        assertNull(exampleEntity.getCarpoId());
        assertNull(exampleEntity.getTimestamp());
        assertTrue(exampleEntity.getProperties().isEmpty());
    }

    @Test
    void buildWithWithValues() {
        String id = UUID.randomUUID().toString();
        Timestamp timestamp = ImmutableTimestamp.build();

        ExampleBuilder builder = new ExampleBuilder();
        builder.setCarpoId(id);
        builder.setTimestamp(timestamp);
        builder.addProperty(ImmutableProperty.build("a", "b"));

        ExampleEntity exampleEntity = builder.build();

        assertEquals(id, exampleEntity.getCarpoId());
        assertEquals(timestamp, exampleEntity.getTimestamp());
        assertFalse(exampleEntity.getProperties().isEmpty());
    }

    @Test
    void buildWithWithNowTimestamp() {
        String id = UUID.randomUUID().toString();

        ExampleBuilder builder = new ExampleBuilder();
        builder.setCarpoId(id);
        builder.setTimestampToNow();
        builder.addProperty(ImmutableProperty.build("a", "b"));

        ExampleEntity exampleEntity = builder.build();

        assertEquals(id, exampleEntity.getCarpoId());
        assertNotNull(exampleEntity.getTimestamp());
        assertFalse(exampleEntity.getProperties().isEmpty());
    }
}