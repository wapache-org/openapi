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

package test.org.springdoc.api.app37;

import javax.validation.Valid;

import org.wapache.openapi.v3.annotations.parameters.ApiRequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HelloController {

	@PostMapping(path = "/bar/baz", consumes = "application/x.a+json", produces = MediaType.TEXT_PLAIN_VALUE)
	public Foo process(@ApiRequestBody Foo a) {
		return a;
	}

	@PostMapping(path = "/bar/baz", consumes = "application/x.b+json", produces = MediaType.TEXT_PLAIN_VALUE)
	public Bar process(@ApiRequestBody Bar b) {
		return b;
	}

	@PostMapping(path = "/bar/baz", consumes = "application/x.c+json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car process(@ApiRequestBody Car c) {
		return c;
	}

	@PostMapping(value = "/pets", consumes = "application/json")
	public ResponseEntity<Void> petsPost(@Valid @ApiRequestBody Pet pet) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PostMapping(value = "/pets", consumes = "text/plain")
	public ResponseEntity<Void> petsPost(@Valid @ApiRequestBody String pet) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}