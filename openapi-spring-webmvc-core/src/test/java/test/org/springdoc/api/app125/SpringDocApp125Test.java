/*
 *
 *  *
 *  *  *
 *  *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *  *
 *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  * You may obtain a copy of the License at
 *  *  *  *
 *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *
 *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  * See the License for the specific language governing permissions and
 *  *  *  * limitations under the License.
 *  *  *
 *  *
 *
 *
 */
package test.org.springdoc.api.app125;

import java.util.Optional;

import org.wapache.openapi.v3.core.converter.ModelConverter;
import org.wapache.openapi.v3.core.converter.ModelConverters;
import org.wapache.openapi.spring.core.converters.SchemaPropertyDeprecatingConverter;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;


/**
 * Tests Spring meta-annotations as method parameters
 */
@TestPropertySource(properties = "springdoc.model-converters.deprecating-converter.enabled=false")
public class SpringDocApp125Test extends AbstractSpringDocTest {

	@SpringBootApplication
	static class SpringDocTestApp {}

	static {
		Optional<ModelConverter> deprecatingConverterOptional =
				ModelConverters.getInstance().getConverters()
						.stream().filter(modelConverter -> modelConverter instanceof SchemaPropertyDeprecatingConverter).findAny();
		deprecatingConverterOptional.ifPresent(ModelConverters.getInstance()::removeConverter);
	}

}
