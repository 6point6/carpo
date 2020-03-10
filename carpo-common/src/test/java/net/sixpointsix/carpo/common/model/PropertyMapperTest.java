package net.sixpointsix.carpo.common.model;

import net.sixpointsix.carpo.common.model.immutable.AbstractImmutableCarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyMapperTest {

    public static class ExampleNestedObject {
        private Object a;

        public ExampleNestedObject(Object a) {
            this.a = a;
        }

        public Object getA() {
            return a;
        }
    }

    public static class ExampleStringObject {
        private String a;

        public ExampleStringObject(String a) {
            this.a = a;
        }

        public String getA() {
            return a;
        }
    }

    private static class ExampleEntity extends AbstractImmutableCarpoPropertyEntity {

        public ExampleEntity(String carpoId, Timestamp timestamp, PropertyCollection properties) {
            super(carpoId, timestamp, properties);
        }
    }

    private static class ExampleBuilder extends AbstractPropertyEntityBuilder<PropertyMapperTest.ExampleEntity> {

        @Override
        public PropertyMapperTest.ExampleEntity build() {
            return new ExampleEntity(carpoId, timestamp, properties);
        }
    }

    @Test
    void shouldReturnNestedValue() {
        String id = UUID.randomUUID().toString();
        Timestamp timestamp = ImmutableTimestamp.build();
        Property tertiaryProperty = ImmutableProperty.build("c", "d");
        Property secondaryProperty = ImmutableProperty.build("b", tertiaryProperty);
        PropertyMapperTest.ExampleBuilder builder = new ExampleBuilder();
        builder.setCarpoId(id);
        builder.setTimestamp(timestamp);
        builder.addProperty(ImmutableProperty.build("a", secondaryProperty));
        PropertyMapperTest.ExampleEntity exampleEntity = builder.build();
        exampleEntity.getProperties();
        PropertyMapper mapper = new PropertyMapper();
        assertEquals(mapper.getStringValue(exampleEntity, "a.b.c").get(), "d");
        assertEquals(mapper.getStringValue(exampleEntity, "a.b.c.d.e").get(), "");
    }

}
