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

package test.org.springdoc.api.app21;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.enums.SecuritySchemeType;
import org.wapache.openapi.v3.annotations.security.OAuthFlow;
import org.wapache.openapi.v3.annotations.security.OAuthFlows;
import org.wapache.openapi.v3.annotations.security.OAuthScope;
import org.wapache.openapi.v3.annotations.security.SecurityRequirement;
import org.wapache.openapi.v3.annotations.security.SecurityScheme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityScheme(name = "personstore_auth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}", scopes = {
		@OAuthScope(name = "write:persons", description = "modify persons in your account"),
		@OAuthScope(name = "read:persons", description = "read your persons") })))
public class HelloController {

	@Operation(summary = "Add a new person to the store", description = "", security = {
			@SecurityRequirement(name = "personstore_auth", scopes = { "write:persons", "read:persons" }) }, tags = {
			"person" })
	@GetMapping(value = "/persons")
	public void persons(@Valid @NotBlank String name) {

	}

}
