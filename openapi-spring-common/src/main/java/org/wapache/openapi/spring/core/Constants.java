/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.wapache.openapi.spring.core;

import org.springframework.util.ResourceUtils;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;

/**
 * The type Constants.
 * @author bnasslahsen
 */
public final class Constants {

	/**
	 * The constant SPRINGDOC_PREFIX.
	 */
	public static final String SPRINGDOC_PREFIX = "springdoc";

	/**
	 * The constant DEFAULT_API_DOCS_URL.
	 */
	public static final String DEFAULT_API_DOCS_URL = "/v3/api-docs";

	/**
	 * The constant DEFAULT_SERVER_DESCRIPTION.
	 */
	public static final String DEFAULT_SERVER_DESCRIPTION = "Generated server url";

	/**
	 * The constant API_DOCS_URL.
	 */
	public static final String API_DOCS_URL = "${springdoc.api-docs.path:#{T(org.wapache.openapi.spring.core.Constants).DEFAULT_API_DOCS_URL}}";

	/**
	 * The constant SWAGGGER_CONFIG_FILE.
	 */
	public static final String SWAGGGER_CONFIG_FILE = "swagger-config";

	/**
	 * The constant SWAGGER_CONFIG_URL.
	 */
	public static final String SWAGGER_CONFIG_URL = API_DOCS_URL + DEFAULT_PATH_SEPARATOR + SWAGGGER_CONFIG_FILE;

	/**
	 * The constant DEFAULT_API_DOCS_URL_YAML.
	 */
	public static final String DEFAULT_API_DOCS_URL_YAML = API_DOCS_URL + ".yaml";

	/**
	 * The constant SPRINGDOC_ENABLED.
	 */
	public static final String SPRINGDOC_ENABLED = "springdoc.api-docs.enabled";

	/**
	 * The constant SPRINGDOC_DEPRECATING_CONVERTER_ENABLED.
	 */
	public static final String SPRINGDOC_DEPRECATING_CONVERTER_ENABLED = "springdoc.model-converters.deprecating-converter.enabled";

	/**
	 * The constant SPRINGDOC_SCHEMA_RESOLVE_PROPERTIES.
	 */
	public static final String SPRINGDOC_SCHEMA_RESOLVE_PROPERTIES = "springdoc.api-docs.resolve-schema-properties";

	/**
	 * The constant SPRINGDOC_SHOW_LOGIN_ENDPOINT.
	 */
	public static final String SPRINGDOC_SHOW_LOGIN_ENDPOINT = "springdoc.show-login-endpoint";

	/**
	 * The constant SPRINGDOC_CACHE_DISABLED.
	 */
	public static final String SPRINGDOC_CACHE_DISABLED = "springdoc.cache.disabled";

	/**
	 * The constant SPRINGDOC_SWAGGER_UI_ENABLED.
	 */
	public static final String SPRINGDOC_SWAGGER_UI_ENABLED = "springdoc.swagger-ui.enabled";

	/**
	 * The constant NULL.
	 */
	public static final String NULL = ":#{null}";

	/**
	 * The constant MVC_SERVLET_PATH.
	 */
	public static final String MVC_SERVLET_PATH = "${spring.mvc.servlet.path" + NULL + "}";

	/**
	 * The constant SPRINGDOC_SHOW_ACTUATOR.
	 */
	public static final String SPRINGDOC_SHOW_ACTUATOR = "springdoc.show-actuator";

	/**
	 * The constant SPRINGDOC_ACTUATOR_TAG.
	 */
	public static final String SPRINGDOC_ACTUATOR_TAG = "Actuator";

	/**
	 * The constant SPRINGDOC_ACTUATOR_DESCRIPTION.
	 */
	public static final String SPRINGDOC_ACTUATOR_DESCRIPTION = "Monitor and interact";

	/**
	 * The constant SPRINGDOC_ACTUATOR_DOC_URL.
	 */
	public static final String SPRINGDOC_ACTUATOR_DOC_URL = "https://docs.spring.io/spring-boot/docs/current/actuator-api/html/";

	/**
	 * The constant SPRINGDOC_ACTUATOR_DOC_DESCRIPTION.
	 */
	public static final String SPRINGDOC_ACTUATOR_DOC_DESCRIPTION = "Spring Boot Actuator Web API Documentation";

	/**
	 * The constant DEFAULT_WEB_JARS_PREFIX_URL.
	 */
	public static final String DEFAULT_WEB_JARS_PREFIX_URL = "/webjars";

	/**
	 * The constant CLASSPATH_RESOURCE_LOCATION.
	 */
	public static final String CLASSPATH_RESOURCE_LOCATION = ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources";

	/**
	 * The constant SWAGGER_UI_URL.
	 */
	public static final String SWAGGER_UI_URL = "/swagger-ui/index.html";

	/**
	 * The constant SWAGGER_UI_OAUTH_REDIRECT_URL.
	 */
	public static final String SWAGGER_UI_OAUTH_REDIRECT_URL = "/swagger-ui/oauth2-redirect.html";

	/**
	 * The constant APPLICATION_OPENAPI_YAML.
	 */
	public static final String APPLICATION_OPENAPI_YAML = "application/vnd.oai.swagger";

	/**
	 * The constant DEFAULT_SWAGGER_UI_PATH.
	 */
	public static final String DEFAULT_SWAGGER_UI_PATH = DEFAULT_PATH_SEPARATOR + "swagger-ui.html";

	/**
	 * The constant SWAGGER_UI_PATH.
	 */
	public static final String SWAGGER_UI_PATH = "${springdoc.swagger-ui.path:#{T(org.wapache.openapi.spring.core.Constants).DEFAULT_SWAGGER_UI_PATH}}";

	/**
	 * The constant DEFAULT_GROUP_NAME.
	 */
	public static final String DEFAULT_GROUP_NAME = "springdocDefault";

	/**
	 * The constant GROUP_CONFIG_FIRST_PROPERTY.
	 */
	public static final String GROUP_CONFIG_FIRST_PROPERTY = "springdoc.group-configs[0].group";

	/**
	 * The constant GROUP_NAME_NOT_NULL.
	 */
	public static final String GROUP_NAME_NOT_NULL = "Group name can not be null";

	/**
	 * The constant GET_METHOD.
	 */
	public static final String GET_METHOD = "get";

	/**
	 * The constant POST_METHOD.
	 */
	public static final String POST_METHOD = "post";

	/**
	 * The constant PUT_METHOD.
	 */
	public static final String PUT_METHOD = "put";

	/**
	 * The constant DELETE_METHOD.
	 */
	public static final String DELETE_METHOD = "delete";

	/**
	 * The constant PATCH_METHOD.
	 */
	public static final String PATCH_METHOD = "patch";

	/**
	 * The constant TRACE_METHOD.
	 */
	public static final String TRACE_METHOD = "trace";

	/**
	 * The constant HEAD_METHOD.
	 */
	public static final String HEAD_METHOD = "head";

	/**
	 * The constant OPTIONS_METHOD.
	 */
	public static final String OPTIONS_METHOD = "options";

	/**
	 * The constant QUERY_PARAM.
	 */
	public static final String QUERY_PARAM = "query";

	/**
	 * The constant DEFAULT_DESCRIPTION.
	 */
	public static final String DEFAULT_DESCRIPTION = "default response";

	/**
	 * The constant DEFAULT_TITLE.
	 */
	public static final String DEFAULT_TITLE = "OpenAPI definition";

	/**
	 * The constant DEFAULT_VERSION.
	 */
	public static final String DEFAULT_VERSION = "v0";

	/**
	 * The constant OPENAPI_STRING_TYPE.
	 */
	public static final String OPENAPI_STRING_TYPE = "string";

	/**
	 * The constant OPENAPI_ARRAY_TYPE.
	 */
	public static final String OPENAPI_ARRAY_TYPE = "array";

	/**
	 * The constant GRACEFUL_EXCEPTION_OCCURRED.
	 */
	public static final String GRACEFUL_EXCEPTION_OCCURRED = "Graceful exception occurred";

	/**
	 * The constant SWAGGER_UI_DEFAULT_URL.
	 */
	public static final String SWAGGER_UI_DEFAULT_URL = "https://petstore.swagger.io/v2/swagger.json";

	/**
	 * The constant CSRF_DEFAULT_COOKIE_NAME.
	 */
	public static final String CSRF_DEFAULT_COOKIE_NAME= "XSRF-TOKEN";

	/**
	 * The constant CSRF_DEFAULT_HEADER_NAME.
	 */
	public static final String CSRF_DEFAULT_HEADER_NAME= "X-XSRF-TOKEN";

	/**
	 * Instantiates a new Constants.
	 */
	private Constants() {
		super();
	}

}
