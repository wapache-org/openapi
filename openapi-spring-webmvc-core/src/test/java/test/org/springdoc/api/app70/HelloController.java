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

package test.org.springdoc.api.app70;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import test.org.springdoc.api.app70.customizer.CustomizedOperation;
import test.org.springdoc.api.app70.customizer.CustomizedParameter;
import test.org.springdoc.api.app70.model.ApiType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@CustomizedOperation
	@Operation(description = "Some operation")
	@GetMapping("/example/{test}")
	public ApiType test(@PathVariable @CustomizedParameter @Parameter(description = "Parameter description") String test) {
		return new ApiType();
	}
}
