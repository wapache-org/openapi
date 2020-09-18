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

package test.org.springdoc.api.app70.customizer;

import java.lang.annotation.Annotation;
import java.time.Duration;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JavaType;
import org.wapache.openapi.v3.core.converter.AnnotatedType;
import org.wapache.openapi.v3.core.util.Json;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.media.StringSchema;

import org.springframework.stereotype.Component;

@Component
public class PropertyCustomizer implements org.wapache.openapi.spring.core.customizers.PropertyCustomizer {
	@Override
	public Schema customize(Schema property, AnnotatedType type) {
		Annotation[] ctxAnnotations = type.getCtxAnnotations();
		if (ctxAnnotations == null) {
			return property;
		}

		Optional<CustomizedProperty> propertyAnnotation = Stream.of(ctxAnnotations)
				.filter(CustomizedProperty.class::isInstance)
				.findFirst()
				.map(CustomizedProperty.class::cast);

		JavaType javaType = Json.mapper().constructType(type.getType());
		if (javaType.getRawClass().equals(Duration.class)) {
			property = new StringSchema().format("duration").properties(Collections.emptyMap());
		}
		return property;
	}
}
