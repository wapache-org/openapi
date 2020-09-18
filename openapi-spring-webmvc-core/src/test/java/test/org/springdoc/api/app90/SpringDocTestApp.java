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

package test.org.springdoc.api.app90;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.spring.core.customizers.OpenApiCustomiser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@SpringBootApplication
class SpringDocTestApp {

	@Value("classpath:/500-90.txt")
	private Resource http500ExampleResource;

	public static String asString(Resource resource) {
		try (Reader reader = new InputStreamReader(resource.getInputStream())) {
			return FileCopyUtils.copyToString(reader);
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Bean
	public OpenApiCustomiser openApiCustomiser(Collection<Entry<String, Example>> examples) {
		return openAPI -> {
			examples.forEach(example -> {
				openAPI.getComponents().addExamples(example.getKey(), example.getValue());
			});
		};
	}

	@Bean
	public Map.Entry<String, Example> http500Example() {
		Example http500Example = new Example();
		Map.Entry<String, Example> entry = new AbstractMap.SimpleEntry<String, Example>("http500Example", http500Example);
		http500Example.setSummary("HTTP 500 JSON Body response example");
		http500Example.setDescription(
				"An example of HTTP response in case an error occurs on server side. instance attribute reference a traceId to ease server side analysis.");
		http500Example.setValue(asString(http500ExampleResource));
		return entry;
	}
}
