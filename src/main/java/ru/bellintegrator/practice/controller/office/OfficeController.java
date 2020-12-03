package ru.bellintegrator.practice.controller.office;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;
import ru.bellintegrator.practice.view.office.OfficeViewList;
import ru.bellintegrator.practice.view.office.OfficeViewSave;
import ru.bellintegrator.practice.view.office.OfficeViewUpdate;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер офиса
 */
@RestController
@AllArgsConstructor
public class OfficeController {
    private final OfficeService officeService;

    /**
     * Получить все офиса в определённом формате соответствующие фильтру
     * @param filter фильтр по котору происходит поиск
     * @return список офисов в определённом формате
     */
    @PostMapping(value = "api/office/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfficeViewList> getOfficeBy(@RequestBody @Valid OfficeViewFilter filter) {
        return officeService.getAllOfficeBy(filter);
    }

    /**
     * Получить офис по id
     * @param id уникальный идентификатор офиса
     * @return возвращаемый view организации
     */
    @GetMapping(value = "api/office/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OfficeView getOffice(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Обновить офис
     * @param office данные для обновления
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/office/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView updateOffice(@RequestBody @Valid OfficeViewUpdate office) {
        return officeService.updateOffice(office);
    }

    /**
     * Сохранить офис
     * @param office данные для сохранения
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/office/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView saveOffice(@RequestBody @Valid OfficeViewSave office) {
        return officeService.saveOffice(office);
    }
}
