# Planer kulturalny

Projekt aplikacji w wersji webowej i konsolowej służącej do przeglądania i zarządzania wydarzeniami.
Realizacja projektu odbyła się w ramach kursu dziennego na Junior Java Developera obejmującego 320h zajęć merytorycznych.
Projekt opiera swoje działanie na API **Planer**, które dostarcza dane dotyczące nadchodzących wydarzeń kulturalnych. 

### Aplikacja webowa

Dane dotyczące wydarzeń przechowywane są w bazie na silniku MySQL przy wykorzystaniu konteneryzacji(Docker).
Wybranym ORM do projektu jest Hibernate, aplikacja deployowana jest na serwerze Wildfly.
Aplikacja została stworzona w technologii Java EE.

#### Funkcjonalności aplikacji webowej

* Przeglądanie wydarzeń
* Wyszukiwanie wydarzeń z wykorzystaniem 
* Zarządzanie ulubionymi wydarzeniami
* Zajawka najbliższego wydarzenia ulubionego
* Rezerwacja biletów
* Moduł statystyk dla administratorów
* Zasilanie bazy danych awaryjnie upload'ująć pliki JSON
* Zasilanie bazy danych z API
* Dodawanie, usuwanie, modyfikacja wydarzeń
* Panel nadawanie uprawnień użytkownikom 

### Aplikacja konsolowa

Aplikacja w wersji konsolowej pozwala na przeglądanie, wyszukiwanie, dodawanie, usuwanie i modyfikowanie wydarzeń. 
Dane dotyczące wydarzeń ładowane są do aplikacji z plików w formacie JSON.
Możliwe jest także zarządzanie ulubionymi wydarzeniami. 
Wyświetlanie jest stronnicowane, zaimplementowane zostało również sortowanie.

## Wykorzystane technologie

* Java EE 8
* Java 13
* Wildfly 
* Maven 
* Jenkins
* Hibernate
* Docker
* MySql
* OAuth 2.0 Google
* Freemarker
* Javascript
* JUnit
* HTML5
* CSS
* Bootstrap4

## Autorzy

* **Bartłomiej Trawiński** - [github](https://github.com/bartraw23) - [linkedin](https://www.linkedin.com/in/bart%C5%82omiej-trawinski/)
* **Jakub Cichacki** - [github](https://github.com/jakubcichacki)
