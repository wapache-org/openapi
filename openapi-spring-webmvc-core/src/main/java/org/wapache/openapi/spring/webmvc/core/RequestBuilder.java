/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.wapache.openapi.spring.webmvc.core;

import java.util.List;
import java.util.Optional;

import org.wapache.openapi.spring.core.AbstractRequestBuilder;
import org.wapache.openapi.spring.core.GenericParameterBuilder;
import org.wapache.openapi.spring.core.OperationBuilder;
import org.wapache.openapi.spring.core.RequestBodyBuilder;
import org.wapache.openapi.spring.core.customizers.ParameterCustomizer;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.wapache.openapi.spring.core.SpringDocUtils;

/**
 * The type Request builder.
 * @author bnasslahsen
 */
public class RequestBuilder extends AbstractRequestBuilder {

	static {
		SpringDocUtils.getConfig()
				.addRequestWrapperToIgnore(javax.servlet.ServletRequest.class)
				.addRequestWrapperToIgnore(javax.servlet.ServletResponse.class)
				.addRequestWrapperToIgnore(javax.servlet.http.HttpServletRequest.class)
				.addRequestWrapperToIgnore(javax.servlet.http.HttpServletResponse.class)
				.addRequestWrapperToIgnore(javax.servlet.http.HttpSession.class)
				.addRequestWrapperToIgnore(javax.servlet.http.HttpSession.class);
	}

	/**
	 * Instantiates a new Request builder.
	 *
	 * @param parameterBuilder the parameter builder 
	 * @param requestBodyBuilder the request body builder 
	 * @param operationBuilder the operation builder 
	 * @param parameterCustomizers the parameter customizers 
	 * @param localSpringDocParameterNameDiscoverer the local spring doc parameter name discoverer
	 */
	public RequestBuilder(GenericParameterBuilder parameterBuilder,
						  RequestBodyBuilder requestBodyBuilder,
                          OperationBuilder operationBuilder,
						  Optional<List<ParameterCustomizer>> parameterCustomizers,
                          LocalVariableTableParameterNameDiscoverer localSpringDocParameterNameDiscoverer) {
		super(parameterBuilder, requestBodyBuilder, operationBuilder, parameterCustomizers, localSpringDocParameterNameDiscoverer);
	}
}
