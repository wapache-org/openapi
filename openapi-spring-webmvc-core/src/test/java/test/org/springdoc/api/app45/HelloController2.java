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

package test.org.springdoc.api.app45;

import java.util.Collections;
import java.util.List;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.media.ArraySchema;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.security.SecurityRequirement;
import org.wapache.openapi.v3.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "People", description = "Use this resource to serve all requests and initiate all operations related to people")
@SecurityRequirement(name = "bearer")
@RestController
@RequestMapping(value = "/v1/people2")
public class HelloController2 {


	@Operation(description = "List all persons")
	@ApiResponse(responseCode = "200", description = "", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))))
	@GetMapping(path = "/list", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> list() {
		PersonDTO person = new PersonDTO();
		person.setFirstName("Nass");
		return Collections.singletonList(person);
	}

	@Operation(description = "List all persons")
	@ApiResponse(responseCode = "200", description = "", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))))
	@GetMapping(path = "/listTwo", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> listTwo() {
		PersonDTO person = new PersonDTO();
		person.setFirstName("Nass");
		return Collections.singletonList(person);
	}

}