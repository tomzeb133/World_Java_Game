package projekt;

public class Trawa extends Roslina{
        public Trawa(int a, int b, Swiat s){
            sila=0;
            piktogram = 'T';
            nastepnyX = -1;
            nastepnyY = -1;
            inicjatywa = 0;
            wiek = 1;
            imie="Trawa";
            polozenie(a, b);
            s.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
            s.wszystkiestworzenia.add(this);
        }
}
