package com.jinloes.polls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.jinloes.polls.model.Poll;
import com.jinloes.polls.repository.PollRepository;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PollControllerTest {
	@LocalServerPort
	protected int port;
	@Autowired
	private PollService pollService;
	@Autowired
	private PollRepository pollRepository;
	private RequestSpecification requestSpec;
	private String pollId;

	@Before
	public void setUp() throws Exception {
		pollId = pollService.save(Poll.builder()
				.id(pollId)
				.name("my poll")
				.build()).block().getId();
		this.requestSpec = new RequestSpecBuilder()
				.setPort(port)
				.setAccept(ContentType.JSON)
				.build();
	}

	@After
	public void tearDown() {
		pollRepository.deleteAll();
	}

	@Test
	public void testCreate() {
		Poll poll = Poll.builder()
				.name("My Poll")
				.build();

		given(requestSpec)
				.contentType(ContentType.JSON)
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

	@Test
	public void testGet() {
		given(requestSpec)
				.when()
				.get("/polls/{pollId}", pollId)
				.then()
				.log().ifError()
				.assertThat()
				.statusCode(200)
				.body("name", is("my poll"));
	}

	@Test
	public void testGetNotFound() {
		given(requestSpec)
				.when()
				.get("/polls/{pollId}", "123")
				.then()
				.log().ifError()
				.assertThat()
				.statusCode(404);
	}

	@Test
	public void testGetAll() {
		given(requestSpec)
				.when()
				.get("/polls")
				.then()
				.log().everything(true)
				.assertThat()
				.statusCode(200)
				.body("_embedded.polls[0].name", is("my poll"));
	}
}
