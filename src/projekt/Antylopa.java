package projekt;

import java.util.Random;

public class Antylopa extends Zwierze {
    Antylopa(int a, int b, Swiat s1)
    {
        sila = 4;
        piktogram = 'A';
        inicjatywa = 4;
        wiek = 1;
        imie="Antylopa";
        polozenie(a, b);
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }
    @Override
    public void akcja(Swiat s) {
        Random random = new Random();
        while (true) {
            losowanie = random.nextInt(4) + 1;
            if (losowanie == 1 && Y != 0 && Y!=1) { //gora
                ustaw_potencjalnewspolrzedne(Y-2, X);
                break;
            }
            else if (losowanie == 2 && Y != s.zwroc_N()-1 && Y != s.zwroc_M()-2) { //dol
                ustaw_potencjalnewspolrzedne(Y+2, X);
                break;
            }
            else if (losowanie == 3 && X != s.zwroc_M()-1 && X != s.zwroc_M()-2) { //prawo
                ustaw_potencjalnewspolrzedne(Y, X+2);
                break;
            }
            else if (losowanie == 4 && X != 0 && X != 1) { //lewo
                ustaw_potencjalnewspolrzedne(Y, X-2);
                break;
            }
        }

        if(s.piktogram(this.zwroc_nastepnyY(), this.zwroc_nastepnyX())!=' '){
            int czy_uciec_przed_walka;
            czy_uciec_przed_walka = random.nextInt(2)+1;
            if(czy_uciec_przed_walka==1) { // ucieczka
                boolean gora_zajeta=false, dol_zajeta=false, prawo_zajeta=false, lewo_zajeta=false;
                while (true) {
                    if(gora_zajeta==true && dol_zajeta==true && prawo_zajeta==true && lewo_zajeta==true){
                        ustaw_potencjalnewspolrzedne(zwroc_Y(), zwroc_X());
                        break;
                    }
                    losowanie = random.nextInt(4) + 1;
                    if (losowanie == 1 &&  gora_zajeta==false) { //gora
                        if(s.piktogram(Y-1, X)!=' ')
                            gora_zajeta=true;
                        else {
                            ustaw_potencjalnewspolrzedne(Y - 1, X);
                            break;
                        }
                    }
                    else if (losowanie == 2  && dol_zajeta==false) { //dol
                        if(s.piktogram(Y+1, X)!=' ')
                            dol_zajeta=true;
                        else {
                            ustaw_potencjalnewspolrzedne(Y + 1, X);
                            break;
                        }
                    }
                    else if (losowanie == 3  && prawo_zajeta==false) { //prawo
                        if(s.piktogram(Y, X+1)!=' ')
                            prawo_zajeta=true;
                        else {
                            ustaw_potencjalnewspolrzedne(Y, X + 1);
                            break;
                        }
                    }
                    else if (losowanie == 4 && lewo_zajeta==false ) { //lewo
                        if(s.piktogram(Y, X-1)!=' ')
                            lewo_zajeta=true;
                        else {
                            ustaw_potencjalnewspolrzedne(Y, X - 1);
                            break;
                        }
                    }
                }
                s.aktualizuj_informacje("Antylopa("+zwroc_Y()+", "+zwroc_X()+") ucieka przed walka na "+"("+zwroc_potencjalnyY()+", "+zwroc_potencjalnyX()+')');
            }
        }
    }
}