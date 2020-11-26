package ru.bellintegrator.practice.dao.country;

import ru.bellintegrator.practice.model.Country;

import java.util.List;
/**
 * DAO для работы со страны
 */
public interface CountryDao {
    /**
     * Получить все страны
     *
     * @return все страны
     */
    List<Country> getAll();
}
