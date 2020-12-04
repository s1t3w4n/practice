package ru.bellintegrator.practice.service.user;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.doc.DocDao;
import ru.bellintegrator.practice.dao.identity.IdentityDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Identity;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.user.UserView;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;
import ru.bellintegrator.practice.view.user.UserViewSave;
import ru.bellintegrator.practice.view.user.UserViewUpdate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final DocDao docDao;
    private final UserDao userDao;
    private final IdentityDao identityDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserViewList> getAllUserBy(UserViewFilter filter) {
        return userDao.findAllUserBy(filter).stream()
                .map(mapperFactory.getMapperFacade(User.class, UserViewList.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        User persisted = userDao.findUserById(id);
        if (Objects.isNull(persisted)) {
            throw new RuntimeException("Нет пользователя с таким id");
        }
        UserView user = mapperFactory.getMapperFacade().map(persisted, UserView.class);
        user.setDocDate(persisted.getIdentity().getDocDate());
        user.setDocNumber(persisted.getIdentity().getDocNumber());
        user.setDocName(persisted.getIdentity().getDoc().getName());
        user.setCitizenshipCode(persisted.getCitizenship().getCode());
        user.setCitizenshipName(persisted.getCitizenship().getName());
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateUser(UserViewUpdate user) {
        if (Objects.nonNull(user.getId()) && user.getId() > 0) {
            User persisted = userDao.findUserById(user.getId());
            if (Objects.nonNull(persisted)) {
                mapperFactory.getMapperFacade().map(user, persisted);
                persisted.setOffice(checkIfExistsOffice(officeDao.findOfficeById(user.getOfficeId())));
                persisted.getIdentity().setDocNumber(user.getDocNumber());
                persisted.getIdentity().setDocDate(user.getDocDate());
                persisted.getIdentity().setDoc(checkIfExistsDoc(docDao.findDocByName(user.getDocName())));
                persisted.setCitizenship(checkIfExistsCountry(countryDao.findCountryByCode(user.getCitizenshipCode())));
                if (userDao.updateUser(persisted)) {
                    return new ResultSuccessView();
                } else {
                    throw new RuntimeException("Обновление офиса не выполнено");
                }
            } else {
                throw new RuntimeException("Нет пользователя с таким id");
            }
        } else {
            throw new RuntimeException("id обязательный параметр");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView saveUser(UserViewSave user) {
        Office office = checkIfExistsOffice(officeDao.findOfficeById(user.getOfficeId()));
        Country country = checkIfExistsCountry(countryDao.findCountryByCode(user.getCitizenshipCode()));
        Doc doc = checkIfExistsDoc(docDao.findDocByNameAndCode(user.getDocName(), user.getDocCode()));

        User newUser = mapperFactory.getMapperFacade().map(user, User.class);
        newUser.setOffice(office);
        newUser.setCitizenship(country);
        userDao.saveUser(newUser);

        Identity identity = new Identity();
        identity.setDoc(doc);
        identity.setDocNumber(user.getDocNumber());
        identity.setDocDate(user.getDocDate());
        identity.setUser(newUser);
        identityDao.saveIdentity(identity);
        return new ResultSuccessView();
    }

    private Office checkIfExistsOffice(Office office) {
        if (Objects.isNull(office)) {
            throw new RuntimeException("Нет такого офиса");
        } else {
            return office;
        }
    }

    private Doc checkIfExistsDoc(Doc doc) {
        if (Objects.nonNull(doc)) {
            return doc;
        } else {
            throw new RuntimeException("Нет такого документа");
        }
    }

    private Country checkIfExistsCountry(Country country) {
        if (Objects.nonNull(country)) {
            return country;
        } else {
            throw new RuntimeException("Нет такой страны в справочнике стран");
        }
    }

}
