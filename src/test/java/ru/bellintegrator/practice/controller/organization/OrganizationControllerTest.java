package ru.bellintegrator.practice.controller.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;
import ru.bellintegrator.practice.view.organization.OrganizationViewList;
import ru.bellintegrator.practice.view.organization.OrganizationViewSave;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Контроллер организаций должен: ")
class OrganizationControllerTest {

    public static final String DATA_ROOT = "$.data";
    public static final String DATA_NAME_ROOT = "$.data.name";
    public static final String RESULT_ROOT = "$.data.result";
    public static final String ERROR_ROOT = "$.error";
    public static final String DATA_ID_ROOT = "$.data.id";
    public static final String DEFAULT_NAME = "ООО \"Майкрософт Рус\"";
    public static final long DEFAULT_ID = 1L;
    public static final long UNDEFINED_ID = 10L;
    public static final String UUID = " UUID: ";
    public static final String TYPE = "application/json;charset=UTF-8";
    public static final String RESULT_SUCCESS = "success";
    public static final String NEW_VALUE = "NEW_VALUE_";
    public static final String NEW_VALUE_9_SYMBOLS = "NEW_VALUE";
    public static final String NEW_VALUE_KPP = "NEW_KPP_V";
    public static final String NEW_VALUE_INN = "NEW_INN_VA";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @DisplayName("возвращать организации по фильтру из базы данных")
    @SneakyThrows
    @Test
    void getOrganizationByName() {
        this.mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OrganizationViewFilter(DEFAULT_NAME, null, null)))
                .accept(MediaType.parseMediaType(TYPE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_ROOT).isArray())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(new OrganizationViewList(DEFAULT_ID, DEFAULT_NAME, true)))));
    }

    @DisplayName("возвращать 400 ошибку по фильтру без имени")
    @SneakyThrows
    @Test
    void returnExceptionWithEmptyName() {
        this.mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OrganizationViewFilter(null, null, true))))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("возвращать организацию с id в url")
    @SneakyThrows
    @Test
    void shouldReturnOrganizationById() {
        this.mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_NAME_ROOT).value(DEFAULT_NAME))
                .andExpect(jsonPath(DATA_ID_ROOT).value(DEFAULT_ID));
    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterGetForUndefinedOrganizationID() {
        this.mockMvc.perform(get("/api/organization/10"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("обновлять существующую организацию")
    @SneakyThrows
    @Test
    void shouldUpdateOrganization() {
        this.mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OrganizationViewUpdate(DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE_9_SYMBOLS, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterUpdateForUndefinedOrganizationID() {
        this.mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OrganizationViewUpdate(UNDEFINED_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE_9_SYMBOLS, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("создавать новую организацию")
    @SneakyThrows
    @Test
    void shouldCreateNewOrganization() {
        this.mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OrganizationViewSave(NEW_VALUE, NEW_VALUE, NEW_VALUE_INN, NEW_VALUE_KPP, NEW_VALUE, NEW_VALUE, Boolean.TRUE)
                )))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
    }
}