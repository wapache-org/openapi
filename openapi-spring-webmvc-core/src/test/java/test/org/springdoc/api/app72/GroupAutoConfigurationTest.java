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

package test.org.springdoc.api.app72;

import org.junit.jupiter.api.Test;
import org.wapache.openapi.spring.core.GroupedOpenApi;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupAutoConfigurationTest {

	private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
			.withUserConfiguration(TestApp.class);

	@Test
	public void group_configuration_loaded() {
		contextRunner
				.run(context -> assertThat(context)
						.hasNotFailed()
						.hasBean("openApiResource")
						.hasBean("springdocBeanFactoryPostProcessor")
						.hasBean("multipleOpenApiResource")
				);
	}

	@EnableAutoConfiguration
	static class TestApp {
		@Bean
		GroupedOpenApi testGroupedOpenApi() {
			return GroupedOpenApi.builder()
					.group("test-group")
					.packagesToScan("org.test")
					.build();
		}
	}
}