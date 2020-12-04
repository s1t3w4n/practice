package ru.bellintegrator.practice.dao.country;

import ru.bellintegrator.practice.model.Country;

import java.util.List;
/**
 * DAO для работы со странами
 */
public interface CountryDao {
    /**
     * Получить все страны
     *
     * @return все страны
     */
    List<Country> getAll();

    /**
     * Получение страны по коду
     * @param code код страны
     * @return страна
     */
    Country findCountryByCode(int code);
}
