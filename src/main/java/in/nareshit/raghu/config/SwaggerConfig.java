package in.nareshit.raghu.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"default","qa"})
public class SwaggerConfig {

	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("in.nareshit.raghu.rest"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(apiInfo())
				;
	}

	private ApiInfo apiInfo() {

		return new ApiInfo("My App Title", //title
				"Hello Test Application, sample app", // description 
				"3.2 GA", //version 
				"http://nareshit.com",//Terms of Service URL 
				new Contact("RAGHU S", "http://raghus.com", "javabyraghu@gmail.com"), //developer contact 
				"NIT License", //license provider name
				"http://nareshit.com/license",  //license URL
				Collections.emptyList()); //client names
	}
}
