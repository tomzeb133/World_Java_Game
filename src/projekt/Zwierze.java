package projekt;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

abstract public class Zwierze extends Organizm{
    @Override
    public void akcja(Swiat s) {
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
    @Override
    public void kolizja(@NotNull Swiat s){
        int y=zwroc_potencjalnyY(), x=zwroc_potencjalnyX();
        int wolny_Y=-1, wolny_X=-1;
        boolean wolne_pola_znalezione=true;
        if(y==-1 && x==-1) {
            if (this instanceof Czlowiek)
                s.aktualizuj_informacje("Czlowiek rusza sie z (" + Y + ", " + X + ") na (" + Y + ", " + X + ')');
        }
        else if(s.piktogram(y, x)==' ') { //pole wolne
                ustaw_nastepnewspolrzedne(y, x);
                if (this instanceof Czlowiek)
                    s.aktualizuj_informacje("Czlowiek rusza sie z (" + Y + ", " + X + ") na (" + zwroc_nastepnyY() + ", " + zwroc_nastepnyX() + ')');
            }
        else{
            for(int a=0; a<s.stworzenia_tablica.length; a++) {
                if (s.stworzenia_tablica[a] != null) {
                    if (s.stworzenia_tablica[a] != this && s.stworzenia_tablica[a].zwroc_Y() == y && s.stworzenia_tablica[a].zwroc_X() == x && this.piktogram == s.stworzenia_tablica[a].piktogram) {
                        Organizm rodzic2 = s.stworzenia_tablica[a];
                        // ROZMNAŻANIE
                        // szukanie wolnego pola dookola 1 rodzica
                        if (s.piktogram(this.zwroc_Y() + 1, this.zwroc_X() + 1) == ' ') { // dwa +
                            wolny_Y = this.zwroc_Y() + 1;
                            wolny_X = this.zwroc_X() + 1;
                        } else if (s.piktogram(this.zwroc_Y() + 1, this.zwroc_X() - 1) == ' ') { // pierwszy +, drugi -
                            wolny_Y = this.zwroc_Y() + 1;
                            wolny_X = this.zwroc_X() - 1;
                        } else if (s.piktogram(this.zwroc_Y() - 1, this.zwroc_X() - 1) == ' ') { // dwa -
                            wolny_Y = this.zwroc_Y() - 1;
                            wolny_X = this.zwroc_X() - 1;
                        } else if (s.piktogram(this.zwroc_Y() - 1, this.zwroc_X() + 1) == ' ') { // pierwszy -, drugi +
                            wolny_Y = this.zwroc_Y() - 1;
                            wolny_X = this.zwroc_X() + 1;
                        } else if (s.piktogram(this.zwroc_Y() - 1, this.zwroc_X()) == ' ') { // pierwszy -, drugi bez zmian
                            wolny_Y = this.zwroc_Y() - 1;
                            wolny_X = this.zwroc_X();
                        } else if (s.piktogram(this.zwroc_Y() + 1, this.zwroc_X()) == ' ') { // pierwszy +, drugi bez zmian
                            wolny_Y = this.zwroc_Y() + 1;
                            wolny_X = this.zwroc_X();
                        } else if (s.piktogram(this.zwroc_Y(), this.zwroc_X() + 1) == ' ') { // pierwszy bez zmian, drugi +
                            wolny_Y = this.zwroc_Y();
                            wolny_X = this.zwroc_X() + 1;
                        } else if (s.piktogram(this.zwroc_Y(), this.zwroc_X() - 1) == ' ') { // pierwszy bez zmian, drugi -
                            wolny_Y = this.zwroc_Y();
                            wolny_X = this.zwroc_X() - 1;
                        }
                        // szukanie wolnego pola dookola 2 rodzica
                        else if (s.piktogram(rodzic2.zwroc_Y() + 1, rodzic2.zwroc_X() + 1) == ' ') { // dwa +
                            wolny_Y = rodzic2.zwroc_Y() + 1;
                            wolny_X = rodzic2.zwroc_X() + 1;
                        } else if (s.piktogram(rodzic2.zwroc_Y() + 1, rodzic2.zwroc_X() - 1) == ' ') { // pierwszy +, drugi -
                            wolny_Y = rodzic2.zwroc_Y() + 1;
                            wolny_X = rodzic2.zwroc_X() - 1;
                        } else if (s.piktogram(rodzic2.zwroc_Y() - 1, rodzic2.zwroc_X() - 1) == ' ') { // dwa -
                            wolny_Y = rodzic2.zwroc_Y() - 1;
                            wolny_X = rodzic2.zwroc_X() - 1;
                        } else if (s.piktogram(rodzic2.zwroc_Y() - 1, rodzic2.zwroc_X() + 1) == ' ') { // pierwszy -, drugi +
                            wolny_Y = rodzic2.zwroc_Y() - 1;
                            wolny_X = rodzic2.zwroc_X() + 1;
                        } else if (s.piktogram(rodzic2.zwroc_Y() - 1, rodzic2.zwroc_X()) == ' ') { // pierwszy -, drugi bez zmian
                            wolny_Y = rodzic2.zwroc_Y() - 1;
                            wolny_X = rodzic2.zwroc_X();
                        } else if (s.piktogram(rodzic2.zwroc_Y() + 1, rodzic2.zwroc_X()) == ' ') { // pierwszy +, drugi bez zmian
                            wolny_Y = rodzic2.zwroc_Y() + 1;
                            wolny_X = rodzic2.zwroc_X();
                        } else if (s.piktogram(rodzic2.zwroc_Y(), rodzic2.zwroc_X() + 1) == ' ') { // pierwszy bez zmian, drugi +
                            wolny_Y = rodzic2.zwroc_Y();
                            wolny_X = rodzic2.zwroc_X() + 1;
                        } else if (s.piktogram(rodzic2.zwroc_Y(), rodzic2.zwroc_X() - 1) == ' ') { // pierwszy bez zmian, drugi -
                            wolny_Y = rodzic2.zwroc_Y();
                            wolny_X = rodzic2.zwroc_X() - 1;
                        } else
                            wolne_pola_znalezione = false;
                        Organizm nowy_org = null;
                        if (wolne_pola_znalezione == true) {
                            if (this.piktogram == 'W')
                                nowy_org = new Wilk(wolny_Y, wolny_X, s);
                            else if (this.piktogram == 'O')
                                nowy_org = new Owca(wolny_Y, wolny_X, s);
                            else if (this.piktogram == 'Z')
                                nowy_org = new Zolw(wolny_Y, wolny_X, s);
                            else if (this.piktogram == 'L')
                                nowy_org = new Lis(wolny_Y, wolny_X, s);
                            else if (this.piktogram == 'A')
                                nowy_org = new Antylopa(wolny_Y, wolny_X, s);
                            s.wszystkiestworzenia.get(s.wszystkiestworzenia.size() - 1).kontroluj_narodzenie(true);
                            s.aktualizuj_informacje("Rodzi sie " + this.zwroc_imie() + "(" + wolny_Y + ", " + wolny_X + "), jego rodzice to (" + this.zwroc_Y() + ", " + this.zwroc_X() + ") oraz (" + s.stworzenia_tablica[a].zwroc_Y() + ", " + s.stworzenia_tablica[a].zwroc_X() + ')');
                        }
                        ustaw_nastepnewspolrzedne(-1, -1);
                        break;

                    } else if (s.stworzenia_tablica[a] != this && s.stworzenia_tablica[a].zwroc_Y() == y && s.stworzenia_tablica[a].zwroc_X() == x && this.piktogram != s.stworzenia_tablica[a].piktogram) {
                        // spotaknie innego organizmu
                        ustaw_nastepnewspolrzedne(y, x);
                        if (this instanceof Czlowiek)
                            s.aktualizuj_informacje("Czlowiek rusza sie z (" + Y + ", " + X + ") na (" + nastepnyY + ", " + nastepnyX + ')');
                        s.stworzenia_tablica[a].obrona(this, s);
                        break;
                    }
                }
            }

        }
    }
    @Override
    public void rysowanie(Swiat s){
        if(zwroc_nastepnyY()==-1 && zwroc_nastepnyX()==-1)
            ;
        else {
            s.wyczysc_pole(zwroc_Y(), zwroc_X());
            polozenie(zwroc_nastepnyY(), zwroc_nastepnyX());
            s.wstaw_organizm(zwroc_nastepnyY(), zwroc_nastepnyX(), piktogram);
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
        return true;
    }
}


