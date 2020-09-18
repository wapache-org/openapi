/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test.org.springdoc.api.app5.sample;

import java.util.ArrayList;

import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.security.OAuthFlow;
import org.wapache.openapi.v3.models.security.OAuthFlows;
import org.wapache.openapi.v3.models.security.Scopes;
import org.wapache.openapi.v3.models.security.SecurityRequirement;
import org.wapache.openapi.v3.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "springdoc.api-docs.path=/api-docs")
public class OpenAPIResourceBeanConfigurationComponentsSecuritySchemesTest extends AbstractSpringDocTest {

	/**
	 * Given: Bean configuration with security scheme http basic (shouldDefineComponentsSecuritySchemesForHttpBasic)
	 * When: Get api-docs
	 * Then: Return security definitions http basic
	 */
	@Test
	public void testApp() throws Exception {
		mockMvc
				.perform(get("/api-docs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.security[0].basicScheme", is(new ArrayList<String>())))
				.andExpect(jsonPath("$.components.securitySchemes.basicScheme.type", is("http")))
				.andExpect(jsonPath("$.components.securitySchemes.basicScheme.scheme", is("basic")))
		;
	}

	/**
	 * Given: Bean configuration with security scheme API key
	 * When: Get api-docs
	 * Then: Return security definitions with API key
	 */
	@Test
	public void shouldDefineComponentsSecuritySchemesForApiKey() throws Exception {
		mockMvc
				.perform(get("/api-docs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.security[1].apiKeyScheme", is(new ArrayList<String>())))
				.andExpect(jsonPath("$.components.securitySchemes.apiKeyScheme.type", is("apiKey")))
				.andExpect(jsonPath("$.components.securitySchemes.apiKeyScheme.in", is("header")))
		;
	}

	/**
	 * Given: Bean configuration with security scheme OAuth2
	 * When: Get api-docs
	 * Then: Return security definitions with OAuth
	 */
	@Test
	public void shouldDefineComponentsSecuritySchemesForOAuth2() throws Exception {
		mockMvc
				.perform(get("/api-docs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.security[2].oAuthScheme", is(new ArrayList<String>())))
				.andExpect(jsonPath("$.components.securitySchemes.oAuthScheme.type", is("oauth2")))
				.andExpect(jsonPath("$.components.securitySchemes.oAuthScheme.description", is("This API uses OAuth 2 with the implicit grant flow. [More info](https://api.example.com/docs/auth)")))
				.andExpect(jsonPath("$.components.securitySchemes.oAuthScheme.flows.implicit.authorizationUrl", is("https://api.example.com/oauth2/authorize")))
				.andExpect(jsonPath("$.components.securitySchemes.oAuthScheme.flows.implicit.scopes.read_pets", is("read your pets")))
				.andExpect(jsonPath("$.components.securitySchemes.oAuthScheme.flows.implicit.scopes.write_pets", is("modify pets in your account")))
		;
	}

	@SpringBootApplication
	static class SpringDocTestApp {}

	@TestConfiguration
	static class Config {

		@Bean
		public OpenAPI openApi() {
			return new OpenAPI()
					.components(new Components()

							//HTTP Basic, see: https://swagger.io/docs/specification/authentication/basic-authentication/
							.addSecuritySchemes("basicScheme", new SecurityScheme()
									.type(SecurityScheme.Type.HTTP)
									.scheme("basic")
							)

							//API Key, see: https://swagger.io/docs/specification/authentication/api-keys/
							.addSecuritySchemes("apiKeyScheme", new SecurityScheme()
									.type(SecurityScheme.Type.APIKEY)
									.in(SecurityScheme.In.HEADER)
									.name("X-API-KEY")
							)

							//OAuth 2.0, see: https://swagger.io/docs/specification/authentication/oauth2/
							.addSecuritySchemes("oAuthScheme", new SecurityScheme()
									.type(SecurityScheme.Type.OAUTH2)
									.description("This API uses OAuth 2 with the implicit grant flow. [More info](https://api.example.com/docs/auth)")
									.flows(new OAuthFlows()
											.implicit(new OAuthFlow()
													.authorizationUrl("https://api.example.com/oauth2/authorize")
													.scopes(new Scopes()
															.addString("read_pets", "read your pets")
															.addString("write_pets", "modify pets in your account")
													)
											)
									)
							)
					)
					.addSecurityItem(new SecurityRequirement()
							.addList("basicScheme")
					)
					.addSecurityItem(new SecurityRequirement()
							.addList("apiKeyScheme")
					)
					.addSecurityItem(new SecurityRequirement()
							.addList("oAuthScheme")
					)
					;
		}
	}

}
