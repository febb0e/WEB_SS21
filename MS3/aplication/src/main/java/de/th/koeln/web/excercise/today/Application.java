package de.th.koeln.web.excercise.today;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(new ApiInfoBuilder()
						.title("Excercise Today API")
						.description("API zum Upload neuer Posts")
						.version("0.0.1-SNAPSHOT")
						.license("MIT")
						.licenseUrl("https://opensource.org/licenses/MIT")
						.build())
				.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build();
	}
}
