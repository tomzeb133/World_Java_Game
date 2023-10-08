package projekt;

import java.util.Random;
import java.util.Vector;

public class Cyberowca extends Zwierze{
    public  Vector<Organizm> wszystkie_barszcze = new Vector<>();
    int najkrotsza_droga=1000, droga=1000, indeks_najblizszego=0;
    Cyberowca(int a, int b, Swiat s1)
    {
        sila = 11;
        piktogram = 'Y';
        inicjatywa = 4;
        wiek = 1;
        polozenie(a, b);
        imie="Cyberowca";
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }
    @Override
    public void akcja(Swiat s) {
        wszystkie_barszcze.clear();
        for(int x=0; x<s.wszystkiestworzenia.size(); x++){
            if(s.wszystkiestworzenia.get(x) instanceof Barszcz_sosnowskiego)
                wszystkie_barszcze.add(s.wszystkiestworzenia.get(x));
        }
        if(wszystkie_barszcze.size()==0){
            Random random = new Random();
            while (true) {
                losowanie = random.nextInt(4) + 1;
                if (losowanie == 1 && Y != 0) { //gora
                    ustaw_potencjalnewspolrzedne(Y-1, X);
                    break;
                }
                else if (losowanie == 2 && Y != s.zwroc_N()-1) { //dol
                    ustaw_potencjalnewspolrzedne(Y+1, X);
                    break;
                }
                else if (losowanie == 3 && X != s.zwroc_M()-1) { //prawo
                    ustaw_potencjalnewspolrzedne(Y, X+1);
                    break;
                }
                else if (losowanie == 4 && X != 0) { //lewo
                    ustaw_potencjalnewspolrzedne(Y, X-1);
                    break;
                }
            }
        }
        else{
            indeks_najblizszego=0;
            najkrotsza_droga=1000;
            droga=0;
            for(int x=0; x<wszystkie_barszcze.size(); x++){
                if(wszystkie_barszcze.get(x).zwroc_Y() >= this.zwroc_Y() && wszystkie_barszcze.get(x).zwroc_X() >= this.zwroc_X())
                    droga= (wszystkie_barszcze.get(x).zwroc_Y() - this.zwroc_Y()) + (wszystkie_barszcze.get(x).zwroc_X() - this.zwroc_X());
                else if(wszystkie_barszcze.get(x).zwroc_Y() >= this.zwroc_Y() && wszystkie_barszcze.get(x).zwroc_X() <= this.zwroc_X())
                    droga= (wszystkie_barszcze.get(x).zwroc_Y() - this.zwroc_Y()) + (this.zwroc_X() - wszystkie_barszcze.get(x).zwroc_X());
                else if(wszystkie_barszcze.get(x).zwroc_Y() <= this.zwroc_Y() && wszystkie_barszcze.get(x).zwroc_X() >= this.zwroc_X())
                    droga= (this.zwroc_Y() - wszystkie_barszcze.get(x).zwroc_Y()) + (wszystkie_barszcze.get(x).zwroc_X() - this.zwroc_X());
                else if(wszystkie_barszcze.get(x).zwroc_Y() <= this.zwroc_Y() && wszystkie_barszcze.get(x).zwroc_X() <= this.zwroc_X())
                    droga= ( this.zwroc_Y() - wszystkie_barszcze.get(x).zwroc_Y()) + (this.zwroc_X() - wszystkie_barszcze.get(x).zwroc_X());

                if(droga< najkrotsza_droga){
                    najkrotsza_droga=droga;
                    indeks_najblizszego=x;
                }
            }

            if(wszystkie_barszcze.get(indeks_najblizszego).zwroc_Y()< zwroc_Y())
                ustaw_potencjalnewspolrzedne(Y-1, X);
            else if(wszystkie_barszcze.get(indeks_najblizszego).zwroc_Y()> zwroc_Y())
                ustaw_potencjalnewspolrzedne(Y+1, X);
            else if(wszystkie_barszcze.get(indeks_najblizszego).zwroc_X()< zwroc_X())
                ustaw_potencjalnewspolrzedne(Y, X-1);
            else if(wszystkie_barszcze.get(indeks_najblizszego).zwroc_X()> zwroc_X())
                ustaw_potencjalnewspolrzedne(Y, X+1);


        }

    }
}
