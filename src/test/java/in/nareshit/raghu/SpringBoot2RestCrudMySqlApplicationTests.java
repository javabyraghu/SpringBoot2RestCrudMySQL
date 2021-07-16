package in.nareshit.raghu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class SpringBoot2RestCrudMySqlApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("EMPLOYEE SAVE TEST")
	@Order(1)
	public void testEmployeeSave() throws Exception {
		//a. Create Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.post("/api/employees/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"empName\" : \"SAM\", \"empSal\": 2000.0, \"empDept\" : \"DEV\" }");

		//b. Execute Request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate Response.
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		if(!response.getContentAsString().contains("Created")) {
			fail("SAVE IS NOT WORKING PROPERLY !!");
		}
	}


	@Test
	@DisplayName("EMPLOYEE ALL TEST")
	@Order(2)
	public void testEmployeeAll() throws Exception {
		//a. Create Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.get("/api/employees/all");

		//b. Execute Request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate Response.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

	}

	//get One successs
	@Test
	@DisplayName("TEST EMPLOYEE GET ONE ")
	@Order(3)
	public void testEmployeeGetOne() throws Exception {
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("/api/employees/one/{id}",1);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	//get one fail
	@Test
	@DisplayName("TEST EMPLOYEE GET ONE NOT FOUND")
	@Order(4)
	public void testEmployeeGetOneNotFound() throws Exception {
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("/api/employees/one/{id}",990);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
	}


	@Test
	@DisplayName("EMPLOYEE UPDATE TEST")
	@Order(5)
	public void testEmployeeUpdate() throws Exception {
		//a. Create Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.put("/api/employees/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"empId\":1, \"empName\" : \"SAM NEW\", \"empSal\": 6000.0, \"empDept\" : \"TQA\" }");

		//b. Execute Request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate Response.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("updated")) {
			fail("UPDATE IS NOT WORKING PROPERLY !!");
		}
	}
	

	@Test
	@DisplayName("EMPLOYEE DELETE TEST")
	@Order(6)
	@Disabled
	public void testEmployeeDelete() throws Exception {
		//a. Create Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.delete("/api/employees/remove/{id}",1);

		//b. Execute Request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate Response.
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("Deleted")) {
			fail(" Record not deleted!");
		}
	}


	@Test
	@DisplayName("EMPLOYEE DELETE NOT FOUND TEST")
	@Order(7)
	public void testEmployeeDeleteNotFound() throws Exception {
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.delete("/api/employees/remove/{id}",990);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
	}

}
