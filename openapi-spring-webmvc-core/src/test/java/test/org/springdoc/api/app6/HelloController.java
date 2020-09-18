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

package test.org.springdoc.api.app6;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.ExampleObject;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Operation(summary = "Get Something by key", responses = {
			@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {
					String.class, Integer.class }), examples = {
					@ExampleObject(name = "The String example", value = "urgheiurgheirghieurg"),
					@ExampleObject(name = "The Integer example", value = "311414") })),
			@ApiResponse(responseCode = "404", description = "Thing not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/hello")
	ResponseEntity<Void> sayHello() {
		return null;
	}
}
