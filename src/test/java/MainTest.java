import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hoshimusubi.seunga.model.UserVO;
import com.hoshimusubi.seunga.security.CustomPrincipal;
import com.hoshimusubi.seunga.security.CustomUserDetails;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class MainTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        UserVO mockUser = new UserVO();
        mockUser.setId(122);
        mockUser.setNickname("Tester");

        CustomUserDetails userDetails = new CustomUserDetails(mockUser);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testCreatePost() throws Exception {

        MockMultipartFile imageFile =
                new MockMultipartFile("imageFile", "test.jpg", "image/jpeg", "Dummy Image Content".getBytes());

        mockMvc.perform(multipart("/post_create")
                .file(imageFile)
                .param("title", "테스트 제목")
                .param("content", "테스트 내용")
                .param("zodiacId", "5"))
                .andExpect(status().is3xxRedirection());
    }
}
