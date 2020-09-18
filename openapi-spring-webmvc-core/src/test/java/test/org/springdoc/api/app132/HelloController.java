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

package test.org.springdoc.api.app132;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController<DataRequest> {


	@Operation(summary = "Create the organization", description = "Create the organization")
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "204",
							description = "The organization was created successfully"),
					@ApiResponse(
							responseCode = "400",
							description = "Invalid argument",
							content =
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = RestControllerError.class))),
					@ApiResponse(
							responseCode = "409",
							description = "An organization with the specified ID already exists",
							content =
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = RestControllerError.class))),
					@ApiResponse(
							responseCode = "500",
							description =
									"An error has occurred and the request could not be processed at this time",
							content =
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = RestControllerError.class)))
			})
	@RequestMapping(
			value = "/organizations",
			method = RequestMethod.POST,
			produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createOrganization(
			@Parameter(name = "organization", description = "i want to override the description of this object", required = true)
			@RequestBody
					Organization organization) {


	}

}
