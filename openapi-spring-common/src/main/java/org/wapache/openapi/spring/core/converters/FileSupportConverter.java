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

package org.wapache.openapi.spring.core.converters;


import java.util.Iterator;

import com.fasterxml.jackson.databind.JavaType;
import org.wapache.openapi.spring.core.GenericParameterBuilder;
import org.wapache.openapi.v3.core.converter.AnnotatedType;
import org.wapache.openapi.v3.core.converter.ModelConverter;
import org.wapache.openapi.v3.core.converter.ModelConverterContext;
import org.wapache.openapi.v3.core.util.Json;
import org.wapache.openapi.v3.models.media.FileSchema;
import org.wapache.openapi.v3.models.media.Schema;

/**
 * The type File support converter.
 * @author bnasslahsen
 */
public class FileSupportConverter implements ModelConverter {

	@Override
	public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
		JavaType javaType = Json.mapper().constructType(type.getType());
		if (javaType != null) {
			Class<?> cls = javaType.getRawClass();
			if (GenericParameterBuilder.isFile(cls))
				return new FileSchema();
		}
		return (chain.hasNext()) ? chain.next().resolve(type, context, chain) : null;
	}

}