package org.wapache.openapi.v3.integration.api;

import java.util.Map;
import java.util.Set;

public interface OpenApiScanner {

    void setConfiguration(OpenAPIConfiguration openApiConfiguration);

    Set<Class<?>> classes();

    Map<String, Object> resources();

}
