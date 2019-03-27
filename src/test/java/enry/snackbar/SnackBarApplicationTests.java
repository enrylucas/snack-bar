package enry.snackbar;


import enry.snackbar.controller.IngredientController;
import enry.snackbar.repository.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@AutoConfigureWebTestClient
public class SnackBarApplicationTests {

        
        private MockMvc mvc;
        
        @Mock
        private IngredientRepository ingredientRepository;
        
        @InjectMocks
        private IngredientController ingredientController;
    
        @Before
        public void setUp() {
            mvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        }
        
	@Test
	public void contextLoads() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/ingredients");
            mvc.perform(request).andExpect(status().isOk());
	}

}
