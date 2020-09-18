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

package org.wapache.openapi.spring.webmvc.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.spring.api.OpenApiResourceNotFoundException;
import org.wapache.openapi.spring.core.AbstractRequestBuilder;
import org.wapache.openapi.spring.core.ActuatorProvider;
import org.wapache.openapi.spring.core.GenericResponseBuilder;
import org.wapache.openapi.spring.core.GroupedOpenApi;
import org.wapache.openapi.spring.core.OpenAPIBuilder;
import org.wapache.openapi.spring.core.OperationBuilder;
import org.wapache.openapi.spring.core.RepositoryRestResourceProvider;
import org.wapache.openapi.spring.core.SecurityOAuth2Provider;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.core.SpringDocConfigProperties.GroupConfig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import static org.wapache.openapi.spring.core.Constants.API_DOCS_URL;
import static org.wapache.openapi.spring.core.Constants.APPLICATION_OPENAPI_YAML;
import static org.wapache.openapi.spring.core.Constants.DEFAULT_API_DOCS_URL_YAML;
import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;

/**
 * The type Multiple open api resource.
 * @author bnasslahsen
 */
@RestController
public class MultipleOpenApiResource implements InitializingBean {

	/**
	 * The Grouped open apis.
	 */
	private final List<GroupedOpenApi> groupedOpenApis;

	/**
	 * The Default open api builder.
	 */
	private final ObjectFactory<OpenAPIBuilder> defaultOpenAPIBuilder;

	/**
	 * The Request builder.
	 */
	private final AbstractRequestBuilder requestBuilder;

	/**
	 * The Response builder.
	 */
	private final GenericResponseBuilder responseBuilder;

	/**
	 * The Operation parser.
	 */
	private final OperationBuilder operationParser;

	/**
	 * The Request mapping handler mapping.
	 */
	private final RequestMappingInfoHandlerMapping requestMappingHandlerMapping;

	/**
	 * The Actuator provider.
	 */
	private final Optional<ActuatorProvider> actuatorProvider;

	/**
	 * The Spring doc config properties.
	 */
	private final SpringDocConfigProperties springDocConfigProperties;

	/**
	 * The Spring security o auth 2 provider.
	 */
	private final Optional<SecurityOAuth2Provider> springSecurityOAuth2Provider;

	/**
	 * The Grouped open api resources.
	 */
	private Map<String, OpenApiResource> groupedOpenApiResources;

	/**
	 * The Router function provider.
	 */
	private final Optional<RouterFunctionProvider> routerFunctionProvider;

	/**
	 * The Repository rest resource provider.
	 */
	private final Optional<RepositoryRestResourceProvider> repositoryRestResourceProvider;

	/**
	 * Instantiates a new Multiple open api resource.
	 *
	 * @param groupedOpenApis the grouped open apis 
	 * @param defaultOpenAPIBuilder the default open api builder 
	 * @param requestBuilder the request builder 
	 * @param responseBuilder the response builder 
	 * @param operationParser the operation parser 
	 * @param requestMappingHandlerMapping the request mapping handler mapping 
	 * @param actuatorProvider the actuator provider 
	 * @param springDocConfigProperties the spring doc config properties 
	 * @param springSecurityOAuth2Provider the spring security o auth 2 provider 
	 * @param routerFunctionProvider the router function provider 
	 * @param repositoryRestResourceProvider the repository rest resource provider
	 */
	public MultipleOpenApiResource(List<GroupedOpenApi> groupedOpenApis,
			ObjectFactory<OpenAPIBuilder> defaultOpenAPIBuilder, AbstractRequestBuilder requestBuilder,
			GenericResponseBuilder responseBuilder, OperationBuilder operationParser,
			RequestMappingInfoHandlerMapping requestMappingHandlerMapping, Optional<ActuatorProvider> actuatorProvider,
			SpringDocConfigProperties springDocConfigProperties, Optional<SecurityOAuth2Provider> springSecurityOAuth2Provider,
			Optional<RouterFunctionProvider> routerFunctionProvider,
			Optional<RepositoryRestResourceProvider> repositoryRestResourceProvider) {

		this.groupedOpenApis = groupedOpenApis;
		this.defaultOpenAPIBuilder = defaultOpenAPIBuilder;
		this.requestBuilder = requestBuilder;
		this.responseBuilder = responseBuilder;
		this.operationParser = operationParser;
		this.requestMappingHandlerMapping = requestMappingHandlerMapping;
		this.actuatorProvider = actuatorProvider;
		this.springDocConfigProperties = springDocConfigProperties;
		this.springSecurityOAuth2Provider = springSecurityOAuth2Provider;
		this.routerFunctionProvider = routerFunctionProvider;
		this.repositoryRestResourceProvider=repositoryRestResourceProvider;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.groupedOpenApiResources = groupedOpenApis.stream()
				.collect(Collectors.toMap(GroupedOpenApi::getGroup, item ->
						{
							GroupConfig groupConfig = new GroupConfig(item.getGroup(), item.getPathsToMatch(), item.getPackagesToScan(), item.getPackagesToExclude(), item.getPathsToExclude());
							springDocConfigProperties.addGroupConfig(groupConfig);
							return new OpenApiResource(item.getGroup(),
									defaultOpenAPIBuilder,
									requestBuilder,
									responseBuilder,
									operationParser,
									requestMappingHandlerMapping,
									actuatorProvider,
									Optional.of(item.getOperationCustomizers()),
									Optional.of(item.getOpenApiCustomisers()),
									springDocConfigProperties,
									springSecurityOAuth2Provider,
									routerFunctionProvider,
									repositoryRestResourceProvider
							);
						}
				));
	}

	/**
	 * Openapi json string.
	 *
	 * @param request the request 
	 * @param apiDocsUrl the api docs url 
	 * @param group the group 
	 * @return the string 
	 * @throws JsonProcessingException the json processing exception
	 */
	@Operation(hidden = true)
	@GetMapping(value = API_DOCS_URL + "/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String openapiJson(HttpServletRequest request, @Value(API_DOCS_URL) String apiDocsUrl,
			@PathVariable String group)
			throws JsonProcessingException {
		return getOpenApiResourceOrThrow(group).openapiJson(request, apiDocsUrl + DEFAULT_PATH_SEPARATOR + group);
	}

	/**
	 * Openapi yaml string.
	 *
	 * @param request the request 
	 * @param apiDocsUrl the api docs url 
	 * @param group the group 
	 * @return the string 
	 * @throws JsonProcessingException the json processing exception
	 */
	@Operation(hidden = true)
	@GetMapping(value = DEFAULT_API_DOCS_URL_YAML + "/{group}", produces = APPLICATION_OPENAPI_YAML)
	public String openapiYaml(HttpServletRequest request, @Value(DEFAULT_API_DOCS_URL_YAML) String apiDocsUrl,
			@PathVariable String group)
			throws JsonProcessingException {
		return getOpenApiResourceOrThrow(group).openapiYaml(request, apiDocsUrl + DEFAULT_PATH_SEPARATOR + group);
	}


	/**
	 * Gets open api resource or throw.
	 *
	 * @param group the group
	 * @return the open api resource or throw
	 */
	private OpenApiResource getOpenApiResourceOrThrow(String group) {
		OpenApiResource openApiResource = groupedOpenApiResources.get(group);
		if (openApiResource == null) {
			throw new OpenApiResourceNotFoundException("No OpenAPI resource found for group: " + group);
		}
		return openApiResource;
	}
}
