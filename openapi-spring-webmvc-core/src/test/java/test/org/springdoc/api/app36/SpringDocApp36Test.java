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

package test.org.springdoc.api.app36;

import org.junit.jupiter.api.Test;
import org.wapache.openapi.spring.core.Constants;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = {
		"springdoc.show-actuator=true",
		"management.endpoints.web.exposure.include=*"})
public class SpringDocApp36Test extends AbstractSpringDocTest {

	@Test
	public void testApp() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1")))
				.andExpect(jsonPath("$.paths./actuator/info.get.operationId", containsString("handle")))
				.andExpect(jsonPath("$.paths./actuator/health.get.operationId", containsString("handle")))
				.andExpect(jsonPath("$.paths./actuator/metrics/{requiredMetricName}.get.parameters[0].in", is("path")))
				.andExpect(jsonPath("$.paths./actuator/metrics/{requiredMetricName}.get.parameters[0].name", is("requiredMetricName")));
	}

	@SpringBootApplication
	static class SpringDocTestApp {}

}