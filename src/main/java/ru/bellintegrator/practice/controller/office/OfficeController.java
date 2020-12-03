package ru.bellintegrator.practice.controller.office;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.office.OfficeViewListIn;
import ru.bellintegrator.practice.view.office.OfficeViewListOut;

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
     * @param filterIn фильтр по котору происходит поиск
     * @return список офисов в определённом формате
     */
    @PostMapping(value = "api/office/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfficeViewListOut> getOrganizationBy(@RequestBody @Valid OfficeViewListIn filterIn) {
        return officeService.getAllOfficeBy(filterIn);
    }
}
