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
package test.org.springdoc.api.app120;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.Explode;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.enums.ParameterStyle;
import org.wapache.openapi.v3.annotations.extensions.Extension;
import org.wapache.openapi.v3.annotations.media.ArraySchema;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.ExampleObject;
import org.wapache.openapi.v3.annotations.media.Schema;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@RestController
public class MetaAnnotationController {

	@GetMapping(value = "/simpleTest/{accountId}")
	String simpleTest(@PathVariable @AccountId String accountId) {
		return accountId;
	}

	/**
	 * When there is a top level {@code @Parameter} annotation it has precedence over the meta-annotation
	 * So the id parameter should have all the defaults, with a name of "id"
	 */
	@GetMapping(value = "/testTopLevelParamAnnotationOverrides/{accountId}")
	String testTopLevelParamAnnotationOverrides(@PathVariable @AccountId @Parameter(name = "id") String accountId) {
		return accountId;
	}

	@GetMapping(value = "/testQueryParam")
	String testQueryParam(@RequestParam @AccountId String accountId) {
		return accountId;
	}

	/**
	 * {@code @AliasFor} in the {@code @AccountId} annotation allows us to override the default it provides.
	 */
	@GetMapping(value = "/testAliasFor")
	String testAliasFor(@RequestParam @AccountId(example = "OVERRIDDEN EXAMPLE") String accountId) {
		return accountId;
	}

	/**
	 * This should inherent all the attributes of {@code @AccountId}, but give it a different name
	 */
	@Target({PARAMETER, METHOD, ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@AccountId(name = "queryAccountId")
	@interface QueryAccountId {
	}

	@GetMapping(value = "/testMetaMetaAnnotation/{accountId}")
	String testMetaMetaAnnotation(
			@RequestParam @QueryAccountId String queryAccountId,
			@PathVariable @AccountId String accountId) {
		return accountId;
	}

	@Target({PARAMETER, METHOD, ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter
	@interface TestAllAttributesAsAlias {

		@AliasFor(annotation = Parameter.class, attribute="name")
		String name() default "name";

		@AliasFor(annotation = Parameter.class, attribute="in")
		ParameterIn in() default ParameterIn.QUERY;

		@AliasFor(annotation = Parameter.class, attribute="description")
		String description() default "desc";

		@AliasFor(annotation = Parameter.class, attribute="required")
		boolean required() default true;

		@AliasFor(annotation = Parameter.class, attribute="deprecated")
		boolean deprecated() default true;

		@AliasFor(annotation = Parameter.class, attribute="allowEmptyValue")
		boolean allowEmptyValue() default true;

		@AliasFor(annotation = Parameter.class, attribute="style")
		ParameterStyle style() default ParameterStyle.DEEPOBJECT;

		@AliasFor(annotation = Parameter.class, attribute="explode")
 		Explode explode() default Explode.TRUE;

		@AliasFor(annotation = Parameter.class, attribute="allowReserved")
		boolean allowReserved() default true;

		@AliasFor(annotation = Parameter.class, attribute="schema")
		Schema schema() default @Schema(name = "special schema", implementation = Boolean.class);

		@AliasFor(annotation = Parameter.class, attribute="array")
		ArraySchema array() default @ArraySchema();

		@AliasFor(annotation = Parameter.class, attribute="content")
		Content[] content() default {};

		@AliasFor(annotation = Parameter.class, attribute="hidden")
		boolean hidden() default false;

		@AliasFor(annotation = Parameter.class, attribute="examples")
		ExampleObject[] examples() default {};

		@AliasFor(annotation = Parameter.class, attribute="example")
		String example() default "1234";

		@AliasFor(annotation = Parameter.class, attribute="extensions")
		Extension[] extensions() default {};

		@AliasFor(annotation = Parameter.class, attribute="ref")
		String ref() default "";
	}

	@GetMapping(value = "/testAllAttributesAsAlias/")
	String testAllAttributesAsAlias(
			@RequestParam @TestAllAttributesAsAlias String name) {
		return name;
	}

	@Target({PARAMETER, METHOD, ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter(name = "name", description = "desc", schema = @Schema(implementation = Boolean.class))
	@interface TestNoAliasFors {
	}

	@GetMapping(value = "/testNoAliasFors/")
	String testNoAliasFors(
			@RequestParam @TestAllAttributesAsAlias String name) {
		return name;
	}
}
