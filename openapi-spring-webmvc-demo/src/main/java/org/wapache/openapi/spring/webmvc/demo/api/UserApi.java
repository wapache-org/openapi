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

/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.wapache.openapi.spring.webmvc.demo.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.wapache.openapi.spring.webmvc.demo.model.User;
import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.Explode;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.enums.ParameterStyle;
import org.wapache.openapi.v3.annotations.headers.Header;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;
import org.wapache.openapi.v3.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "org.springdoc.demo.app2.codegen.languages.SpringCodegen", date = "2019-07-11T00:09:29.839+02:00[Europe/Paris]")

@Tag(name = "user", description = "用户接口")
public interface UserApi {

	default UserApiDelegate getDelegate() {
		return new UserApiDelegate() {
		};
	}

	@Operation(summary = "创建用户", description = "只有登录后才有权限执行此操作.", tags = { "user" })
	@ApiResponses(value = { @ApiResponse(description = "操作成功", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }) })
	@PostMapping(value = "/user", consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
	default ResponseEntity<Void> createUser(
			@Parameter(description = "用户信息") @Valid @RequestBody User user) {
		return getDelegate().createUser(user);
	}

	@Operation(summary = "批量创建用户", tags = { "user" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "操作成功") })

	@PostMapping(value = "/user/createWithArray", consumes = { "application/json" })
	default ResponseEntity<Void> createUsersWithArrayInput(
			@Parameter(description = "用户信息列表", required = true) @Valid @RequestBody List<User> user) {
		return getDelegate().createUsersWithArrayInput(user);
	}

	@Operation(summary = "批量创建用户", tags = { "user" })
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "操作成功", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }),
		@ApiResponse(description = "操作成功")
	})
	@PostMapping(value = "/user/createWithList", consumes = { "application/json" })
	default ResponseEntity<Void> createUsersWithListInput(@Parameter @Valid @RequestBody List<User> user) {
		return getDelegate().createUsersWithListInput(user);
	}

	@Operation(summary = "删除用户", description = "根据用户名删除用户", tags = { "user" })
	@ApiResponses(value = {
		@ApiResponse(responseCode = "400", description = "用户名不合法"),
		@ApiResponse(responseCode = "404", description = "用户不存在")
	})
	@DeleteMapping(value = "/user/{username}")
	default ResponseEntity<Void> deleteUser(
			@Parameter(description = "用户名", required = true) @PathVariable("username") String username) {
		return getDelegate().deleteUser(username);
	}

	@Operation(summary = "根据用户名获取用户信息", tags = { "user" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "操作成功", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "400", description = "用户名不合法", content = @Content),
			@ApiResponse(responseCode = "404", description = "用户不存在", content = @Content) 
	})
	@GetMapping(value = "/user/{username}")
	default ResponseEntity<User> getUserByName(
			@Parameter(description = "用户名, 可以用`user1`这个用户名来测试. ", required = true) @PathVariable("username") String username) {
		return getDelegate().getUserByName(username);
	}

	@Operation(summary = "登录", tags = { "user" }, description = "可以随便填")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", headers = {
				@Header(name = "X-Rate-Limit", description = "用户每小时调用次数限制", schema = @Schema(type = "integer", format = "int32")),
				@Header(name = "X-Expires-After", description = "令牌过期时间, 格式是UTC的格式", schema = @Schema(type = "string", format = "date-time")) 
			},
			description = "操作成功", 
			content = @Content(schema = @Schema(implementation = String.class))
		),
		@ApiResponse(responseCode = "400", description = "用户名或密码不合法", content = @Content)
	})
	@GetMapping(value = "/user/login", produces = { "application/json", "application/xml" })
	default ResponseEntity<String> loginUser(
		@NotNull
		@Parameter(description = "用户名", required = false)
		@Valid
		@RequestParam(value = "username", required = false)
		String username,

		@NotNull
		@Parameter(description = "密码(明文)", required = false)
		@Valid
		@RequestParam(value = "password", required = false)
		String password
	) {
		return getDelegate().loginUser(username, password);
	}

	@Operation(summary = "注销登录", tags = { "user" })
	@ApiResponses(value = { @ApiResponse(description = "操作成功") })
	@GetMapping(value = "/user/logout")
	default ResponseEntity<Void> logoutUser() {
		return getDelegate().logoutUser();
	}

	@Operation(summary = "更新用户信息", description = "只有登录后才有权限执行此操作.", tags = { "user" })
	@ApiResponses(value = @ApiResponse(description = "操作成功"))
	@PutMapping(value = "/user/{username}", consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
	default ResponseEntity<Void> updateUser(
			@Parameter(description = "用户名", required = true, explode = Explode.FALSE, in = ParameterIn.PATH, name = "username", style = ParameterStyle.SIMPLE, schema = @Schema(type = "string")) @PathVariable("username") String username,
			@Parameter(description = "用户信息") @Valid @RequestBody User user) {
		return getDelegate().updateUser(username, user);
	}

}
