package server;


import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database implements Serializable {

    List<User> userList = new ArrayList<>();
    List<ArrayList<Questions>> categoryList = new ArrayList<>();
    ArrayList<Questions> DJUR_OCH_NATUR = new ArrayList<>();
    ArrayList<Questions> GEOGRAFI = new ArrayList<>();
    ArrayList<Questions> MUSIK = new ArrayList<>();
    ArrayList<Questions> HISTORIA = new ArrayList<>();
    ArrayList<Questions> KROPP_OCH_KNOPP = new ArrayList<>();
    ArrayList<Questions> MAT_OCH_DRYCK = new ArrayList<>();
    ArrayList<Questions> KÄNDISAR = new ArrayList<>();
    ArrayList<Questions> FILM_OCH_SERIER = new ArrayList<>();
    ArrayList<Questions> KONST_OCH_KULTUR = new ArrayList<>();
    ArrayList<Questions> SPRÅK_OCH_TEXT = new ArrayList<>();

    Questions natur1 = new Questions("Vilket är södermanlands landskapsblomma?", "Vit näckros", "Liljekonvalj", "Gullviva", "Blåklocka", "DJUR OCH NATUR");
    Questions natur2 = new Questions("Vad kallas virket av tall?","Furu", "Granvirke", "Timmer", "Pärlspont", "NATUR");
    Questions natur3 = new Questions("Vad äter delfiner?","Fisk, bläckfisk\noch kräftdjur", "Hajar och rockor", "Musslor och havsväxter", "Avföring och\nnedbrytna djur", "NATUR");
    Questions natur4 = new Questions("Vad är Östersjön?","Ett hav", "En sjö", "En flod", "En bäck", "NATUR");
    Questions natur5 = new Questions("Vad kallas ungen om ett lejon skulle para sig med en tiger?","Liger", "Tigon", "Lejoniger", "Tejon", "NATUR");

    Questions geografi1 = new Questions("Hur många mil är det mellan kiruna och stockholm?", "120 mil", "50 mil", "100 mil", "220 mil", "GEOGRAFI");
    Questions geografi2 = new Questions("Vilken är världens största stad?","Tokyo", "New York", "Mexico City", "New Dehli", "GEOGRAFI");
    Questions geografi3 = new Questions("Vad kallas Umeå även för?","Björkarnas stad", "Gurkornas stad", "Den gyllene staden", "Korparnas stad", "GEOGRAFI");
    Questions geografi4 = new Questions("Vilken flod rinner genom Paris?","Seine", "Loire", "Rhône", "Thames", "GEOGRAFI");
    Questions geografi5 = new Questions("Japan består av tusentals öar, men vilken är störst?","Honshu", "Hokkaido", "Shikoku", "Kyushu", "GEOGRAFI");

    Questions musik1 = new Questions("Vilken låt slog Beyoncé igenom med som soloartist?","Crazy in love", "Baby boy", "Halo", "Irreplacable", "MUSIK");
    Questions musik2 = new Questions("Vad heter sångerskan som sjunger i ett internationellt svenskt hårdrocksband?","Elize Ryd", "Miriam Bryant", "Laleh", "Sabina Ddumba", "MUSIK");
    Questions musik3 = new Questions("Vems låt är 'Vi mot världen'?","Veronica Maggio", "Eva Dahlgren", "Carola", "Lillbabs", "MUSIK");
    Questions musik4 = new Questions("ABBA var en svensk popgrupp. Varifrån kommer namnet?","Från medlemmarnas\ninitialer", "Från bolaget\nAbba Seafood", "Man vet inte vartifrån\nnamnet kommer", "Smeknamn som ung", "MUSIK");
    Questions musik5 = new Questions("Vem har komponerat 'Solveigs sång'?","Edvard Grieg", "Edvard Munch", "Henrik Ibsen", "Tor Ahlberg", "MUSIK");

    Questions historia1 = new Questions("Från vilket land kom psykoanalysens fader Sigmund Freud?","Österrike", "Tyskland", "Schweiz", "Holland", "HISTORIA");
    Questions historia2 = new Questions("När grundades Stockholm?","1252", "1522", "1432", "1342", "HISTORIA");
    Questions historia3 = new Questions("Vem grundade IKEA?","Ingvar kamprad", "Ingvar Kamp", "Alvar Kamprad", "Göte Kamp", "HISTORIA");
    Questions historia4 = new Questions("1979 invaderade Sovjetunionen Afghanistan. Vilket år tog kriget slut?","1989", "1984", "1982", "1986", "HISTORIA");
    Questions historia5 = new Questions("Vem var Ansgar?","En kristen munk", "En popartist", "En kung", "En smed", "HISTORIA");

    Questions kropp1 = new Questions("Hur många tänder har en människa naturligt? (Ej visdomständer inräknade)","28", "12", "24", "30", "KROPP OCH KNOPP");
    Questions kropp2 = new Questions("Vad står KBT för?","Kognitiv beteendeterapi", "Känslo-, beteende\noch tanketerapi", "Kognitiv beteendeteori", "Känslomässig beteende\nterapi", "KROPP OCH KNOPP");
    Questions kropp3 = new Questions("Vad är mjäll?","Hudsvamp", "Torr hud", "Fett", "Eksem", "KROPP OCH KNOPP");
    Questions kropp4 = new Questions("Vad är en geriatriker specialiserad på?","Äldres sjukdomar", "Invärtes sjukdomar", "Fötternas sjukdomar", "Hjärtats Sjukdomar", "KROPP OCH KNOPP");
    Questions kropp5 = new Questions("Vad heter skelettets längsta ben?","Lårbenen", "Ryggraden", "Revben", "Nyckelben", "KROPP OCH KNOPP");

    Questions mat1 = new Questions("Vilket är världsrekordet för flest ätna sushibitar på 6 minuter?","141", "41", "121", "161", "MAT OCH DRYCK");
    Questions mat2 = new Questions("Vad är en sous vide?","Vakuumpacknings-\nmaskin", "Tryckkokare", "Fritös", "Vedugn", "MAT OCH DRYCK");
    Questions mat3 = new Questions("Vad heter den klassiska franska köttgrytan?","Boeuf bourguignon", "Coq au vin", "Moules frites", "Bouillabaisse", "MAT OCH DRYCK");
    Questions mat4 = new Questions("Vad är sveriges motsvarighet till den japanska wasabin?","Peparrot", "Senap", "Vitlök", "Ingefära", "MAT OCH DRYCK");
    Questions mat5 = new Questions("Vilken av följande pasta sorter är tunnast?","Capellini", "Spagetti", "Tagliatelle", "Rigatoni", "MAT OCH DRYCK");

    Questions kändis1 = new Questions("Vilket av följande namn är INTE en av Kim Kardashian och Kanye Wests barn?","True", "Chicago", "North", "Saint", "KÄNDISAR");
    Questions kändis2 = new Questions("Vad heter Donald Trumps dotter?","Ivanka", "Ivana", "Melania", "Marla", "KÄNDISAR");
    Questions kändis3 = new Questions("Vad heter Zara Larssons syster?","Hanna", "Maja", "Anna", "Sara", "KÄNDISAR");
    Questions kändis4 = new Questions("Hur gammal är Brad Pitt","56", "52", "48", "50", "KÄNDISAR");
    Questions kändis5 = new Questions("Hur lång är Zlatan Ibrahimovic?","1,95", "1,90", "1,92", "1,98", "KÄNDISAR");

    Questions film1 = new Questions("Var utspelas filmen Call me by your name?","Italien", "Spanien", "USA", "Sverige", "FILM OCH SERIER");
    Questions film2 = new Questions("Vad heter serien som handlar om en arkitekt som rymmer ett fängelse?","Prison Break", "Dexter", "Breaking Bad", "Fargo", "FILM OCH SERIER");
    Questions film3 = new Questions("Vilken känd rappare från USA var med i filmen 'Friday'?","Ice Cube", "Snoop Dogg", "Wiz Khalifa", "Eminem", "FILM OCH SERIER");
    Questions film4 = new Questions("Vad heter serien då en kvinna transporteras tillbaks till 1700-talet?","Outlander", "Made in Abyss", "Game of Thrones", "Vikings", "FILM OCH SERIER");
    Questions film5 = new Questions("I serien 'Dexter' så är huvudpersonen intresserad av en viss vätska, vilken?","Blod", "Vatten", "Mjölk", "Urin", "FILM OCH SERIER");

    Questions konst1 = new Questions("Vilken färg får du om du blandar röd och blå?","Lila", "Brun", "Rosa", "Grön", "KONST OCH KULTUR");
    Questions konst2 = new Questions("Vem målade den välkända tavlan 'Stjärnenatt'?","Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Edvard Munch", "KONST OCH KULTUR");
    Questions konst3 = new Questions("Vad heter den nakna statyn som är skapad av Michelangelo som står på torget framför Palazzo Vecchio i Florens?","David", "Elias", "Eva", "Lisa", "KONST OCH KULTUR");
    Questions konst4 = new Questions("Vad heter skivan som en målare brukar lägga sin färg i?","Palett", "Färgskiva", "Kulörkopp", "Färg tallrik", "KONST OCH KULTUR");
    Questions konst5 = new Questions("Leonardo da Vinci, en välkänd konstnär. Vilken stad föddes han i?","Florens", "Rom", "Venedig", "Neapel", "KONST OCH KULTUR");

    Questions språk1 = new Questions("Vad betyder det danska ordet 'undskyld'?","Ursäkta", "Munskydd", "Skuld", "Skam", "SPRÅK OCH TEXT");
    Questions språk2 = new Questions("Vilket språk brukade skottar prata förr i tiden?","Gäliska", "Kymriska", "Korniska", "Bretoniska", "SPRÅK OCH TEXT");
    Questions språk3 = new Questions("I vilken stad i Sverige pratar man 'sveamål'?","Stockholm", "Jämtland", "Västerbotten", "Fårö", "SPRÅK OCH TEXT");
    Questions språk4 = new Questions("Vad kallas en person som kan 2 eller fler språk flytande?","polygott", "polylang", "multispråklig", "polysagd", "SPRÅK OCH TEXT");
    Questions språk5 = new Questions("Vad betyder det franska ordet 'Au Revoir'?","Adjö", "Hej", "Jag kommer", "Nej Tack", "SPRÅK OCH TEXT");

    public Database() {

        DJUR_OCH_NATUR.add(natur1);
        DJUR_OCH_NATUR.add(natur2);
        DJUR_OCH_NATUR.add(natur3);
        DJUR_OCH_NATUR.add(natur4);
        DJUR_OCH_NATUR.add(natur5);

        GEOGRAFI.add(geografi1);
        GEOGRAFI.add(geografi2);
        GEOGRAFI.add(geografi3);
        GEOGRAFI.add(geografi4);
        GEOGRAFI.add(geografi5);

        MUSIK.add(musik1);
        MUSIK.add(musik2);
        MUSIK.add(musik3);
        MUSIK.add(musik4);
        MUSIK.add(musik5);

        HISTORIA.add(historia1);
        HISTORIA.add(historia2);
        HISTORIA.add(historia3);
        HISTORIA.add(historia4);
        HISTORIA.add(historia5);

        KROPP_OCH_KNOPP.add(kropp1);
        KROPP_OCH_KNOPP.add(kropp2);
        KROPP_OCH_KNOPP.add(kropp3);
        KROPP_OCH_KNOPP.add(kropp4);
        KROPP_OCH_KNOPP.add(kropp5);

        MAT_OCH_DRYCK.add(mat1);
        MAT_OCH_DRYCK.add(mat2);
        MAT_OCH_DRYCK.add(mat3);
        MAT_OCH_DRYCK.add(mat4);
        MAT_OCH_DRYCK.add(mat5);

        KÄNDISAR.add(kändis1);
        KÄNDISAR.add(kändis2);
        KÄNDISAR.add(kändis3);
        KÄNDISAR.add(kändis4);
        KÄNDISAR.add(kändis5);

        FILM_OCH_SERIER.add(film1);
        FILM_OCH_SERIER.add(film2);
        FILM_OCH_SERIER.add(film3);
        FILM_OCH_SERIER.add(film4);
        FILM_OCH_SERIER.add(film5);

        KONST_OCH_KULTUR.add(konst1);
        KONST_OCH_KULTUR.add(konst2);
        KONST_OCH_KULTUR.add(konst3);
        KONST_OCH_KULTUR.add(konst4);
        KONST_OCH_KULTUR.add(konst5);

        SPRÅK_OCH_TEXT.add(språk1);
        SPRÅK_OCH_TEXT.add(språk2);
        SPRÅK_OCH_TEXT.add(språk3);
        SPRÅK_OCH_TEXT.add(språk4);
        SPRÅK_OCH_TEXT.add(språk5);

        categoryList.add(DJUR_OCH_NATUR);
        categoryList.add(GEOGRAFI);
        categoryList.add(MUSIK);
        categoryList.add(HISTORIA);
        categoryList.add(KROPP_OCH_KNOPP);
        categoryList.add(MAT_OCH_DRYCK);
        categoryList.add(KÄNDISAR);
        categoryList.add(FILM_OCH_SERIER);
        categoryList.add(KONST_OCH_KULTUR);
        categoryList.add(SPRÅK_OCH_TEXT);

    }

    public List chooseCategory(String catRecieved) {
        List<Questions> tempList = new ArrayList<>();
        int counter = 0;
        for (var v: categoryList) {
            if (v.get(counter).getCategoryName().equalsIgnoreCase(catRecieved)){
                tempList = v;
            }
        }
        return tempList;
    }

    public void addUser(User u) {
        userList.add(u);
    }

    public List<User> getUsers() {
        return userList;
    }

    public void removeUser(User u) {
        userList.remove(u);
    }

    public static String randomCategorys(){
        Random r = new Random();
        int temp = r.nextInt(10);
        if (temp == 1)
            return "KROPP OCH KNOPP";
        else if (temp==2)
            return "MAT OCH DRYCK";
        else if (temp==3)
            return "KÄNDISAR";
        else if (temp==4)
            return "GEOGRAFI";
        else if (temp==5)
            return "HISTORIA";
        else if (temp==6)
            return "DJUR OCH NATUR";
        else if (temp==7)
            return "MUSIK";
        else if (temp==8)
            return "FILM OCH SERIER";
        else if (temp==9)
            return "KONST OCH KULTUR";
        else
        return "SPRÅK OCH TEXT";
    }
}
