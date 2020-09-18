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

import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.info.Info;
import org.wapache.openapi.v3.models.info.License;
import org.wapache.openapi.v3.models.media.StringSchema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.security.SecurityScheme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDocTestApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringDocTestApp.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("basicScheme",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
						.addParameters("myHeader1",
								new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
						.addHeaders("myHeader2",
								new Header().description("myHeader2 header").schema(new StringSchema())))
				.info(new Info().title("Petstore API").version("v0").description(
						"This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
