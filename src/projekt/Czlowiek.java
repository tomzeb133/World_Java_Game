package projekt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
//klasa CZLOWIEK

class Czlowiek extends Zwierze {
    int licznik_tur=0;
    boolean jestem_na_granicy=false;
    String stan_tarczy_alzura = "gotowa do uzycia", kierunek_ruchu_czlowieka=""; // tarcza ma 3 stany: gotowy do uzycia, laduje sie, obecnie uzywana
    String zwroc_stan_tarczy(){
        return stan_tarczy_alzura;
    }
    @Override
    public void akcja(Swiat s){
        if((stan_tarczy_alzura=="laduje sie" || stan_tarczy_alzura=="obecnie uzywana") && licznik_tur!=0) {
            licznik_tur--;
            if(licznik_tur==0)
                if(zwroc_stan_tarczy()=="laduje sie")
                    stan_tarczy_alzura="gotowa do uzycia";
                else if(zwroc_stan_tarczy()=="obecnie uzywana") {
                    stan_tarczy_alzura = "laduje sie";
                    licznik_tur=5;
                }
        }
        if(s.zwroc_kierunek_ruchu_czlowieka()=="lewo") {
            //nastepnyY = Y;
            if (X == 0) {
                //nastepnyX = X;
                ustaw_potencjalnewspolrzedne(Y, X);
                jestem_na_granicy = true;
            } else
                ustaw_potencjalnewspolrzedne(Y, X-1);
                //nastepnyX = X - 1;
        }
        if(s.zwroc_kierunek_ruchu_czlowieka()=="prawo") {
            //nastepnyY = Y;
            if (X == s.zwroc_M() - 1) {
                //nastepnyX = X;
                ustaw_potencjalnewspolrzedne(Y, X);
                jestem_na_granicy = true;
            } else
                ustaw_potencjalnewspolrzedne(Y, X+1);
                //nastepnyX = X + 1;
        }
        if(s.zwroc_kierunek_ruchu_czlowieka()=="gora") {
            //nastepnyX = X;
            if (Y == 0) {
                //nastepnyY = Y;
                ustaw_potencjalnewspolrzedne(Y, X);
                jestem_na_granicy = true;
            } else
                ustaw_potencjalnewspolrzedne(Y-1, X);
                //nastepnyY = Y - 1;
        }
        if(s.zwroc_kierunek_ruchu_czlowieka()=="dol") {
                  //  nastepnyX = X;
                    if (Y == s.zwroc_N()-1) {
                        //nastepnyY = Y;
                        ustaw_potencjalnewspolrzedne(Y, X);
                        jestem_na_granicy=true;
                    } else
                        ustaw_potencjalnewspolrzedne(Y+1, X);
                        //nastepnyY = Y + 1;
            }

    }
    @Override
    public void rysowanie(Swiat s){
        if (jestem_na_granicy==true) {
            jestem_na_granicy=false;
        }
        else {
            s.wyczysc_pole(zwroc_Y(), zwroc_X());
            polozenie(zwroc_nastepnyY(), zwroc_nastepnyX());
            s.wstaw_organizm(zwroc_nastepnyY(), zwroc_nastepnyX(), piktogram);
        }
    }
    Czlowiek(int a, int b, Swiat s1)
    {
        sila = 5;
        piktogram = 'C';
        inicjatywa = 4;
        wiek = 1;
        imie="Czlowiek";
        polozenie(a, b);
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
        //s1.dodaj_organizm(this);
    }
    public void ustaw_tarcza_alzura(Swiat s){
        if(licznik_tur==0){
            if(stan_tarczy_alzura=="gotowa do uzycia") {
                stan_tarczy_alzura = "obecnie uzywana";
                licznik_tur=5;
            }
        }
    }

    @Override
    public boolean obrona(Organizm agresor, Swiat s) {
        if(stan_tarczy_alzura=="obecnie uzywana")
            tarcza_alzura(agresor, s);
        else{
            int indeks_atakujacego = 0, indeks_broniacego=0;
            for (int k = 0; k < s.stworzenia_tablica.length; k++)
                if (s.stworzenia_tablica[k] == agresor)
                    indeks_atakujacego = k;
                else if(s.stworzenia_tablica[k]==this)
                    indeks_broniacego=k;

            if (this.zwroc_sila() < agresor.zwroc_sila()) { // protektor przegrywa z agresorem
                s.aktualizuj_informacje( agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", "+agresor.zwroc_X()  + ") atakuje i zabija "+this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X()+ ")" );
                s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
                s.wszystkiestworzenia.remove(indeks_broniacego);
                if(s.stworzenia_tablica[indeks_broniacego]!=null)
                    s.stworzenia_tablica[indeks_broniacego]=null;
            } else {  // protektor wygrywa z agresorem
                s.aktualizuj_informacje( agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", "+agresor.zwroc_X()  + ") atakuje i ginie z reki "+this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X()+ ")" );
                s.wyczysc_pole(s.wszystkiestworzenia.get(indeks_atakujacego).zwroc_Y(), s.wszystkiestworzenia.get(indeks_atakujacego).zwroc_X());
                s.wszystkiestworzenia.remove(indeks_atakujacego);
                if(s.stworzenia_tablica[indeks_atakujacego]!=null)
                    s.stworzenia_tablica[indeks_atakujacego]=null;
            }
        }
        return true;
    }

    public void tarcza_alzura(Organizm agresor, Swiat s){
        Random random = new Random();
        while (true) {
            losowanie = random.nextInt(4) + 1;
            if (losowanie == 1 && Y != 0) { //gora
                agresor.ustaw_potencjalnewspolrzedne(Y-1, X);
                break;
            }
            else if (losowanie == 2 && Y != s.zwroc_N()-1) { //dol
                agresor.ustaw_potencjalnewspolrzedne(Y+1, X);
                break;
            }
            else if (losowanie == 3 && X != s.zwroc_M()-1) { //prawo
                agresor.ustaw_potencjalnewspolrzedne(Y, X+1);
                break;
            }
            else if (losowanie == 4 && X != 0) { //lewo
                agresor.ustaw_potencjalnewspolrzedne(Y, X-1);
                break;
            }
        }
        s.aktualizuj_informacje("Czlowiek("+Y+", "+X+") odpycha(tarcza) "+agresor.zwroc_imie()+" ("+agresor.zwroc_Y()+", "+agresor.zwroc_X()+")");
        agresor.kolizja(s);
    }
};
