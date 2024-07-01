# SpringProject

## Jak uruchomić:
- Musisz posiadać Intellij i Docker
- W IntelliJ jest gotowy profil kompozycyjny **"Run Backend"**
- Po uruchomieniu wejdź na **localhost:5000**
- Dodany na start jest użytkownik
  - login: admin
  - hasło: admin 

## Funkcjonalności:
- Przeglądanie przedmiotów przez każdego
- Jeżeli jest USER
  - Może dodawać przedmioty do koszyka, po dodaniu home się aktualizuje i pokazuje ilość w koszyku
  - Może usuwać przedmioty z koszyka, jeśli takowe są
- Jeżeli jest ADMIN
  - To samo co USER
  - Ma dostęp do Panelu Administratora
    - Można dodawać użytkowników
    - Można usuwać użytkowników, zablokowana jest możliwość usunięcia samego siebie
    - Można dodawać przedmioty
    - Można usuwać przedmioty
- Każda strona ma przejścia wstecz czy w alternatywe (chodzi o rotacje między login i register)
- Home jest reaktywny w zależności od roli
  - Jeżeli zalogowany to widzi tylko wyloguj z opcji logowania
  - Jeżeli nie zalogowany to widzi tylko zaloguj z opcji logowania
  - Jeżeli nie zalogowany nie widzi przycisków do dodawania do koszyka jak i przycisku do przejścia do niego
- Ochrona przed atakami CSRF 
