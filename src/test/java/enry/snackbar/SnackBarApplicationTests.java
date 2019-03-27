package enry.snackbar;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import enry.snackbar.controller.FoodController;
import enry.snackbar.controller.IngredientController;
import enry.snackbar.controller.IngredientWrapper;
import enry.snackbar.model.Ingredient;
import enry.snackbar.repository.FoodRepository;
import enry.snackbar.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@AutoConfigureWebTestClient
public class SnackBarApplicationTests {

        
        private MockMvc ingredientMvc;
        private MockMvc foodMvc;
        
        @Mock
        private IngredientRepository ingredientRepository;
        
        @Mock
        private FoodRepository foodRepository;
        
        @InjectMocks
        private IngredientController ingredientController;
        
        @InjectMocks
        private FoodController foodController;
    
        @Before
        public void setUp() {
            ingredientMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
            foodMvc = MockMvcBuilders.standaloneSetup(foodController).build();
        }
        
	@Test
	public void getIngredientsTest() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/ingredients");
            ingredientMvc.perform(request).andExpect(status().isOk());
	}
        
        @Test
	public void getFoodsTest() throws Exception {
            RequestBuilder request = MockMvcRequestBuilders.get("/foods");
            foodMvc.perform(request).andExpect(status().isOk());
	}
        
        @Test
	public void getPriceTest() throws Exception {
            IngredientWrapper wrapper = new IngredientWrapper();
            List<Integer> ings = new ArrayList<>();//X-Bacon
            ings.add(2);
            ings.add(3);
            ings.add(5);
            wrapper.setIngredientsId(ings);
            List<Integer> qts = new ArrayList<>();
            qts.add(1);
            qts.add(1);
            qts.add(1);
            wrapper.setIngredientsQty(qts);
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(wrapper);
            RequestBuilder request = MockMvcRequestBuilders.post("/ingredients/build").contentType(MediaType.APPLICATION_JSON).content(json);
            ingredientMvc.perform(request).andExpect(content().string("6.50"));
	}
        
        @Test
	public void getPriceTestWithInflation() throws Exception {
            IngredientWrapper wrapper = new IngredientWrapper();
            List<Integer> ings = new ArrayList<>();//X-Egg Bacon
            ings.add(2);
            ings.add(3);
            ings.add(4);
            ings.add(5);
            wrapper.setIngredientsId(ings);
            List<Integer> qts = new ArrayList<>();
            qts.add(1);
            qts.add(1);
            qts.add(1);
            qts.add(1);
            wrapper.setIngredientsQty(qts);
            
            Ingredient newBacon = new Ingredient();
            newBacon.setId(2);
            newBacon.setName("Bacon");
            newBacon.setPrice(4.0f);
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(wrapper);
            String newPrice = ow.writeValueAsString(newBacon);
            
            
            RequestBuilder request1 = MockMvcRequestBuilders.put("/ingredients/2").contentType(MediaType.APPLICATION_JSON_UTF8).content(newPrice);
            ingredientMvc.perform(request1).andExpect(status().isOk()); //isso realmente altera o valor no banco? (eu gostaria que sim)
            
            
            RequestBuilder request2 = MockMvcRequestBuilders.post("/ingredients/build").contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
            ingredientMvc.perform(request2).andExpect(content().string("9.30")); //o findById dentro do post não funciona quando é o teste rodando
	}

}
