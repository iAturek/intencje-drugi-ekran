# intencje-drugi-ekran
## Commit 5:
* ActivityResultLauncher musi zostać zarejestrowany przed uruchomieniem aktywności. Dlatego deklarujemy go jako pole klasy i rejestrujemy podczas tworzenia MainActivity.
* Po naciśnięciu przycisku Wstecz drugi ekran zostanie zamknięty, a użytkownik wróci do pierwszego ekranu. Wynik działania będzie RESULT_CANCELED, dlatego nie zostanie wyświetlony Snackbar z numerem zgłoszenia.

## Zadanie 2:
Intencja jawna

Wskazuje konkretną aktywność, którą chcemy uruchomić. W mojej aplikacji użyłem jej w MainActivity, aby przejść do SummaryActivity.

Intencja niejawna

Nie wskazuje konkretnej aktywności, tylko określa, jaką czynność chcemy wykonać. W mojej aplikacji użyłem jej do udostępnienia podsumowania zamówienia. 
Android sam wybiera aplikację, która może wykonać tę czynność.
