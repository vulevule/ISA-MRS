package projekat.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import projekat.demo.TestUtil;
import projekat.demo.dto.ReservationDTO;
import projekat.demo.model.RoleType;
import projekat.demo.model.Visitor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {
	private static final String URL_ADDREGISTRATION = "/reservation/createReservation";
	private static final String URL_DELETE_RESERVATION = "/reservation/cancelReservation";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MockHttpSession session;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	//dodavanje  rezervacije za sediste koje je vec rezervisano
	@Test
	@Transactional
	@Rollback(true)
	public void addReservation() throws Exception{
		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaj33@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		Set<String>seats = new HashSet<String>();
		Set<String>friends = new HashSet<String>();
		seats.add("1-5");
		ReservationDTO rd = new ReservationDTO(1,seats, friends);
		String json = TestUtil.json(rd);
		this.mockMvc.perform(post(URL_ADDREGISTRATION).session(session).contentType(contentType).content(json))
		.andExpect(status().isBadRequest());
	}
	
	//dodavanje nove rezervacije sa pozivom prijatelja
	@Test
	@Transactional
	@Rollback(true)
	public void addReservationInviteFriends() throws Exception{
		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaj33@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		Set<String>seats = new HashSet<String>();
		Set<String>friends = new HashSet<String>();
		friends.add("jocaftn15@gmail.com");
		seats.add("2-6");
		seats.add("2-8");
		ReservationDTO rd = new ReservationDTO(1,seats, friends);
		String json = TestUtil.json(rd);
		this.mockMvc.perform(post(URL_ADDREGISTRATION).session(session).contentType(contentType).content(json))
		.andExpect(status().isCreated());
	}
	
	//brisanje postojece projekcije
	@Test
	@Transactional
	@Rollback(true)
	public void deleteReservation() throws Exception{
		Visitor v = new Visitor("Jovana", "Jovanovic", "jocaftn15@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57878",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		this.mockMvc.perform(get(URL_DELETE_RESERVATION + "?id=" + 1).session(session)).andExpect(status().isOk());

	}
}
