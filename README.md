# Quizkampen-Assignment

Visa Spelet 
  - Kör med 4 användare och 2-3 rundor var med 3 frågor i varje
  - Försöka välja olika kategorier varje runda
  - Berätta om slutskärm, resultat, olika knappar synliga mm
  - Klurigt med FX
  
Visa Server <<MAX>>
  - Visa hur den ser ut.
  - Visa att den väntar på två användare
  - Kopplar till samma Protocol
  - player1.start(); = Run() i User
  - player2.start(); = Run() i User

Visa User <<MAX>>
  - Construktorn
    - Här skickas olika info beroende på om du är spelare 1 eller 2
  - Run()
   -  Allt som skickas mellan Client och Server tas emot och berarbetas via Protcol här

Visa Protocol <<SARA>>
  - Här "allt" händer 
  - Text som skickas ifrån Clienten (Controlles) styr vad Protocol ska skicka tillbaka

Visa några Clienter <<SARA>>
  - Visa GameView
    - initialize
    - Actionlyssnare

Visa ScreenNavigator <<AXEL>>
  - Visa att det är denna klass vi använder när vi byter fönster

Visa Databasen (Serializerad) <<SIMON>>
  - Visa Databasen och att den är Serializerad,
  - Kategorierna är listor med Questions
    - Varje objekt innehåller: En fråga, ett rätt svar samt tre fel svar
  - Det finns en lista med listorna Kategorier
  
Informationens väg <<PATRIK>>
  - Visa steg för steg vad som händer när klienten och Servern pratar med varandra.

Waiting (countDown)<<PATRIK>>
  - Varför vi använder den
    - Första steget för att veta hur många rundor, hur många frågor samt vilken kategori
    - Andra steget: För att samla ihop all info som ska skrivas ut på GameOverView
  - hur den fungerar
  - Hade problem med denna innan vi hittade CountDownLanch

Bra 
  - Lätt att få hjälp av varandra när man kör fast
  - Breafing varje morgon
  - löpande kominikation under dagen (Discord, Github, Trello)
  
Dåligt
 - Svårt att sätta sig in i andra gruppmedlemmars tankesätt
 - Missförstånd i designe av programmet (vilket gjort att vi behövt sitta en del tillsammans för att få ihop de)
Lärt oss
  - Trodde man skulle hinna mer än vad man faktiskt gjorde, kände oss 80% klara väldigt tidigt men stötte på tidskrävande problem
  - Lägga mer tid innan man börjar koda på hur programmet ska se ut
  - exempel med updateGameWindow() (hänger sig med whileloop)
