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

package test.org.springdoc.api.app67;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.Parameters;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.media.Schema;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo",
		produces = MediaType.TEXT_PLAIN_VALUE)
public class HelloController {

	@GetMapping("operation1")
	@Operation(summary = "Operation 1 (expected result - no parameters)")
	public String operation1() {
		return "operation1";
	}

	@GetMapping("operation2")
	@Operation(summary = "Operation 2 (expected result - 3 parameters)", parameters = {
			@Parameter(name = "pageNumber", description = "page number",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
			@Parameter(name = "pageSize", description = "page size",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
			@Parameter(name = "sort", description = "sort specification",
					in = ParameterIn.QUERY, schema = @Schema(type = "string"))
	})
	public String operation2() {
		return "operation2";
	}

	@GetMapping("operation3")
	@Operation(summary = "Operation 3 (expected result - 3 parameters)")
	@Parameters({
			@Parameter(name = "pageNumber", description = "page number",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
			@Parameter(name = "pageSize", description = "page size",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
			@Parameter(name = "sort", description = "sort specification",
					in = ParameterIn.QUERY, schema = @Schema(type = "string"))
	})
	public String operation3() {
		return "operation3";
	}

	@GetMapping("operation4")
	@Operation(summary = "Operation 4 (expected result - 3 parameters)")
	@QueryPaging
	@QuerySort
	public String operation4() {
		return "operation4";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	@Parameters({
			@Parameter(name = "pageNumber", description = "page number",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
			@Parameter(name = "pageSize", description = "page size",
					in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
	})
	public @interface QueryPaging {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	@Parameters({
			@Parameter(name = "sort", description = "sort specification",
					in = ParameterIn.QUERY, schema = @Schema(type = "string"))
	})
	public @interface QuerySort {

	}
}