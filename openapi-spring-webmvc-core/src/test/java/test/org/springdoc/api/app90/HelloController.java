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

import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.ExampleObject;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.parameters.ApiRequestBody;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/test")
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(examples = @ExampleObject(name = "500", ref = "#/components/examples/http500Example"), mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }) })
	public void test1(String hello) {
	}

	@PostMapping("/test2")
	@ApiRequestBody(
			description = "Details of the Item to be created",
			required = true,
			content = @Content(
					schema = @Schema(implementation = User.class),
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					examples = {
							@ExampleObject(
									name = "An example request with the minimum required fields to create.",
									value = "min",
									summary = "Minimal request"),
							@ExampleObject(
									name = "An example request with all fields provided with example values.",
									value = "full",
									summary = "Full request") }))
	public void test2(String hello) {
	}

}