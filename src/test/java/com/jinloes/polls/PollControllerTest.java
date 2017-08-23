package com.jinloes.polls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.jinloes.polls.model.Poll;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PollControllerTest {
	@LocalServerPort
	protected int port;
	private RequestSpecification requestSpec;

	@Before
	public void setUp() throws Exception {
		this.requestSpec = new RequestSpecBuilder()
				.setPort(port)
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.build();
	}

	@Test
	public void testCreate() {
		Poll poll = Poll.builder()
				.name("My Poll")
				.build();

		given(requestSpec)
				.body(poll)
				.when()
				.post("/polls")
				.then()
				.log().ifError()
				.assertThat()
				.statusCode(201);
	}

	@Test
	public void testCreateEmptyName() {
		Poll poll = Poll.builder()
				.build();

		given(requestSpec)
				.body(poll)
				.when()
				.post("/polls")
				.then()
				.log().ifError()
				.assertThat()
				.statusCode(400)
				.body("subErrors[0].field", is("name"));
	}
}
