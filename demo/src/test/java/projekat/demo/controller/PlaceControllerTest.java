package projekat.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import projekat.demo.TestUtil;
import projekat.demo.constants.PlaceConstants;
import projekat.demo.model.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceControllerTest {
	private static final String URL_ADD_PLACE = "/places/createPlace";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSavePlace() throws Exception {
		Place place = new Place();
		place.setName(PlaceConstants.NEW_NAME);
		place.setDescription(PlaceConstants.NEW_DESCRIPTION);
		place.setAddress(PlaceConstants.NEW_ADDRESS);
		place.setType(PlaceConstants.NEW_TYPE);

		String json = TestUtil.json(place);
		this.mockMvc.perform(post(URL_ADD_PLACE).contentType(contentType).content(json))
				.andExpect(status().isCreated());
	}

}
