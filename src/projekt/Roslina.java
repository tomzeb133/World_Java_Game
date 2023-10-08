package projekt;
//import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Random;

abstract public class Roslina extends Organizm {
 int nasienie=0;
        public void akcja(Swiat s){
            Random random = new Random();
            nasienie = random.nextInt(3)+ 1;
            if (nasienie == 1) {
                        losowanie = random.nextInt(4) + 1;
                        if (losowanie == 1 && Y != 0) //gora
                            ustaw_potencjalnewspolrzedne(Y-1 ,X);
                         else if (losowanie == 2 && Y != s.zwroc_N()-1) //dol
                            ustaw_potencjalnewspolrzedne(Y+1 ,X);
                         else if (losowanie == 3 && X != s.zwroc_M()-1) //prawo
                            ustaw_potencjalnewspolrzedne(Y ,X+1);
                         else if (losowanie == 4 && X != 0)  //lewo
                            ustaw_potencjalnewspolrzedne(Y ,X-1);
                    }
        }
        @Override
        public void rysowanie(Swiat s){
            /*if(zwroc_nastepnyY()==-1 && zwroc_nastepnyX()==-1)
                ;
            else
                s.wstaw_organizm(zwroc_nastepnyY(), zwroc_nastepnyX(), piktogram);*/
        }
        @Override
        public void kolizja(Swiat s) {
            if(s.piktogram(zwroc_potencjalnyY(), zwroc_potencjalnyX())==' '){
                if(this.piktogram=='T')
                    new Trawa(potencjalnyY, potencjalnyX, s);
                else if(this.piktogram=='M')
                    new Mlecz(potencjalnyY, potencjalnyX, s);
                else if(this.piktogram=='G')
                    new Guarana(potencjalnyY, potencjalnyX, s);
                else if(this.piktogram=='J')
                    new Wilcze_jagody(potencjalnyY, potencjalnyX, s);
                else if(this.piktogram=='B')
                    new Barszcz_sosnowskiego(potencjalnyY, potencjalnyX, s);
              //  ustaw_nastepnewspolrzedne(zwroc_potencjalnyY(), zwroc_potencjalnyX());
            }
            //else
               // ustaw_nastepnewspolrzedne(-1,-1);
        }
        @Override
        public boolean obrona(Organizm agresor, Swiat s) {
            int indeks_atakujacego = 0, indeks_broniacego=0;
            for (int k = 0; k < s.stworzenia_tablica.length; k++)
                if (s.stworzenia_tablica[k] == agresor)
                    indeks_atakujacego = k;
                else if(s.stworzenia_tablica[k]==this)
                    indeks_broniacego=k;

                s.aktualizuj_informacje(agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", " + agresor.zwroc_X() + ") zjada " + this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X() + ")");
                s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
                for(int a=0; a<s.wszystkiestworzenia.size(); a++)
                    if(s.wszystkiestworzenia.get(a)==s.stworzenia_tablica[indeks_broniacego])
                        s.wszystkiestworzenia.remove(a);
                if(s.stworzenia_tablica[indeks_broniacego]!=null)
                    s.stworzenia_tablica[indeks_broniacego]=null;
            return false;
        }
};


