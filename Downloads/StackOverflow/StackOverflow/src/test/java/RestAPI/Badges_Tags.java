package RestAPI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class Badges_Tags {	
	@Test
	public void getBadgeInfo() {
	
	Response res=given().log().all()
					.queryParam("key", "U4DMV*8nvpm3EOpvf69Rxw((")
					.queryParam("site", "stackoverflow")
					.queryParam("order", "desc")
					.queryParam("sort", "rank")
					.queryParam("filter", "default")
				.when()
					.get("https://api.stackexchange.com/2.3/badges/tags")
				.then()
				.assertThat()
				.statusCode(200)
				.body("items[1]", hasEntry("rank", "bronze"))
				.extract().response();
	System.out.println(res.asPrettyString());
		
	}
	
	@Test
	public void invalidReq() {
	
	Response res=given().log().all()
				.when()
					.get("https://api.stackexchange.com/2.3/badges/tags")
				.then()
				.assertThat()
				.statusCode(400)
				.body("error_name",equalTo("bad_parameter"))
				.extract().response();
	
	System.out.println(res.asString());
	}
}
