package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.service.Stock;
import ru.geekbrains.service.StockService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = {BrandControllerTest.TestConfig.class})
public class BrandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BrandRepository brandRepository;

//    @MockBean
//    private StockService stockService;

    @TestConfiguration
    public static class TestConfig {

        @Primary
        @Bean
        public StockService stockService() {
            return id -> new Stock(id, 123);
        }

    }

    @BeforeEach
    public void init() {
        brandRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandCreation() throws Exception {
        mvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New brand")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        Optional<Brand> opt = brandRepository.findOne(Example.of(new Brand("New brand")));

        assertTrue(opt.isPresent());
        assertEquals("New brand", opt.get().getName());
    }
}
