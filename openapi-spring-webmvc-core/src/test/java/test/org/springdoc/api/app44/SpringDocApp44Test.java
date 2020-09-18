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

package test.org.springdoc.api.app44;

import org.junit.jupiter.api.Test;
import org.wapache.openapi.spring.core.Constants;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpringDocApp44Test extends AbstractSpringDocTest {

	@Test
	public void testApp() throws Exception {
		mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1"))).andExpect(jsonPath("$.paths./helloworld.post.responses.200.content.['application/json'].schema.oneOf").isArray()).andExpect(jsonPath("$.paths./helloworld.post.responses.200.content.['application/json'].schema.oneOf[*].$ref", containsInAnyOrder("#/components/schemas/HelloDTO2",
				"#/components/schemas/HelloDTO1")))
				.andExpect(jsonPath("$.paths./helloworld.post.requestBody.content.['application/vnd.v1+json'].schema.$ref", is("#/components/schemas/RequestV1")))
				.andExpect(jsonPath("$.paths./helloworld.post.requestBody.content.['application/vnd.v2+json'].schema.$ref", is("#/components/schemas/RequestV2")))
				.andExpect(jsonPath("$.paths./helloworld.post.responses.400.content.['application/json'].schema.$ref", is("#/components/schemas/ErrorDTO")));

	}

	@SpringBootApplication
	static class SpringDocTestApp {}

}