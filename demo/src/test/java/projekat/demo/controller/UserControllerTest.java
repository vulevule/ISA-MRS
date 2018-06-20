package projekat.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import projekat.demo.TestUtil;
import projekat.demo.constants.UserConstants;
import projekat.demo.dto.UserDTO;
import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.Visitor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	private static final String URL_REGISTRATION = "/users/registrationUser";
	private static final String URL_LOGIN = "/users/loginUser";
	private static final String URL_UPDATE = "/users/updateAccount";
	private static final String URL_ADD_FRIEND = "/users/addFriend";
	private static final String URL_ACCEPT_FRIEND = "/users/acceptFriendship";
	private static final String URL_DELETE_FRIEND = "/users/deleteFriendship";
	private static final String URL_NOTACCEPT_FRIEND = "/users/notAcceptFriendship";



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

	//registracija novog korisnika
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveVisitor() throws Exception {
		UserDTO user = new UserDTO();
		user.setFirstName(UserConstants.NEW_FIRST_NAME);
		user.setLastName(UserConstants.NEW_LAST_NAME);
		user.setEmail("jovanovicj296@gmail.com");
		user.setAddress(UserConstants.NEW_ADDRESS);
		user.setPassword(UserConstants.NEW_PASSWORD);
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(UserConstants.NEW_TYPE);
		user.setRepeatPassword(UserConstants.NEW_PASSWORD);

		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_REGISTRATION).contentType(contentType).content(json))
				.andExpect(status().isCreated());
	}

	//registracija korisnika koji vec postoji
	@Test
	@Transactional
	public void testSaveExistVisitor() throws Exception {
		UserDTO user = new UserDTO();
		user.setFirstName(UserConstants.NEW_FIRST_NAME);
		user.setLastName(UserConstants.NEW_LAST_NAME);
		user.setEmail("jovanaj33@gmail.com");
		user.setAddress(UserConstants.NEW_ADDRESS);
		user.setPassword(UserConstants.NEW_PASSWORD);
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(UserConstants.NEW_TYPE);
		user.setRepeatPassword(UserConstants.NEW_PASSWORD);

		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_REGISTRATION).contentType(contentType).content(json))
				.andExpect(status().isAlreadyReported());
	}
	
	
	//logovanje posetioca
		@Test
		public void testLogin() throws Exception{
			LoginUser lu = new LoginUser("jovanaj33@gmail.com", "jovanajovanovic");
			String json = TestUtil.json(lu);
			this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isOk());
		}

	//logovanje korisnika koji nije aktiviran
	@Test
	public void testLoginNotActivateUser() throws Exception{
		LoginUser lu = new LoginUser("maram@yahoo.com", "maramarkovic");
		String json = TestUtil.json(lu);
		this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isNotFound());
	}
	
	
	//izmene korisnickog naloga 
	@Test
	@Transactional
	@Rollback
	public void testUpdateUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setLastName(UserConstants.NEW_FIRST_NAME1);
		user.setFirstName(UserConstants.NEW_LAST_NAME);
		user.setEmail("jovanaj33@gmail.com");
		user.setAddress(UserConstants.NEW_ADDRESS2);
		user.setPassword(UserConstants.NEW_PASSWORD1);
		user.setRepeatPassword(UserConstants.NEW_PASSWORD1);
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(RoleType.VISITOR);

		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaj33@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_UPDATE).session(session).contentType(contentType).content(json))
				.andExpect(status().isOk());
	}
	


	@Test
	@Transactional
	@Rollback(true)
	public void testAddFriend() throws Exception{
		//sender
		Visitor v = new Visitor("Jovana", "Jovanovic", "jocaftn15@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57878",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
	
		this.mockMvc.perform(get(URL_ADD_FRIEND + "?email=jovanaftn@yahoo.com" ).session(session)).andExpect(status().isCreated());

		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptFriendship() throws Exception{
		//receiver - user sa sesije
		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaftn@yahoo.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		
		this.mockMvc.perform(get(URL_ACCEPT_FRIEND + "?email=" + "jovanaj33@gmail.com").session(session)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testNotAcceptFriendship() throws Exception{
		//receiver - user sa sesije
		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaftn@yahoo.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		
		this.mockMvc.perform(get(URL_NOTACCEPT_FRIEND + "?email="+"jovanaj33@gmail.com").session(session)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFriend() throws Exception{
		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaj33@gmail.com", "jovanajovanovic", "Novosadskog sajma 5, Novi Sad", "57464745",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		this.mockMvc.perform(get(URL_DELETE_FRIEND + "?email=" + "jocaftn15@gmail.com").session(session)).andExpect(status().isOk());
	}
}
