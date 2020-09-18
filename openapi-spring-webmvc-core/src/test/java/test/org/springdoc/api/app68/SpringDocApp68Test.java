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

package test.org.springdoc.api.app68;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.wapache.openapi.spring.core.Constants;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "springdoc.show-actuator=true")
public class SpringDocApp68Test extends AbstractSpringDocTest {

	public static String className;

	@Test
	public void testApp() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL + "/stores"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(content().json(getContent("results/app68-1.json"), true));
	}

	@Test
	public void testApp2() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL + "/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(content().json(getContent("results/app68-2.json"), true));
	}

	@Test
	public void testApp3() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL + "/pets"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(content().json(getContent("results/app68-3.json"), true));
	}

	@Test
	public void testApp4() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL + "/groups test"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(content().json(getContent("results/app68-4.json"), true));
	}

	@Test
	public void testActuator() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(jsonPath("$.paths./actuator/info.get.operationId", containsString("handle_")))
				.andExpect(jsonPath("$.paths./actuator/info.get.summary", Matchers.is("Actuator web endpoint 'info'")))
				.andExpect(jsonPath("$.paths./actuator/health.get.operationId", containsString("handle_")));
	}

	@Test
	public void testActuatorDescription() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(jsonPath("$.tags", hasSize(4)))
				.andExpect(jsonPath("$.tags[?(@.name == '" + Constants.SPRINGDOC_ACTUATOR_TAG + "')].name", contains(Constants.SPRINGDOC_ACTUATOR_TAG)))
				.andExpect(jsonPath("$.tags[?(@.name == '" + Constants.SPRINGDOC_ACTUATOR_TAG + "')].description", contains(Constants.SPRINGDOC_ACTUATOR_DESCRIPTION)))
				.andExpect(jsonPath("$.tags[?(@.name == '" + Constants.SPRINGDOC_ACTUATOR_TAG + "')].externalDocs.description", contains(Constants.SPRINGDOC_ACTUATOR_DOC_DESCRIPTION)))
				.andExpect(jsonPath("$.tags[?(@.name == '" + Constants.SPRINGDOC_ACTUATOR_TAG + "')].externalDocs.url", contains(Constants.SPRINGDOC_ACTUATOR_DOC_URL)));
	}

	@Test
	public void testApp5() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL + "/user"))
				.andExpect(status().isNotFound());
	}
}
