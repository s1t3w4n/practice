INSERT INTO Doc (code,version, name)
VALUES (03, 0, 'Свидетельство о рождении'),
       (07, 0, 'Военный билет'),
       (08, 0, 'Временное удостоверение, выданное взамен военного билета'),
       (10, 0, 'Паспорт иностранного гражданина'),
       (11, 0, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу'),
       (12, 0, 'Вид на жительство в Российской Федерации'),
       (13, 0, 'Удостоверение беженца'),
       (15, 0, 'Разрешение на временное проживание в РФ'),
       (18, 0, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации'),
       (21, 0, 'Паспорт гражданина Российской Федерации'),
       (23, 0, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства'),
       (24, 0, 'Удостоверение личности военнослужащего Российской Федерации'),
       (91, 0, 'Иные документы');

INSERT INTO Country (code,version, name)
VALUES (643, 0, 'Российская Федерация'),
       (826, 0, 'Соединенное Королевство Великобритании и Северной Ирландии'),
       (156, 0, 'Китайская Народная Республика'),
       (840, 0, 'Соединенные Штаты Америки'),
       (276, 0, 'Федеративная Республика Германия'),
       (250, 0, 'Французская Республика'),
       (724, 0, 'Королевство Испания'),
       (356, 0, 'Республика Индия');

INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone)
VALUES (0, 'ООО "Майкрософт Рус"', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "МАЙКРОСОФТ РУС"', '7743528989', '773101001',  '121614, город Москва, Крылатская улица, 17-1', '74959678585'),
       (0, 'ООО "Эппл Рус"', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "ЭППЛ РУС"', '7707767220', '770401001',  '125009, город Москва, переулок Романов, дом 4 строение 2, эт 6 пом II ком 54', '74959612426'),
       (0, 'ООО "Техкомпания Хуавэй "', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "ТЕХКОМПАНИЯ ХУАВЭЙ"', '7714186804', '773101001',  '121614, город Москва, Крылатская улица, 17-2', '7495340686');

INSERT INTO Office (version, org_id, name, address, phone)
VALUES (0, 1, 'Северный', 'Северный адрес', '74951111111'),
       (0, 1, 'Южный', 'Южный адрес', '74952222222'),
       (0, 2, 'Западный', 'Западный адрес', '74953333333'),
       (0, 2, 'Восточный', 'Восточный адрес', '74954444444'),
       (0, 3, 'Северо-Западный', 'Северо-Западный адрес', '74955555555'),
       (0, 3, 'Юго-Восточный', 'Юго-Восточный адрес', '74956666666');

INSERT INTO User (version, office_id, first_name, second_name, middle_name, position, phone, citizenship_code)
VALUES (0, 1, 'Иванов', 'Иван', 'Иванович', 'Директор', '777', 643),
       (0, 3, 'Петров', 'Петр', 'Петрович', 'Программист', '256', 840),
       (0, 5, 'Мартынов', 'Мартын', 'Мартынович', 'Менеджер', '111', 276);

INSERT INTO Identity (version, doc_code, doc_number, doc_date)
VALUES (0, 03, '123456789', '2010-10-10'),
       (0, 07, '234567891', '2011-11-11'),
       (0, 08, '345678912', '2012-12-12'),
       (0, 10, '456789123', '2013-01-13');

INSERT INTO User_Identity (user_id, identity_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4);