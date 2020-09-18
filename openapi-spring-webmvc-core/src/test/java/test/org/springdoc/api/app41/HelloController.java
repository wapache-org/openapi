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

package test.org.springdoc.api.app41;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HelloController {

	@Operation(description = "Download file")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "File resource", content = @Content(schema = @Schema(implementation = java.io.File.class))),
			@ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = java.lang.Error.class))),
			@ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = java.lang.Error.class))) })
	@GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<java.io.File> getFile(
			@NotNull @Parameter(description = "File path", required = true) @Valid @RequestParam(value = "path") String path) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

}