package RestAPI;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;


public class Badges_ID {	
	
	@Test
	public void getBadgeInfo() {
	
	Response res=given().log().all()
					.queryParam("key", "U4DMV*8nvpm3EOpvf69Rxw((")
					.queryParam("site", "stackoverflow")
					.queryParam("order", "desc")
					.queryParam("sort", "rank")
					.queryParam("filter", "default")
					.pathParam("id", "5")
				.when()
					.get("https://api.stackexchange.com/2.3/badges/{id}")
				.then()
				.assertThat()
				.statusCode(200)
				.body("items[0]", hasEntry("rank", "bronze"))
				.extract().response();
	
		Assert.assertEquals("5", res.jsonPath().getString("items[0].badge_id").toString());
	}
	
	@Test
	public void invalidReq() {
	
	Response res=given().log().all()
					.pathParam("id", "5")
				.when()
					.post("https://api.stackexchange.com/2.3/badges/{id}")
				.then()
				.assertThat()
				.statusCode(400)
				.body("error_message",equalTo("no method found with this name"))
				.extract().response();
	
	System.out.println(res.asString());
	}
}
