package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.serialization.exception.NoSerializerException;
import net.sixpointsix.carpo.serialization.persistence.serializer.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serialization manager to be applied to the properties
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class PlugableSerializationManager implements PersistenceSerializationManger {

    private final List<PersistenceSerializer> persistenceSerializers;

    public static PersistenceSerializationManger buildWithDefaultSerializers() {
        return new PlugableSerializationManager(
                List.of(
                        new JavaSerializer(),
                        new StringSerializer(),
                        new LongSerializer(),
                        new DoubleSerializer(),
                        new NullSerializer(),
                        new JavaListSerializer()
                )
        );
    }

    /**
     * Build the serializer
     * @param persistenceSerializers serializers to be used
     */
    public PlugableSerializationManager(List<PersistenceSerializer> persistenceSerializers) {
        this.persistenceSerializers = persistenceSerializers
                .stream()
                .sorted(Comparator.comparing(PersistenceSerializer::getPriority))
                .collect(Collectors.toList());
    }

    @Override
    public SerializedProperty serializeProperty(Property property) {
        for (PersistenceSerializer p: persistenceSerializers) {
            if(p.canSerialize(property)) {
                return p.serialize(property);
            }
        }

        throw new NoSerializerException("Unable to serialize property of type " + property.getType());
    }
}
