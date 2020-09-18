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

package test.org.springdoc.api.app24;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.enums.SecuritySchemeIn;
import org.wapache.openapi.v3.annotations.enums.SecuritySchemeType;
import org.wapache.openapi.v3.annotations.security.SecurityRequirement;
import org.wapache.openapi.v3.annotations.security.SecurityScheme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityScheme(type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, name = "Authorization", description = "A core-auth Bearer token")
public class HelloController {

	@Operation(summary = "Add a new person to the store", description = "", security = {
			@SecurityRequirement(name = "Authorization") })
	@GetMapping(value = "/persons")
	public void persons(@Valid @NotBlank String name) {

	}

}
