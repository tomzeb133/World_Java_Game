package projekt;

import java.util.Random;

public class Zolw extends Zwierze {
    Zolw(int a, int b, Swiat s1) {
        sila = 2;
        piktogram = 'Z';
        inicjatywa = 1;
        wiek = 1;
        imie = "Zolw";
        polozenie(a, b);
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }
    @Override
    public void akcja(Swiat s) {
        Random random = new Random();
        int ruch_zolwia = random.nextInt(4) + 1;
        if (ruch_zolwia == 1) {
            while (true) {
                losowanie = random.nextInt(4) + 1;
                if (losowanie == 1 && Y != 0) { //gora
                    ustaw_potencjalnewspolrzedne(Y - 1, X);
                    break;
                } else if (losowanie == 2 && Y != s.zwroc_N() - 1) { //dol
                    ustaw_potencjalnewspolrzedne(Y + 1, X);
                    break;
                } else if (losowanie == 3 && X != s.zwroc_M() - 1) { //prawo
                    ustaw_potencjalnewspolrzedne(Y, X + 1);
                    break;
                } else if (losowanie == 4 && X != 0) { //lewo
                    ustaw_potencjalnewspolrzedne(Y, X - 1);
                    break;
                }
            }
        }
    }

    @Override
    public boolean obrona(Organizm agresor, Swiat s) {
        int indeks_atakujacego = 0, indeks_broniacego=0;
        for (int k = 0; k < s.stworzenia_tablica.length; k++)
            if (s.stworzenia_tablica[k] == agresor)
                indeks_atakujacego = k;
            else if(s.stworzenia_tablica[k]==this)
                indeks_broniacego=k;

        if (agresor.zwroc_sila() < 5) { // protektor przegrywa z agresorem
            s.aktualizuj_informacje( "Zolw(" + this.zwroc_Y() + ", " + this.zwroc_X()+ ") odpycha "+agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", "+agresor.zwroc_X()  + ")");
            agresor.ustaw_nastepnewspolrzedne(agresor.zwroc_Y(), agresor.zwroc_X());
        } else {  // protektor wygrywa z agresorem
            s.aktualizuj_informacje( agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", "+agresor.zwroc_X()  + ") atakuje i zabija "+this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X()+ ")" );
            s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
            s.wszystkiestworzenia.remove(indeks_broniacego);
            if(s.stworzenia_tablica[indeks_broniacego] != null)
                s.stworzenia_tablica[indeks_broniacego]=null;
        }
        return true;
    }
}
