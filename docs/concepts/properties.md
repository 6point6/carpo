# Carpo Properties

Defining a flexible data structure for cases is difficult to manage and Java does not help. Cases need to have the ability to use different data structure for different use cases and so flexible properties were the only option.

The approach to properties is similar to a [Union type](https://en.wikipedia.org/wiki/Union_type) that some languages offer.

!!!info "The Golden Rule"
    
    A property has a key and a value. The key is a string but the value could be a string, a number, a boolean, an object or a list. 
    
    It can only have one value and should be immutable.
    
    
The approach to serialisation, persistence and storage will depend on the destination system and this approach may lead to performance issues but in order to archive flexibility the property method was needed.

## The Property Interface

```java
public interface Property {

    String getKey();

    Boolean isNull();

    Optional<String> getStringValue();
    
    Optional<Long> getLongValue();

    Optional<Double> getDoubleValue();

    Optional<Boolean> getBooleanValue();

    <T> Optional<T> getObjectValue(Class<T> type);

    <T> List<T> getListValue(Class<T> type);

    Boolean hasStringValue();

    Boolean hasLongValue();

    Boolean hasDoubleValue();

    Boolean hasBooleanValue();

    Boolean hasObjectValue();

    Boolean hasListValue();
}
```

The property interface has `get` and `has` methods for the different data types. Only one data type can be held at a time and all the gets use `Optional` in order to avoid `null`.

!!!danger "Introducing Mutability"
    
    Properities are a core design aspect of Carpo but they are coded to an interface to allow flexibility. The `carpo-common` module provides the interface and an immutable implementation.
    
    It is possible to implement the `Property` interface with mutability and multiple values but that may cause unexpected effects in the system. If you really need mutability or multiple values then raise a feature request to explain the need.