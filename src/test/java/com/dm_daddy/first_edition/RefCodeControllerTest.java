package com.dm_daddy.first_edition;

import com.dm_daddy.first_edition.Controllers.ItemController;
import com.dm_daddy.first_edition.Model.Items;
import com.dm_daddy.first_edition.Service.ItemTestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
@ContextConfiguration(classes = {FirstEditionTestsConfiguration.class})
public class RefCodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ItemController itemController;

    @MockBean
    private ItemTestService itemTestService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void createItem() throws Exception {
        Items items = new Items();
        items.setName("Wand");
        items.setDescription("Test for me");


        String inputInJson = this.mapToJson(items);

        String URI = "/items/create";

        Mockito.when(itemTestService.createItem(Mockito.any(Items.class))).thenReturn(items);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI).content(inputInJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getAllItems() throws Exception {
        Items items1 = new Items();
        items1.setId(1L);
        items1.setName("Wand");
        items1.setDescription("wand test");

        Items items2 = new Items();
        items2.setId(2L);
        items2.setName("Rod");
        items2.setDescription("rod test");

        List<Items> itemsList = new ArrayList<>();
        itemsList.add(items1);
        itemsList.add(items2);

        Mockito.when(itemTestService.getAllItems()).thenReturn(itemsList);
        String URI = "/items/list";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(itemsList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
