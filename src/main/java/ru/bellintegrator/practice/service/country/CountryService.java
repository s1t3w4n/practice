package ru.bellintegrator.practice.service.country;

import ru.bellintegrator.practice.view.country.CountryView;

import java.util.List;

/**
 * Сервис для работы со странами
 */
public interface CountryService {
    /**
     * Получить список стран
     *
     * @return список стран
     */
    List<CountryView> getAllCountries();
}
