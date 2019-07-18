package swust.yang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

/**
 * swagger文档
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiger {
	static List<Parameter> getParamList() {
		List<Parameter> pars = new ArrayList<Parameter>();
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();

		ParameterBuilder signPar = new ParameterBuilder();
		signPar.name("sign").description("数据签名").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		ParameterBuilder timePar = new ParameterBuilder();
		timePar.name("timestamp").description("时间戳").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		pars.add(signPar.build());
		pars.add(timePar.build());
		return pars;
	}

	@Bean
	public Docket teachersDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("C/C++语言代码评阅插件系统")
				.apiInfo(new ApiInfoBuilder().title("接口文档")
						.contact(new Contact("西南科技大学", "www.swust.edu.cn", "huizhuoli@foxmail.com")).version("1.0")
						.build())
				.select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("swust.yang.controller")).build()
				.globalOperationParameters(SwaggerConfiger.getParamList());
	}
}