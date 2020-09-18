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

package test.org.springdoc.api.app25;

import java.time.Instant;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/check")
	@ResponseStatus(HttpStatus.OK)
	void check() {
	}

	@GetMapping(value = "/list")
	void list(

			@Parameter(name = "trackerId", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", example = "the-tracker-id")) @PathVariable String trackerId,
			@Parameter(name = "start", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:00:00.000Z")) @RequestParam(value = "start", required = false) Instant startDate,
			@Parameter(name = "end", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:10:00.000Z")) @RequestParam(value = "end", required = false) Instant endDate) {
	}

	@GetMapping(value = "/secondlist")
	void secondlist(
			@Parameter(name = "trackerId", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", example = "the-tracker-id")) @PathVariable String trackerId,
			@Parameter(name = "start", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:00:00.000Z")) @RequestParam(value = "start", required = false) Instant startDate,
			@Parameter(name = "end", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:10:00.000Z")) @RequestParam(value = "end", required = false) Instant endDate) {

	}

	@Operation(description = "Get last data from a tracker", parameters = {
			@Parameter(name = "trackerId", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", example = "the-tracker-id")),
			@Parameter(name = "start", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:00:00.000Z")),
			@Parameter(name = "end", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time", required = false, example = "1970-01-01T00:10:00.000Z")),
			@Parameter(name = "limit", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "number", required = false, example = "10")) }, responses = {
			@ApiResponse(responseCode = "200") })

	@GetMapping(value = "/values/{trackerId}/data")
	void thirdList(@PathVariable String trackerId, @RequestParam(value = "start", required = false) Instant start,
			@RequestParam(value = "end", required = false) Instant end,
			@RequestParam(value = "limit", required = false) Integer limit) {

	}
}
