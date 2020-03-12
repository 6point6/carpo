package net.sixpointsix.carpo.common.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.sixpointsix.carpo.common.jackson.deserialize.PropertyDeserializer;
import net.sixpointsix.carpo.common.jackson.serialize.PropertyCollectionSerializer;
import net.sixpointsix.carpo.common.jackson.serialize.PropertySerializer;
import net.sixpointsix.carpo.common.jackson.serialize.TimestampSerializer;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;

/**
 * Build a jackson module to load into the object mapper
 *
 * @author Andrew Tarry
 * @since 0.5.0
 */
public class CarpoModuleBuilder {

    /**
     * Get the carpo jackson module to configure serialization
     * @return Jacksno module
     */
    public static Module getCarpoModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(PropertyCollection.class, new PropertyDeserializer());
        simpleModule.addSerializer(Property.class, new PropertySerializer());
        simpleModule.addSerializer(PropertyCollection.class, new PropertyCollectionSerializer());
        simpleModule.addSerializer(Timestamp.class, new TimestampSerializer());

        return simpleModule;
    }
}
