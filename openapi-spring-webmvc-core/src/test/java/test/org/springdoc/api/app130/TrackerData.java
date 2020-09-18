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

package test.org.springdoc.api.app130;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.wapache.openapi.v3.annotations.Hidden;
import org.wapache.openapi.v3.annotations.media.Schema;

@Hidden
public class TrackerData {

	@JsonProperty("trackerId")
	String trackerId;

	@Schema(name = "timestamp", type = "string", format = "date-time", required = true, example = "2018-01-01T00:00:00Z")
	@JsonProperty("timestamp")
	Instant timestamp;

	@Schema(name = "value", type = "number", format = "double", description = "The data value", required = true, example = "19.0")
	@JsonProperty("value")
	Double value;

}
