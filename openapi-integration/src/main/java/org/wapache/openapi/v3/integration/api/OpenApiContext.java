package org.wapache.openapi.v3.integration.api;

import org.wapache.openapi.v3.core.converter.ModelConverter;
import org.wapache.openapi.v3.integration.OpenApiConfigurationException;
import org.wapache.openapi.v3.models.OpenAPI;

import java.util.Set;

public interface OpenApiContext {

    String OPENAPI_CONTEXT_ID_KEY = "openapi.context.id";
    String OPENAPI_CONTEXT_ID_PREFIX = OPENAPI_CONTEXT_ID_KEY + ".";
    String OPENAPI_CONTEXT_ID_DEFAULT = OPENAPI_CONTEXT_ID_PREFIX + "default";

    String getId();

    OpenApiContext init() throws OpenApiConfigurationException;

    OpenAPI read();

    OpenAPIConfiguration getOpenApiConfiguration();

    String getConfigLocation();

    OpenApiContext getParent();

    void setOpenApiScanner(OpenApiScanner openApiScanner);

    void setOpenApiReader(OpenApiReader openApiReader);

    /**
     * @since 2.0.6
     */
    void setObjectMapperProcessor(ObjectMapperProcessor objectMapperProcessor);

    /**
     * @since 2.0.6
     */
    void setModelConverters(Set<ModelConverter> modelConverters);

}
