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

package org.wapache.openapi.spring.core;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import org.wapache.openapi.v3.core.util.AnnotationsUtils;
import org.wapache.openapi.v3.core.util.Json;
import org.wapache.openapi.v3.annotations.enums.Explode;
import org.wapache.openapi.v3.annotations.media.ExampleObject;
import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.media.ArraySchema;
import org.wapache.openapi.v3.models.media.Content;
import org.wapache.openapi.v3.models.media.FileSchema;
import org.wapache.openapi.v3.models.media.ObjectSchema;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wapache.openapi.spring.core.customizers.DelegatingMethodParameterCustomizer;

import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Generic parameter builder.
 * @author bnasslahsen
 */
@SuppressWarnings("rawtypes")
public class GenericParameterBuilder {

	/**
	 * The constant FILE_TYPES.
	 */
	private static final List<Class<?>> FILE_TYPES = new ArrayList<>();

	/**
	 * The Optional delegating method parameter customizer.
	 */
	private final Optional<DelegatingMethodParameterCustomizer> optionalDelegatingMethodParameterCustomizer;

	/**
	 * The constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericParameterBuilder.class);

	static {
		FILE_TYPES.add(MultipartFile.class);
		FILE_TYPES.add(Resource.class);
	}

	/**
	 * The Property resolver utils.
	 */
	private final PropertyResolverUtils propertyResolverUtils;

	/**
	 * Instantiates a new Generic parameter builder.
	 *
	 * @param propertyResolverUtils the property resolver utils
	 * @param optionalDelegatingMethodParameterCustomizer the optional delegating method parameter customizer
	 */
	public GenericParameterBuilder(PropertyResolverUtils propertyResolverUtils,
			   Optional<DelegatingMethodParameterCustomizer> optionalDelegatingMethodParameterCustomizer) {
		this.propertyResolverUtils = propertyResolverUtils;
		this.optionalDelegatingMethodParameterCustomizer=optionalDelegatingMethodParameterCustomizer;
	}

	/**
	 * Add file type.
	 *
	 * @param classes the classes
	 */
	public static void addFileType(Class<?>... classes) {
		FILE_TYPES.addAll(Arrays.asList(classes));
	}

	/**
	 * Is file boolean.
	 *
	 * @param type the type
	 * @return the boolean
	 */
	public static boolean isFile(Class type) {
		return FILE_TYPES.stream().anyMatch(clazz -> clazz.isAssignableFrom(type));
	}

	/**
	 * Merge parameter parameter.
	 *
	 * @param existingParamDoc the existing param doc
	 * @param paramCalcul the param calcul
	 * @return the parameter
	 */
	public static Parameter mergeParameter(List<Parameter> existingParamDoc, Parameter paramCalcul) {
		Parameter result = paramCalcul;
		if (paramCalcul != null && paramCalcul.getName() != null) {
			final String name = paramCalcul.getName();
			Parameter paramDoc = existingParamDoc.stream().filter(p -> name.equals(p.getName())).findAny().orElse(null);
			if (paramDoc != null) {
				mergeParameter(paramCalcul, paramDoc);
				result = paramDoc;
			}
			else
				existingParamDoc.add(result);
		}
		return result;
	}

	/**
	 * Merge parameter.
	 *
	 * @param paramCalcul the param calcul
	 * @param paramDoc the param doc
	 */
	private static void mergeParameter(Parameter paramCalcul, Parameter paramDoc) {
		if (StringUtils.isBlank(paramDoc.getDescription()))
			paramDoc.setDescription(paramCalcul.getDescription());

		if (StringUtils.isBlank(paramDoc.getIn()))
			paramDoc.setIn(paramCalcul.getIn());

		if (paramDoc.getExample() == null)
			paramDoc.setExample(paramCalcul.getExample());

		if (paramDoc.getDeprecated() == null)
			paramDoc.setDeprecated(paramCalcul.getDeprecated());

		if (paramDoc.getRequired() == null)
			paramDoc.setRequired(paramCalcul.getRequired());

		if (paramDoc.getAllowEmptyValue() == null)
			paramDoc.setAllowEmptyValue(paramCalcul.getAllowEmptyValue());

		if (paramDoc.getAllowReserved() == null)
			paramDoc.setAllowReserved(paramCalcul.getAllowReserved());

		if (StringUtils.isBlank(paramDoc.get$ref()))
			paramDoc.set$ref(paramDoc.get$ref());

		if (paramDoc.getSchema() == null && paramDoc.getContent() == null)
			paramDoc.setSchema(paramCalcul.getSchema());

		if (paramDoc.getExamples() == null)
			paramDoc.setExamples(paramCalcul.getExamples());

		if (paramDoc.getExtensions() == null)
			paramDoc.setExtensions(paramCalcul.getExtensions());

		if (paramDoc.getStyle() == null)
			paramDoc.setStyle(paramCalcul.getStyle());

		if (paramDoc.getExplode() == null)
			paramDoc.setExplode(paramCalcul.getExplode());
	}

	/**
	 * Build parameter from doc parameter.
	 *
	 * @param parameterDoc the parameter doc
	 * @param components the components
	 * @param jsonView the json view
	 * @return the parameter
	 */
	public Parameter buildParameterFromDoc(org.wapache.openapi.v3.annotations.Parameter parameterDoc,
                                           Components components, JsonView jsonView) {
		Parameter parameter = new Parameter();
		if (StringUtils.isNotBlank(parameterDoc.description()))
			parameter.setDescription(propertyResolverUtils.resolve(parameterDoc.description()));
		if (StringUtils.isNotBlank(parameterDoc.name()))
			parameter.setName(propertyResolverUtils.resolve(parameterDoc.name()));
		if (StringUtils.isNotBlank(parameterDoc.in().toString()))
			parameter.setIn(parameterDoc.in().toString());
		if (StringUtils.isNotBlank(parameterDoc.example())) {
			try {
				parameter.setExample(Json.mapper().readTree(parameterDoc.example()));
			}
			catch (IOException e) {
				parameter.setExample(parameterDoc.example());
			}
		}
		if (parameterDoc.deprecated())
			parameter.setDeprecated(parameterDoc.deprecated());
		if (parameterDoc.required())
			parameter.setRequired(parameterDoc.required());
		if (parameterDoc.allowEmptyValue())
			parameter.setAllowEmptyValue(parameterDoc.allowEmptyValue());
		if (parameterDoc.allowReserved())
			parameter.setAllowReserved(parameterDoc.allowReserved());

		if (parameterDoc.content().length > 0) {
			Optional<Content> optionalContent = AnnotationsUtils.getContent(parameterDoc.content(), null, null, null, components, jsonView);
			optionalContent.ifPresent(parameter::setContent);
		} else {
			setSchema(parameterDoc, components, jsonView, parameter);
		}

		setExamples(parameterDoc, parameter);
		setExtensions(parameterDoc, parameter);
		setParameterStyle(parameter, parameterDoc);
		setParameterExplode(parameter, parameterDoc);

		return parameter;
	}

	/**
	 * Sets schema.
	 *
	 * @param parameterDoc the parameter doc
	 * @param components the components
	 * @param jsonView the json view
	 * @param parameter the parameter
	 */
	private void setSchema(org.wapache.openapi.v3.annotations.Parameter parameterDoc, Components components, JsonView jsonView, Parameter parameter) {
		if (StringUtils.isNotBlank(parameterDoc.ref()))
			parameter.$ref(parameterDoc.ref());
		else {
			Schema schema = null;
			try {
				schema = AnnotationsUtils.getSchema(parameterDoc.schema(), null, false,
						parameterDoc.schema().implementation(), components, jsonView).orElse(null);
			}
			catch (Exception e) {
				LOGGER.warn(Constants.GRACEFUL_EXCEPTION_OCCURRED, e);
			}
			if (schema == null) {
				schema = AnnotationsUtils.getArraySchema(parameterDoc.array(), components, jsonView).orElse(null);
			}
			parameter.setSchema(schema);
		}
	}

	/**
	 * Calculate schema schema.
	 *
	 * @param components the components
	 * @param parameterInfo the parameter info
	 * @param requestBodyInfo the request body info
	 * @param jsonView the json view
	 * @return the schema
	 */
	Schema calculateSchema(Components components, ParameterInfo parameterInfo, RequestBodyInfo requestBodyInfo, JsonView jsonView) {
		Schema schemaN;
		String paramName = parameterInfo.getpName();
		MethodParameter methodParameter = parameterInfo.getMethodParameter();

		if (parameterInfo.getParameterModel() == null || parameterInfo.getParameterModel().getSchema() == null) {
			Type type = ReturnTypeParser.getType(methodParameter);
			schemaN = SpringDocAnnotationsUtils.extractSchema(components, type, jsonView, methodParameter.getParameterAnnotations());
		}
		else
			schemaN = parameterInfo.getParameterModel().getSchema();

		if (requestBodyInfo != null) {
			schemaN = calculateRequestBodySchema(components, parameterInfo, requestBodyInfo, schemaN, paramName);
		}

		return schemaN;
	}

	/**
	 * Calculate request body schema schema.
	 *
	 * @param components the components
	 * @param parameterInfo the parameter info
	 * @param requestBodyInfo the request body info
	 * @param schemaN the schema n
	 * @param paramName the param name
	 * @return the schema
	 */
	private Schema calculateRequestBodySchema(Components components, ParameterInfo parameterInfo, RequestBodyInfo requestBodyInfo, Schema schemaN, String paramName) {
		if (schemaN != null && StringUtils.isEmpty(schemaN.getDescription()) && parameterInfo.getParameterModel() != null) {
			String description = parameterInfo.getParameterModel().getDescription();
			if (schemaN.get$ref() != null && schemaN.get$ref().contains(AnnotationsUtils.COMPONENTS_REF)) {
				String key = schemaN.get$ref().substring(21);
				Schema existingSchema = components.getSchemas().get(key);
				if (!StringUtils.isEmpty(description))
					existingSchema.setDescription(description);
			}
			else
				schemaN.setDescription(description);
		}

		if (requestBodyInfo.getMergedSchema() != null) {
			requestBodyInfo.getMergedSchema().addProperties(paramName, schemaN);
			schemaN = requestBodyInfo.getMergedSchema();
		}
		else if (schemaN instanceof FileSchema || schemaN instanceof ArraySchema && ((ArraySchema) schemaN).getItems() instanceof FileSchema) {
			schemaN = new ObjectSchema().addProperties(paramName, schemaN);
			requestBodyInfo.setMergedSchema(schemaN);
		}
		else
			requestBodyInfo.addProperties(paramName, schemaN);
		return schemaN;
	}

	/**
	 * Sets examples.
	 *
	 * @param parameterDoc the parameter doc
	 * @param parameter the parameter
	 */
	private void setExamples(org.wapache.openapi.v3.annotations.Parameter parameterDoc, Parameter parameter) {
		Map<String, Example> exampleMap = new HashMap<>();
		if (parameterDoc.examples().length == 1 && StringUtils.isBlank(parameterDoc.examples()[0].name())) {
			Optional<Example> exampleOptional = AnnotationsUtils.getExample(parameterDoc.examples()[0]);
			exampleOptional.ifPresent(parameter::setExample);
		}
		else {
			for (ExampleObject exampleObject : parameterDoc.examples()) {
				AnnotationsUtils.getExample(exampleObject)
						.ifPresent(example -> exampleMap.put(exampleObject.name(), example));
			}
		}
		if (exampleMap.size() > 0) {
			parameter.setExamples(exampleMap);
		}
	}

	/**
	 * Sets extensions.
	 *
	 * @param parameterDoc the parameter doc
	 * @param parameter the parameter
	 */
	private void setExtensions(org.wapache.openapi.v3.annotations.Parameter parameterDoc, Parameter parameter) {

		if (StringUtils.isNotBlank(parameterDoc.title())) {
			parameter.addExtension("x-title", parameterDoc.title());
		}

		if (parameterDoc.extensions().length > 0) {
			Map<String, Object> extensionMap = AnnotationsUtils.getExtensions(parameterDoc.extensions());
			extensionMap.forEach(parameter::addExtension);
		}

	}

	/**
	 * Sets parameter explode.
	 *
	 * @param parameter the parameter
	 * @param p the p
	 */
	private void setParameterExplode(Parameter parameter, org.wapache.openapi.v3.annotations.Parameter p) {
		if (isExplodable(p)) {
			if (Explode.TRUE.equals(p.explode())) {
				parameter.setExplode(Boolean.TRUE);
			}
			else if (Explode.FALSE.equals(p.explode())) {
				parameter.setExplode(Boolean.FALSE);
			}
		}
	}

	/**
	 * Sets parameter style.
	 *
	 * @param parameter the parameter
	 * @param p the p
	 */
	private void setParameterStyle(Parameter parameter, org.wapache.openapi.v3.annotations.Parameter p) {
		if (StringUtils.isNotBlank(p.style().toString())) {
			parameter.setStyle(Parameter.StyleEnum.valueOf(p.style().toString().toUpperCase()));
		}
	}

	/**
	 * Is explodable boolean.
	 *
	 * @param p the p
	 * @return the boolean
	 */
	private boolean isExplodable(org.wapache.openapi.v3.annotations.Parameter p) {
		org.wapache.openapi.v3.annotations.media.Schema schema = p.schema();
		boolean explode = true;
		Class<?> implementation = schema.implementation();
		if (implementation == Void.class && !schema.type().equals("object") && !schema.type().equals("array")) {
			explode = false;
		}
		return explode;
	}

	/**
	 * Is file boolean.
	 *
	 * @param methodParameter the method parameter
	 * @return the boolean
	 */
	public boolean isFile(MethodParameter methodParameter) {
		if (methodParameter.getGenericParameterType() instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) methodParameter.getGenericParameterType();
			return isFile(parameterizedType);
		}
		else {
			Class type = methodParameter.getParameterType();
			return isFile(type);
		}
	}

	/**
	 * Gets delegating method parameter customizer.
	 *
	 * @return the delegating method parameter customizer
	 */
	public Optional<DelegatingMethodParameterCustomizer> getDelegatingMethodParameterCustomizer() {
		return optionalDelegatingMethodParameterCustomizer;
	}

	/**
	 * Is file boolean.
	 *
	 * @param parameterizedType the parameterized type
	 * @return the boolean
	 */
	private boolean isFile(ParameterizedType parameterizedType) {
		Type type = parameterizedType.getActualTypeArguments()[0];
		Class fileClass = ResolvableType.forType(type).getRawClass();
		if (fileClass != null && isFile(fileClass))
			return true;
		else if (type instanceof WildcardType) {
			WildcardType wildcardType = (WildcardType) type;
			Type[] upperBounds = wildcardType.getUpperBounds();
			return MultipartFile.class.getName().equals(upperBounds[0].getTypeName());
		}
		return false;
	}
}
