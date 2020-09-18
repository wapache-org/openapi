package test.org.springdoc.api.app109;

import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/api/v1/resource")
	public Resource getResource() {
		return new ByteArrayResource(new byte[] {});
	}

	@GetMapping("/api/v1/bytearray")
	@ApiResponse(content = @Content(schema = @Schema(type = "string", format = "binary")))
	public byte[] getByteArray() {
		return new byte[] {};
	}
}
