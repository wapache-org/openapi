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

package test.org.springdoc.api.app5;

import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.info.Info;
import org.wapache.openapi.v3.models.tags.Tag;

import org.springframework.context.annotation.Bean;

public class CustomOpenAPIConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Custom API").version("100")).addTagsItem(new Tag().name("mytag"));
	}
}
