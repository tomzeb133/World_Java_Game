package projekt;

import java.util.Random;

public class Mlecz extends Roslina {
        public Mlecz(int a, int b, Swiat s){
            sila=0;
            piktogram = 'M';
            nastepnyX = -1;
            nastepnyY = -1;
            inicjatywa = 0;
            wiek = 1;
            imie="Mlecz";
            polozenie(a, b);
            s.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
            s.wszystkiestworzenia.add(this);
            //s.dodaj_organizm(this);
        }
        @Override
        public void akcja(Swiat s) {
            for(int q=0; q<3; q++) {
                Random random = new Random();
                nasienie = random.nextInt(3) + 1;
                if (nasienie == 1) {
                    losowanie = random.nextInt(4) + 1;
                    if (losowanie == 1 && Y != 0) //gora
                        ustaw_potencjalnewspolrzedne(Y - 1, X);
                    else if (losowanie == 2 && Y != s.zwroc_N() - 1) //dol
                        ustaw_potencjalnewspolrzedne(Y + 1, X);
                    else if (losowanie == 3 && X != s.zwroc_M() - 1) //prawo
                        ustaw_potencjalnewspolrzedne(Y, X + 1);
                    else if (losowanie == 4 && X != 0)  //lewo
                        ustaw_potencjalnewspolrzedne(Y, X - 1);
                }

                if (s.piktogram(zwroc_potencjalnyY(), zwroc_potencjalnyX()) == ' ') {
                        new Mlecz(potencjalnyY, potencjalnyX, s);
                    //ustaw_nastepnewspolrzedne(zwroc_potencjalnyY(), zwroc_potencjalnyX());
                }
                //else
                  //  ustaw_nastepnewspolrzedne(-1, -1);

                //if(zwroc_nastepnyY()==-1 && zwroc_nastepnyX()==-1)
                  //  ;
                //else
                  //  s.wstaw_organizm(zwroc_nastepnyY(), zwroc_nastepnyX(), piktogram);
            }
        }

    @Override
    public void kolizja(Swiat s) {
        ;
    }

    @Override
    public void rysowanie(Swiat s) {
        ;
    }
}
