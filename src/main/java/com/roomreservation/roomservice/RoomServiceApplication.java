package com.roomreservation.roomservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class RoomServiceApplication {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from RESTEasy Reactive";
	}
}


//@EnableSwagger2
//@SpringBootApplication
//public class RoomServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(RoomServiceApplication.class, args);
//	}
//
//	@Bean
//	public Docket get() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(createApiInfo());
//	}
//
//	private ApiInfo createApiInfo() {
//		return new ApiInfo(
//				"RoomReservation.RoomService API",
//				"opis...",
//				"0.0.1",
//				"",
//				new Contact("Michal Bednarczyk", "", "michal.bednarczyk1993@gmail.com"),
//				"Apache License 2.0",
//				"https://github.com/michalbednarczyk1993/RoomReservation.RoomsService/blob/master/LICENSE",
//				Collections.emptyList()
//		);
//	}
//
//}
