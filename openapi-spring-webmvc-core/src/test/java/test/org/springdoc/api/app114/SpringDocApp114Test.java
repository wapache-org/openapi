package test.org.springdoc.api.app114;

import javax.money.MonetaryAmount;

import org.wapache.openapi.spring.core.SpringDocUtils;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SpringDocApp114Test extends AbstractSpringDocTest {

	@SpringBootApplication
	static class SpringDocTestApp {}

	static {
		SpringDocUtils.getConfig().replaceWithClass(MonetaryAmount.class, org.wapache.openapi.spring.core.converters.models.MonetaryAmount.class);
	}
}
