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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.wapache.openapi.spring.core.Constants;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigProperties;
import org.wapache.openapi.spring.core.ui.OpenApiUiOAuthProperties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * The type Abstract UI index transformer.
 * @author bnasslahsen
 */
public class AbstractOpenApiUiIndexTransformer {

	/**
	 * The Object mapper.
	 */
	protected ObjectMapper objectMapper;

	/**
	 * The OpenApi ui config.
	 */
	protected OpenApiUiConfigProperties uiConfig;

	/**
	 * The OpenApi ui o auth properties.
	 */
	protected OpenApiUiOAuthProperties oAuthProperties;

	/**
	 * Instantiates a new Abstract swagger index transformer.
	 *
	 * @param uiConfig the swagger ui config
	 * @param oAuthProperties the swagger ui o auth properties
	 * @param objectMapper the object mapper
	 */
	public AbstractOpenApiUiIndexTransformer(
		OpenApiUiConfigProperties uiConfig,
		OpenApiUiOAuthProperties oAuthProperties,
		ObjectMapper objectMapper
	) {
		this.uiConfig = uiConfig;
		this.oAuthProperties = oAuthProperties;
		this.objectMapper = objectMapper;
	}

	/**
	 * Add init oauth string.
	 *
	 * @param html the html
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	protected String addInitOauth(String html) throws JsonProcessingException {
		StringBuilder stringBuilder = new StringBuilder("window.ui = ui\n");
		stringBuilder.append("ui.initOAuth(\n");
		String json = objectMapper.writeValueAsString(oAuthProperties.getConfigParameters());
		stringBuilder.append(json);
		stringBuilder.append(")");
		return html.replace("window.ui = ui", stringBuilder.toString());
	}

	/**
	 * Read fully as string string.
	 *
	 * @param inputStream the input stream
	 * @return the string
	 * @throws IOException the io exception
	 */
	protected String readFullyAsString(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, length);
		}
		return baos.toString(StandardCharsets.UTF_8.name());
	}

	/**
	 * Overwrite swagger default url string.
	 *
	 * @param html the html
	 * @return the string
	 */
	protected String overwriteOpenApiDefaultUrl(String html) {
		return html.replace(Constants.OPENAPI_UI_DEFAULT_URL, StringUtils.EMPTY);
	}

	/**
	 * Has default transformations boolean.
	 *
	 * @return the boolean
	 */
	protected boolean hasDefaultTransformations() {
		boolean oauth2Configured = !CollectionUtils.isEmpty(oAuthProperties.getConfigParameters());
		return oauth2Configured || uiConfig.isDisableDefaultUrl() || uiConfig.isCsrfEnabled();
	}

	/**
	 * Default transformations string.
	 *
	 * @param inputStream the input stream
	 * @return the string
	 * @throws IOException the io exception
	 */
	protected String defaultTransformations(InputStream inputStream) throws IOException {
		String html = readFullyAsString(inputStream);
		if (!CollectionUtils.isEmpty(oAuthProperties.getConfigParameters())) {
			html = addInitOauth(html);
		}
		if (uiConfig.isDisableDefaultUrl()) {
			html = overwriteOpenApiDefaultUrl(html);
		}
		if (uiConfig.isCsrfEnabled()) {
			html = addCSRF(html);
		}
		return html;
	}

	/**
	 * Add csrf string.
	 *
	 * @param html the html
	 * @return the string
	 */
	protected String addCSRF(String html) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("requestInterceptor: function() {\n");
		stringBuilder.append("const value = `; ${document.cookie}`;\n");
		stringBuilder.append("const parts = value.split(`; ");
		stringBuilder.append(uiConfig.getCsrf().getCookieName());
		stringBuilder.append("=`);\n");
		stringBuilder.append("console.log(parts);\n");
		stringBuilder.append("if (parts.length === 2)\n");
		stringBuilder.append("this.headers['");
		stringBuilder.append(uiConfig.getCsrf().getHeaderName());
		stringBuilder.append("'] = parts.pop().split(';').shift();\n");
		stringBuilder.append("return this;\n");
		stringBuilder.append("},\n");
		stringBuilder.append("presets: [");
		return html.replace("presets: [", stringBuilder.toString());
	}
}
