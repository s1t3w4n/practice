package ru.bellintegrator.practice.controller.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;
import ru.bellintegrator.practice.view.office.OfficeViewList;
import ru.bellintegrator.practice.view.office.OfficeViewSave;
import ru.bellintegrator.practice.view.office.OfficeViewUpdate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Контроллер офисов должен: ")
class OfficeControllerTest {

    private static final String DATA_ROOT = "$.data";
    private static final String DATA_NAME_ROOT = "$.data.name";
    private static final String DATA_ADDRESS_ROOT = "$.data.address";
    private static final String DATA_PHONE_ROOT = "$.data.phone";
    private static final String RESULT_ROOT = "$.data.result";
    private static final String ERROR_ROOT = "$.error";
    private static final String DATA_ID_ROOT = "$.data.id";
    private static final String DEFAULT_NAME = "Северный";
    private static final long DEFAULT_ID = 1L;
    private static final long UNDEFINED_ID = 10L;
    private static final String UUID = " UUID: ";
    private static final String TYPE = "application/json;charset=UTF-8";
    private static final String RESULT_SUCCESS = "success";
    private static final String NEW_VALUE = "NEW_VALUE_";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @DisplayName("возвращать офисы по фильтру")
    @SneakyThrows
    @Test
    void getOfficeByFilter() {
        this.mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OfficeViewFilter(DEFAULT_ID, null, null, true)))
                .accept(MediaType.parseMediaType(TYPE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_ROOT).isArray())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(new OfficeViewList(DEFAULT_ID, DEFAULT_NAME, true)))));
    }

    @DisplayName("возвращать 400 ошибку по фильтру без id организации")
    @SneakyThrows
    @Test
    void returnExceptionWithEmptyName() {
        this.mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OfficeViewFilter(null, null, null, true))))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("возвращать office с id в url")
    @SneakyThrows
    @Test
    void shouldReturnOfficeById() {
        this.mockMvc.perform(get("/api/office/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_NAME_ROOT).value(DEFAULT_NAME))
                .andExpect(jsonPath(DATA_ID_ROOT).value(DEFAULT_ID));
    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterGetForUndefinedOfficeId() {
        this.mockMvc.perform(get("/api/office/10"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("обновлять существующий офис")
    @SneakyThrows
    @Test
    void shouldUpdateOffice() {
        this.mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OfficeViewUpdate(DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
        this.mockMvc.perform(get("/api/office/" + DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_NAME_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_ADDRESS_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_PHONE_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_ID_ROOT).value(DEFAULT_ID));
    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterUpdateForUndefinedOfficeId() {
        this.mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OfficeViewUpdate(UNDEFINED_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("создавать новый офис")
    @SneakyThrows
    @Test
    void shouldCreateNewOffice() {
        this.mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OfficeViewSave(DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
    }

    @DisplayName("возвращать ошибку, если нет организации с таким id организации")
    @SneakyThrows
    @Test
    void shouldReturnErrorIfOrganizationIdIsInvalid() {
        this.mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new OfficeViewSave(UNDEFINED_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, Boolean.TRUE))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }
}