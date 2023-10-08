package projekt;

public class Owca extends Zwierze {
    Owca(int a, int b, Swiat s1)
    {
        sila = 4;
        piktogram = 'O';
        inicjatywa = 4;
        wiek = 1;
        imie="Owca";
        polozenie(a, b);
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }

}
