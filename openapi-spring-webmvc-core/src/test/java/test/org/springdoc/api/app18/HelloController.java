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

package test.org.springdoc.api.app18;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.wapache.openapi.v3.annotations.Parameter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/persons")
	public String persons(@NotBlank String name) {
		return "OK";
	}

	@GetMapping(value = "/persons2")
	public String persons2(@NotBlank @Parameter(description = "persons name") String name) {
		return "OK";
	}

	@GetMapping(value = "/persons3")
	public String persons3(@NotBlank @Parameter(description = "persons name") @RequestParam String name) {
		return "OK";
	}

	@GetMapping(value = "/persons4")
	public String persons4(@PositiveOrZero int age) {
		return "OK";
	}

	@GetMapping(value = "/persons5")
	public String persons5(@NegativeOrZero int age) {
		return "OK";
	}

	@GetMapping(value = "/persons6")
	public String persons6(@NotEmpty @Parameter(description = "persons name") String name) {
		return "OK";
	}

}
