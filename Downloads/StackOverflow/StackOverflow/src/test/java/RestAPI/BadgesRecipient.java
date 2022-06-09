package RestAPI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class BadgesRecipient {	
	
	@Test
	public void getRecipient() {
	
	Response res=given().log().all()
					.queryParam("key", "U4DMV*8nvpm3EOpvf69Rxw((")
					.queryParam("site", "stackoverflow")
					.queryParam("filter", "default")
				.when()
					.get("https://api.stackexchange.com/2.3/badges/recipients")
				.then()
				.assertThat()
				.statusCode(200)
				.body("items[4]", hasKey("badge_type"))
				.extract().response();
	
	System.out.println(res.asString());
	}
	
	@Test
	public void InvalidReq_Post() {
	
	Response res=given().log().all()
					.queryParam("key", "U4DMV*8nvpm3EOpvf69Rxw((")
				.when()
					.post("https://api.stackexchange.com/2.3/badges/recipients")
				.then()
				.assertThat()
				.statusCode(400)
				.body("error_message",equalTo("no method found with this name"))
				.extract().response();
	
	System.out.println(res.asString());
	System.out.println(res.getStatusCode());
	}
}
