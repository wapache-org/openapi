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

package org.wapache.openapi.spring.ui;

import org.apache.commons.lang3.StringUtils;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.core.SwaggerUiConfigParameters;
import org.wapache.openapi.spring.core.SwaggerUiConfigProperties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import static org.wapache.openapi.spring.core.Constants.SWAGGGER_CONFIG_FILE;
import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;


/**
 * The type Abstract swagger welcome.
 * @author bnasslahsen
 */
public abstract class AbstractSwaggerWelcome implements InitializingBean {

	/**
	 * The Swagger ui configuration.
	 */
	protected final SwaggerUiConfigProperties swaggerUiConfig;

	/**
	 * The Spring doc config properties.
	 */
	protected final SpringDocConfigProperties springDocConfigProperties;

	/**
	 * The Swagger ui calculated config.
	 */
	protected final SwaggerUiConfigParameters swaggerUiConfigParameters;


	/**
	 * Instantiates a new Abstract swagger welcome.
	 *
	 * @param swaggerUiConfig the swagger ui config
	 * @param springDocConfigProperties the spring doc config properties
	 * @param swaggerUiConfigParameters the swagger ui config parameters
	 */
	public AbstractSwaggerWelcome(SwaggerUiConfigProperties swaggerUiConfig, SpringDocConfigProperties springDocConfigProperties, SwaggerUiConfigParameters swaggerUiConfigParameters) {
		this.swaggerUiConfig = swaggerUiConfig;
		this.springDocConfigProperties = springDocConfigProperties;
		this.swaggerUiConfigParameters = swaggerUiConfigParameters;
	}

	@Override
	public void afterPropertiesSet() {
		springDocConfigProperties.getGroupConfigs().forEach(groupConfig -> swaggerUiConfigParameters.addGroup(groupConfig.getGroup()));
		calculateUiRootPath();
	}

	/**
	 * Build url string.
	 *
	 * @param contextPath the context path
	 * @param docsUrl the docs url
	 * @return the string
	 */
	protected String buildUrl(String contextPath, final String docsUrl) {
		if (contextPath.endsWith(DEFAULT_PATH_SEPARATOR)) {
			return contextPath.substring(0, contextPath.length() - 1) + docsUrl;
		}
		return contextPath + docsUrl;
	}

	/**
	 * Build config url.
	 *
	 * @param contextPath the context path
	 * @param uriComponentsBuilder the uri components builder
	 */
	protected void buildConfigUrl(String contextPath, UriComponentsBuilder uriComponentsBuilder) {
		String apiDocsUrl = springDocConfigProperties.getApiDocs().getPath();
		if (StringUtils.isEmpty(swaggerUiConfig.getConfigUrl())) {
			String url = buildUrl(contextPath, apiDocsUrl);
			String swaggerConfigUrl = url + DEFAULT_PATH_SEPARATOR + SWAGGGER_CONFIG_FILE;
			swaggerUiConfigParameters.setConfigUrl(swaggerConfigUrl);
			if (CollectionUtils.isEmpty(swaggerUiConfigParameters.getUrls())) {
				String swaggerUiUrl = swaggerUiConfig.getUrl();
				if (StringUtils.isEmpty(swaggerUiUrl))
					swaggerUiConfigParameters.setUrl(url);
				else
					swaggerUiConfigParameters.setUrl(swaggerUiUrl);
			}
			else
				swaggerUiConfigParameters.addUrl(url);
		}
		calculateOauth2RedirectUrl(uriComponentsBuilder);
	}

	/**
	 * Gets uri components builder.
	 *
	 * @param sbUrl the sb url
	 * @return the uri components builder
	 */
	protected UriComponentsBuilder getUriComponentsBuilder(String sbUrl) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(sbUrl);
		if (swaggerUiConfig.isDisplayQueryParams() && StringUtils.isNotEmpty(swaggerUiConfigParameters.getUrl())) {
			swaggerUiConfigParameters.getConfigParameters().entrySet().stream()
					.filter(entry -> !SwaggerUiConfigParameters.CONFIG_URL_PROPERTY.equals(entry.getKey()))
					.filter(entry -> !entry.getKey().startsWith(SwaggerUiConfigParameters.URLS_PROPERTY))
					.filter(entry -> StringUtils.isNotEmpty((String) entry.getValue()))
					.forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
		} else if (swaggerUiConfig.isDisplayQueryParamsWithoutOauth2() && StringUtils.isNotEmpty(swaggerUiConfigParameters.getUrl())) {
			swaggerUiConfigParameters.getConfigParameters().entrySet().stream()
					.filter(entry -> !SwaggerUiConfigParameters.CONFIG_URL_PROPERTY.equals(entry.getKey()))
					.filter(entry -> !SwaggerUiConfigParameters.OAUTH2_REDIRECT_URL_PROPERTY.equals(entry.getKey()))
					.filter(entry -> !entry.getKey().startsWith(SwaggerUiConfigParameters.URLS_PROPERTY))
					.filter(entry -> StringUtils.isNotEmpty((String) entry.getValue()))
					.forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
		}
		else {
			uriBuilder.queryParam(SwaggerUiConfigParameters.CONFIG_URL_PROPERTY, swaggerUiConfigParameters.getConfigUrl());
			if (StringUtils.isNotEmpty(swaggerUiConfigParameters.getLayout()))
				uriBuilder.queryParam(SwaggerUiConfigParameters.LAYOUT_PROPERTY, swaggerUiConfigParameters.getLayout());
			if (swaggerUiConfigParameters.getFilter() != null) {
				uriBuilder.queryParam(SwaggerUiConfigParameters.FILTER_PROPERTY, swaggerUiConfigParameters.getFilter());
			}
		}
		return uriBuilder;
	}

	/**
	 * Calculate oauth 2 redirect url.
	 *
	 * @param uriComponentsBuilder the uri components builder
	 */
	protected abstract void calculateOauth2RedirectUrl(UriComponentsBuilder uriComponentsBuilder);

	/**
	 * Calculate ui root path.
	 *
	 * @param sbUrls the sb urls
	 */
	protected abstract void calculateUiRootPath(StringBuilder... sbUrls);

}