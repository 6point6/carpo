package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.model.PropertyType;

public @interface RequiredProperty {

    String key();

    PropertyType propertyType() default PropertyType.UNKNOWN;
}
