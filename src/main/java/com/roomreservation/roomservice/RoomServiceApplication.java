package com.roomreservation.roomservice;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@OpenAPIDefinition(
		tags = {
				@Tag(
						name="RoomReservation.RoomService API",
						description="opis...")
		},
		info = @Info(
				title="RoomReservation.RoomService API",
				version = "0.0.1",
				contact = @Contact(
						name = "Michał Bednarczyk",
						email = "michal.bednarczyk1993@gmail.com"),
				license = @License(
						name = "Apache-2.0 license",
						url = "https://github.com/michalbednarczyk1993/RoomReservation.RoomsService/blob/master/LICENSE"))
)
public class RoomServiceApplication extends Application {
}
