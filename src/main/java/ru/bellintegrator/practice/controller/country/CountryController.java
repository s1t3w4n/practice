package ru.bellintegrator.practice.controller.country;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.country.CountryService;
import ru.bellintegrator.practice.view.country.CountryView;

import java.util.List;

/**
 * Контроллер страны
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    /**
     * Получение списка стран
     * @return список стран
     */
    @PostMapping
    public List<CountryView> getAllCountries(){
        return countryService.getAllCountries();
    }
}
