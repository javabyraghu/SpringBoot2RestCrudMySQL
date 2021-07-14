package in.nareshit.raghu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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


	@Test
	@DisplayName("EMPLOYEE DELETE TEST")
	@Order(3)
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

}
