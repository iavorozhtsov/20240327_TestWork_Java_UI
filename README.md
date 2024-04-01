# 20240327_TestWork

Для удобства идентифицируем требования к функционалу:
R-00 Возможность зарегистрировать нового пользователя
R-01 Возможность войти из под учетной записи зарегистрированного пользователя
R-02 Поле Email является обязательным при регистрациии
R-03 Поле Password является обязательным при регистрациии

Тестовые сценарии приведены в папке TestCases
Найденные дефекты приведены в папке BugReports

Автоматизированные тесты написаны на языке Java.
Сборщик проекта Maven.
Использован тестовый фреймворк JUnit-jupiter версии 5.10.2.
Для работы с браузером Google Chrome использовалась библиотека Selenium версии 4.18.1 
Применён паттерн проектирования Page Object.

Предусловие для одной из проверок секции Login:
1. В системе должен существовать пользователь test@test.com с паролем "$tr0ng_@nd_L0ng1" и именем "John Doe"