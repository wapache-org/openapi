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

package test.org.springdoc.api.app46;

import org.wapache.openapi.v3.annotations.ExternalDocumentation;
import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/persons/{subscriptionId}")
	@Operation(operationId = "operationId", summary = "Operation Summary", description = "Operation Description", tags = {
			"Example Tag" }, externalDocs = @ExternalDocumentation(description = "External documentation description", url = "http://url.com"), parameters = {
			@Parameter(in = ParameterIn.PATH, name = "subscriptionId", required = true, description = "parameter description", allowEmptyValue = true, allowReserved = true, schema = @Schema(type = "string", format = "uuid", description = "the generated UUID", accessMode = Schema.AccessMode.READ_ONLY)) }, responses = {
			@ApiResponse(responseCode = "200", description = "voila!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))) })
	public String persons(String subscriptionId) {
		return "OK";
	}

}