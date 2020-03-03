# Carpo Serialization

In order to store and transmit data stored in properties we need to serialize. The actual serializers are configurable 
to meet the needs of the backend that will accept the data. 

## Persistence

The serializer works as below

```java
public interface PersistenceSerializationManger {

    SerializedProperty serializeProperty(Property property);
}
```

The output data is a byte array that can store the data in the most generic way

```java

public interface SerializedProperty {

    /**
     * Get the type of the entity
     * @return Property Type
     */
    PropertyType getType();

    /**
     * Get the property key
     * @return Key of the property
     */
    String getKey();

    /**
     * Get the data of the entity
     * @return object data
     */
    byte[] getData();
}
```