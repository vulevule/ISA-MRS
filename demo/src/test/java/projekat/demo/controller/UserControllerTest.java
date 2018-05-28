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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import projekat.demo.TestUtil;
import projekat.demo.constants.UserConstants;
import projekat.demo.model.LoginUser;
import projekat.demo.model.RoleType;
import projekat.demo.model.Visitor;
import projekat.demo.model.DTO.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	private static final String URL_REGISTRATION = "/users/registrationUser";
	private static final String URL_LOGIN = "/users/loginUser";
	private static final String URL_UPDATE = "/users/updateAccount";
	private static final String URL_ADD_FRIEND = "/users/addFriend";
	private static final String URL_ACCEPT_FRIEND = "/users/acceptFriendship";
	private static final String URL_DELETE_FRIEND = "/users/deleteFriendship";


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
//	@Autowired
	//private MockHttpSession session;

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
		user.setEmail(UserConstants.NEW_EMAIL);
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
	/*
	
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
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(UserConstants.NEW_TYPE1);

		Visitor v = new Visitor("Jovana", "Jovanovic", "jovanaj33@gmail.com", "jovanajovanovic", "nn 56,nn", "875778",
				true, RoleType.VISITOR);
		session.setAttribute("loginUser", v);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_UPDATE).session(session).contentType(contentType).content(json))
				.andExpect(status().isOk());
	}
	
	//logovanje posetioca
	@Test
	public void testLogin() throws Exception{
		LoginUser lu = new LoginUser("jovanaj33@gmail.com", "jovanajovanovic");
		String json = TestUtil.json(lu);
		this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isOk());
	}

/*	
	@Test
	public void testLoginNotActivateVisitor() throws Exception {
		LoginUser lu = new LoginUser();
		lu.setUsername(UserConstants.LOGIN_EMAIL);
		lu.setPassword(UserConstants.LOGIN_PASSWORD);

		String json = TestUtil.json(lu);
		this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isNotFound());
	}
/*
	@Test
	@Transactional
	@Rollback(true)
	public void testAddFriend() throws Exception{
		Friendship fs = new Friendship();
		User u1 = new User(UserConstants.NEW_FIRST_NAME, UserConstants.NEW_LAST_NAME, UserConstants.NEW_EMAIL, UserConstants.NEW_PASSWORD, UserConstants.NEW_ADDRESS,UserConstants.NEW_PHONE, UserConstants.NEW_TYPE, true, "", null, null);
		User u2 = new User(UserConstants.NEW_FIRST_NAME2, UserConstants.NEW_LAST_NAME2, UserConstants.NEW_EMAIL2, UserConstants.NEW_PASSWORD2, UserConstants.NEW_ADDRESS2,UserConstants.NEW_PHONE2, UserConstants.NEW_TYPE2, true, "", null, null);

		fs.setSender(u1);
		fs.setReceiver(u2);
		fs.setStatus(FriendshipStatus.SEND_REQUEST);
		String json = TestUtil.json(fs);
		this.mockMvc.perform(post(URL_ADD_FRIEND).contentType(contentType).content(json)).andExpect(status().isCreated());

		
	}
	/*
	@Test
	public void testAcceptFriendship() throws Exception{
		Friendship fs = new Friendship();
		User u1 = new User(UserConstants.NEW_FIRST_NAME, UserConstants.NEW_LAST_NAME, UserConstants.NEW_EMAIL, UserConstants.NEW_PASSWORD, UserConstants.NEW_ADDRESS,UserConstants.NEW_PHONE, UserConstants.NEW_TYPE, true, "", null, null);
		User u2 = new User(UserConstants.NEW_FIRST_NAME2, UserConstants.NEW_LAST_NAME2, UserConstants.NEW_EMAIL2, UserConstants.NEW_PASSWORD2, UserConstants.NEW_ADDRESS2,UserConstants.NEW_PHONE2, UserConstants.NEW_TYPE2, true, "", null, null);

		fs.setSender(u1);
		fs.setReceiver(u2);
		fs.setStatus(FriendshipStatus.SEND_REQUEST);
		String json = TestUtil.json(fs);
		this.mockMvc.perform(post(URL_ACCEPT_FRIEND).contentType(contentType).content(json)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeleteFriend() throws Exception{
		Friendship fs = new Friendship();
		User u1 = new User(UserConstants.NEW_FIRST_NAME, UserConstants.NEW_LAST_NAME, UserConstants.NEW_EMAIL, UserConstants.NEW_PASSWORD, UserConstants.NEW_ADDRESS,UserConstants.NEW_PHONE, UserConstants.NEW_TYPE, true, "", null, null);
		User u2 = new User(UserConstants.NEW_FIRST_NAME2, UserConstants.NEW_LAST_NAME2, UserConstants.NEW_EMAIL2, UserConstants.NEW_PASSWORD2, UserConstants.NEW_ADDRESS2,UserConstants.NEW_PHONE2, UserConstants.NEW_TYPE2, true, "", null, null);

		fs.setSender(u1);
		fs.setReceiver(u2);
		fs.setStatus(FriendshipStatus.SEND_REQUEST);
		String json = TestUtil.json(fs);
		this.mockMvc.perform(post(URL_DELETE_FRIEND).contentType(contentType).content(json)).andExpect(status().isBadRequest());
	}*/
	
}
