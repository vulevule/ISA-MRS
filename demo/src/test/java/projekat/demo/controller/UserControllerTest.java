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
import projekat.demo.constants.UserConstants;
import projekat.demo.model.LoginUser;
import projekat.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	private static final String URL_REGISTRATION = "/users/registrationUser";
	private static final String URL_LOGIN = "/users/loginUser";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveVisitor() throws Exception{
		User user = new User();
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setSurname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(UserConstants.NEW_PASSWORD);
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(UserConstants.NEW_TYPE);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_REGISTRATION).contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSaveAdmin() throws Exception{
		User user = new User();
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setSurname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(UserConstants.NEW_PASSWORD);
		user.setPhone(UserConstants.NEW_PHONE);
		user.setType(UserConstants.NEW_TYPE1);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_REGISTRATION).contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	/*
	 * MOCI CEMO DA PROVERIMO TEK KADA NAMESTIMO DA SE BAZA CUVA, ODNOSNO DA SE NE BRISE POSLE SVAKOG GASENJA APLIKACIJE
	@Test
	public void testLoginActivateUser() throws Exception{
		LoginUser lu = new LoginUser();
		lu.setUsername(UserConstants.LOGIN_EMAIL);
		lu.setPassword(UserConstants.LOGIN_PASSWORD);
		
		String json = TestUtil.json(lu);
		this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isOk());
	}

	*/
	@Test
	public void testLoginNotActivateVisitor() throws Exception{
		LoginUser lu = new LoginUser();
		lu.setUsername(UserConstants.LOGIN_EMAIL);
		lu.setPassword(UserConstants.LOGIN_PASSWORD);
		
		String json = TestUtil.json(lu);
		this.mockMvc.perform(post(URL_LOGIN).contentType(contentType).content(json)).andExpect(status().isBadRequest());
	}
	
	
}
