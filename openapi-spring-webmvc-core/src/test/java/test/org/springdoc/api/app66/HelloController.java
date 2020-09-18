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

package test.org.springdoc.api.app66;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "Health check / ping API")
@RestController
public class HelloController {

	@Operation(summary = "Check server status", description = "Check server status, will return 200 with simple string if alive. Do nothing else.")
	@GetMapping(value = { "/ping", "/health", "/" }, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> ping(UndocumentedClass possiblyInjectedByAspect) {
		return ResponseEntity.ok("Healthy");
	}

}