package al.ikubinfo.hrmanagement;
import al.ikubinfo.hrmanagement.converters.RoleConverter;
import al.ikubinfo.hrmanagement.converters.UserConverter;
import al.ikubinfo.hrmanagement.dto.requestdtos.NewRequestDto;
import al.ikubinfo.hrmanagement.dto.requestdtos.RequestDto;
import al.ikubinfo.hrmanagement.dto.userdtos.LoginDto;
import al.ikubinfo.hrmanagement.dto.userdtos.NewUserDto;
import al.ikubinfo.hrmanagement.dto.userdtos.UserDto;
import al.ikubinfo.hrmanagement.repository.RoleRepository;
import al.ikubinfo.hrmanagement.services.DepartmentService;
import al.ikubinfo.hrmanagement.services.RoleService;
import al.ikubinfo.hrmanagement.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class controllerTest extends TestSupport {

    private static final String URL_LOGIN = "/login";
    private static final String URL_REQUEST = "/requests";
    private static final  String URL_USER = "/users";

    @Autowired
    private UserService userService;


    @Nested
    class AuthTests {
        @Test
        void validLogin() throws Exception {
            String token = userService.getTokenProvider().generateToken("admin@gmail.com", "password");
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post(URL_LOGIN)
                                    .header(AUTHORIZATION, BEARER + token)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(getLoginJson()))
                    .andExpect(status().isOk());
            assertNotNull(token);
        }

        private String getLoginJson() {
            LoginDto loginDto = new LoginDto("admin@gmail.com", "password");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(loginDto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    @Nested
    class ControllerTests {
        @Test
        void addNewRequestTest() throws Exception {
            String token = userService.getTokenProvider().generateToken("admin@gmail.com", "password");
            String requestJson = createJson(addRequest());
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post(URL_REQUEST)
                                    .header(AUTHORIZATION, BEARER + token)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestJson))
                    .andExpect(status().isOk());
        }


        @Test
        void addNewUserTest() throws Exception {
            String token = userService.getTokenProvider().generateToken("admin@gmail.com", "password");
            String requestJson = createJson(addUSer());
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post(URL_USER)
                                    .header(AUTHORIZATION, BEARER + token)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestJson))
                    .andExpect(status().isOk());
        }


        private NewRequestDto addRequest() {
            NewRequestDto requestDto = new NewRequestDto();
            requestDto.setReason("TEST");
            requestDto.setFromDate(LocalDate.now());
            requestDto.setToDate(LocalDate.now().plusDays(5));
            return requestDto;
        }

        private NewUserDto addUSer() {
            NewUserDto userDto = new NewUserDto();
            userDto.setFirstName("TEST");
            userDto.setLastName("TEST");
            userDto.setEmail("TEST");
            userDto.setPassword("TEST");
            userDto.setBirthday(LocalDate.now());
            userDto.setGender("TEST");
            userDto.setRole(1L);
            userDto.setDepartment(1L);
            return userDto;
        }
    }
}


