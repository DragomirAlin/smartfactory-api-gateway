package ro.dragomiralin.gateway;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Bean
//	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiParameters, RouteDefinitionLocator routeDefinitionLocator) {
//		List<GroupedOpenApi> groups = new ArrayList<>();
//		List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions().collectList().block();
//		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//			String name = routeDefinition.getId().replaceAll("-service", "");
//			swaggerUiParameters.addGroup(name);
//			groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
//		});
//		groups.forEach(defi -> {
//			System.out.println(defi.getPathsToMatch());
//		});
//		return groups;
//	}
}


