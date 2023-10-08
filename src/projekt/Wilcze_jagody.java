package projekt;

public class Wilcze_jagody extends Roslina {
        public Wilcze_jagody(int a, int b, Swiat s){
            sila=99;
            piktogram = 'J';
            nastepnyX = -1;
            nastepnyY = -1;
            inicjatywa = 0;
            wiek = 1;
            imie="Wilcze Jagody";
            polozenie(a, b);
            s.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
            s.wszystkiestworzenia.add(this);
        }
    @Override
    public boolean obrona(Organizm agresor, Swiat s) {
        int indeks_atakujacego = 0, indeks_broniacego=0;
        for (int k = 0; k < s.stworzenia_tablica.length; k++)
            if (s.stworzenia_tablica[k] == agresor)
                indeks_atakujacego = k;
            else if(s.stworzenia_tablica[k]==this)
                indeks_broniacego=k;

        s.aktualizuj_informacje(agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", " + agresor.zwroc_X() + ") truje sie " + this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X() + ") i ginie");
        s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
        s.wszystkiestworzenia.remove(indeks_broniacego);
        if(s.stworzenia_tablica[indeks_broniacego]!=null)
            s.stworzenia_tablica[indeks_broniacego]=null;

        s.wyczysc_pole(agresor.zwroc_Y(), agresor.zwroc_X());
        s.wszystkiestworzenia.remove(indeks_atakujacego);
        if(s.stworzenia_tablica[indeks_atakujacego]!=null)
            s.stworzenia_tablica[indeks_atakujacego]=null;
        return false;
    }
}
