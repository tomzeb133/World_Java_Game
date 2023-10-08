package projekt;

public class Wilk extends Zwierze{
    Wilk(int a, int b, Swiat s1)
    {
        sila = 9;
        piktogram = 'W';
        inicjatywa = 5;
        wiek = 1;
        polozenie(a, b);
        imie="Wilk";
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }
}
