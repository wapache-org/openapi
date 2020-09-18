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

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.spring.api.AbstractOpenApiResource;
import org.wapache.openapi.spring.core.converters.AdditionalModelsConverter;
import org.wapache.openapi.spring.core.converters.ConverterUtils;
import org.wapache.openapi.spring.core.converters.SchemaPropertyDeprecatingConverter;

/**
 * The type Spring doc utils.
 * @author bnasslahsen
 */
public class SpringDocUtils {

	/**
	 * The constant springDocConfig.
	 */
	private static final SpringDocUtils springDocConfig = new SpringDocUtils();

	/**
	 * Instantiates a new Spring doc utils.
	 */
	private SpringDocUtils() {
	}

	/**
	 * Gets config.
	 *
	 * @return the config
	 */
	public static SpringDocUtils getConfig() {
		return springDocConfig;
	}

	/**
	 * Add deprecated type spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils addDeprecatedType(Class<? extends Annotation> cls) {
		SchemaPropertyDeprecatingConverter.addDeprecatedType(cls);
		return this;
	}

	/**
	 * Add rest controllers spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addRestControllers(Class<?>... classes) {
		AbstractOpenApiResource.addRestControllers(classes);
		return this;
	}

	/**
	 * Add hidden rest controllers spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addHiddenRestControllers(Class<?>... classes) {
		AbstractOpenApiResource.addHiddenRestControllers(classes);
		return this;
	}

	/**
	 * Add hidden rest controllers spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addHiddenRestControllers(String... classes) {
		AbstractOpenApiResource.addHiddenRestControllers(classes);
		return this;
	}

	/**
	 * Replace with class spring doc utils.
	 *
	 * @param source the source
	 * @param target the target
	 * @return the spring doc utils
	 */
	public SpringDocUtils replaceWithClass(Class source, Class target) {
		AdditionalModelsConverter.replaceWithClass(source, target);
		return this;
	}

	/**
	 * Replace with schema spring doc utils.
	 *
	 * @param source the source
	 * @param target the target
	 * @return the spring doc utils
	 */
	public SpringDocUtils replaceWithSchema(Class source, Schema target) {
		AdditionalModelsConverter.replaceWithSchema(source, target);
		return this;
	}

	/**
	 * Add request wrapper to ignore spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addRequestWrapperToIgnore(Class<?>... classes) {
		AbstractRequestBuilder.addRequestWrapperToIgnore(classes);
		return this;
	}

	/**
	 * Remove request wrapper to ignore spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeRequestWrapperToIgnore(Class<?>... classes) {
		AbstractRequestBuilder.removeRequestWrapperToIgnore(classes);
		return this;
	}

	/**
	 * Add file type spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addFileType(Class<?>... classes) {
		GenericParameterBuilder.addFileType(classes);
		return this;
	}

	/**
	 * Add response wrapper to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils addResponseWrapperToIgnore(Class<?> cls) {
		ConverterUtils.addResponseWrapperToIgnore(cls);
		return this;
	}

	/**
	 * Remove response wrapper to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeResponseWrapperToIgnore(Class<?> cls) {
		ConverterUtils.removeResponseWrapperToIgnore(cls);
		return this;
	}

	/**
	 * Add response type to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils addResponseTypeToIgnore(Class<?> cls) {
		ConverterUtils.addResponseTypeToIgnore(cls);
		return this;
	}

	/**
	 * Remove response type to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeResponseTypeToIgnore(Class<?> cls) {
		ConverterUtils.removeResponseTypeToIgnore(cls);
		return this;
	}

	/**
	 * Add annotations to ignore spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addAnnotationsToIgnore(Class<?>... classes) {
		SpringDocAnnotationsUtils.addAnnotationsToIgnore(classes);
		return this;
	}

	/**
	 * Remove annotations to ignore spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeAnnotationsToIgnore(Class<?>... classes) {
		SpringDocAnnotationsUtils.removeAnnotationsToIgnore(classes);
		return this;
	}

	/**
	 * Add flux wrapper to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils addFluxWrapperToIgnore(Class<?> cls) {
		ConverterUtils.addFluxWrapperToIgnore(cls);
		return this;
	}

	/**
	 * Remove flux wrapper to ignore spring doc utils.
	 *
	 * @param cls the cls
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeFluxWrapperToIgnore(Class<?> cls) {
		ConverterUtils.removeFluxWrapperToIgnore(cls);
		return this;
	}

	/**
	 * Add simple types for parameter object spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils addSimpleTypesForParameterObject(Class<?>... classes) {
		MethodParameterPojoExtractor.addSimpleTypes(classes);
		return this;
	}

	/**
	 * Remove simple types for parameter object spring doc utils.
	 *
	 * @param classes the classes
	 * @return the spring doc utils
	 */
	public SpringDocUtils removeSimpleTypesForParameterObject(Class<?>... classes) {
		MethodParameterPojoExtractor.removeSimpleTypes(classes);
		return this;
	}

	/**
	 * Add simple type predicate for parameter object spring doc utils.
	 *
	 * @param predicate the predicate
	 * @return the spring doc utils
	 */
	public SpringDocUtils addSimpleTypePredicateForParameterObject(Predicate<Class<?>> predicate) {
		MethodParameterPojoExtractor.addSimpleTypePredicate(predicate);
		return this;
	}

	/**
	 * Disable replacement spring doc utils.
	 *
	 * @param source the source
	 * @return the spring doc utils
	 */
	public SpringDocUtils disableReplacement(Class source) {
		AdditionalModelsConverter.disableReplacement(source);
		return this;
	}

}

