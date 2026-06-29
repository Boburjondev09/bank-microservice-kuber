# Currency Service (Микросервис Валют и Обменных Курсов)

## Описание проекта
Данный микросервис является частью банковской системы (**Project Bank**) и предназначен для управления справочниками стран, международных валют, а также для отслеживания и обновления их взаимных обменных курсов в режиме реального времени.

Проект спроектирован с четким разделением ответственности (Layered Architecture):
* **Endpoint (Controller):** REST API для взаимодействия с клиентами или внешними сервисами.
* **Service:** Бизнес-логика (включая транзакционную логику Upsert для предотвращения дублирования данных).
* **Mapper (MapStruct):** Изолированное преобразование между сущностями базы данных и объектами передачи данных (DTO).
* **Repository (Spring Data JPA):** Слой доступа к базе данных PostgreSQL.

---

## Технологии и Инструменты

| Компонент       | Технология / Библиотека   | Статус    |
|-----------------|---------------------------|-----------|
| **Язык** | Java 21                   | Used      |
| **Фреймворк** | Spring Boot 3.x (Data JPA, Web) | Used      |
| **Сборка** | Maven                     | Used      |
| **Маппинг** | MapStruct                 | Used      |
| **Генерация кода** | Lombok                  | Used      |
| **База данных** | PostgreSQL                | Used      |
| **Профайлинг** | VisualVM                  | Used      |
| **IDE** | IntelliJ IDEA             | Used      |

---

## Структура пакетов проекта

```text
ru.otus.currencyservice
│
├── aggregate
│   ├── DTO         # Объекты передачи данных (CountryDto, CurrencyDto, ExchangeRateDto)
│   ├── entity      # Сущности СУБД (Country, Currency, ExchangeRate)
│   └── mapper      # Интерфейсы MapStruct (CountryMapper, ExchangeRateMapper)
│
├── endpoint        # REST-контроллеры (CountryEndpoint, ExchangeRateEndpoint)
│
├── repository      # Интерфейсы Spring Data JPA (CurrencyRepository, CountryRepository, ExchangeRateRepository)
│
└── service         # Реализация бизнес-логики (CountryService, ExchangeRateService)
    └── business    # Интерфейсы сервисного слоя
```
---
Контакты
Автор: Urunov Hamdamboy

Telegram: @urunovv

Компания / Продукт:  PRODUCT

Дата старта: 26.06.2026