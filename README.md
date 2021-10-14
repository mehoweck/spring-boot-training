# spring-boot-training

##Wprowadzenie
- Co to jest Spring Boot?
- Spring Initializer
- Struktura wygenerowanego projektu
- Uruchamianie aplikacji
##Zarządzanie zależnościami
- Co to jest bean?
- @Scope i cykl życia
- Wstrzykiwanie zależności
- Konfiguracja za pomocą kodu
- Zaawansowane mechanizmy wyboru zależności
##Konfiguracja
- Plik application.yml i jego najważniejsze elementy
- Profile aplikacji i sposoby ich wykorzystania
- Wstrzykiwanie konfiguracji poprzez @Value
- Wstrzykiwanie konfiguracji poprzez @ConfigurationProperties
- Warunkowe tworzenie beanów na podstawie konfiguracji
##Wystawienie REST API przy użyciu Spring Web
- Podstawy wystawiania API REST za pomocą @RestController
- Obsługa operacji GET, POST, PUT, DELETE
- Przekazywanie parametrów do zapytań - query, path, body i nagłówki
- Zwracanie danych z serwera (body, statys, nagłówki)
- Upload i download plików binarnych
- Własne typy danych w API
- Walidacja poprawności zapytań
- Generowanie specyfikacji OpenAPI 3.0
- Przygotowanie testów wystawionego API
##Konsumowanie REST API
- Wykonywanie zapytań za pomocą RestTemplate
- Wykonywanie zapytań za pomocą Feign
- Generowanie klienta ze specyfikacji OpenAPI 3.0
- Wprowadzenie Cache
##Mechanizm wywoływania operacji cyklicznych
- @Scheduled i jego konfiguracja
- Wykonywanie operacji z opóźnieniem
- Wykonywanie operacji cyklicznie z odstępem czasowym
- Wykorzystanie wyrażeń cron
##Dostęp do bazy danych z wykorzystaniem Spring Data
- Tworzenie struktury bazy danych za pomocą Liquibase
- Wprowadzanie zmian w istniejącej strukturze bazy
- Tworzenie @Repository
- Definicja mapowań encji bazodanowych
- Tworzenie własnych zapytań
- Obsługa transakcji
##Bezpieczeństwo z użyciem Spring Security
- Wprowadzenie
- Uwierzytelnianie za pomocą HTTP Basic oraz OAuth2
- Zabezpieczenie na podstawie ról
- Zabezpieczenie na podstawie danych
- Odpytywanie zabezpieczonych serwisów
##Monitoring aplikacji z użyciem Spring Boot Actuator
- Healthcheck czyli informacja o zdrowiu aplikacji
- Wystawienie metryk o naszej aplikacji dla Prometheusa
- Definiowanie własnych metryk
##Docker
- Wprowadzenie i instalacja
- Podstawowe operacje
- Operacje wewnątrz kontenera
- Tworzenie własnego obrazu za pomocą Dockerfile
- Przekazywanie parametrów do obrazu
- Przechowywanie danych poza kontenerem
- Przygotowanie obrazu dla aplikacji Spring Boot
##Docker Compose
- Wprowadzenie
- Definicja kontenerów
- Przekazywanie parametrów do kontenerów
- Tworzenie sieci
- Przykład stworzenia lokalnego środowiska developerskiego
##Kubernetes
- Wprowadzenie
- Podstawowe pojęcia
- Dobre praktyki dla developera
