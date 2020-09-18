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

package test.org.springdoc.api.app49;

import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.media.Content;
import org.wapache.openapi.v3.models.media.StringSchema;
import org.wapache.openapi.v3.models.responses.ApiResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

@SpringBootApplication
public class SpringDocTestApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocTestApp.class, args);
	}

	@Bean
	public OpenAPI defineOpenApi() {
		OpenAPI api = new OpenAPI();
		api.components(new Components().addResponses("Unauthorized",
				new ApiResponse().description("Unauthorized")
						.content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
								new org.wapache.openapi.v3.models.media.MediaType().schema(new StringSchema())))));
		return api;
	}

}
