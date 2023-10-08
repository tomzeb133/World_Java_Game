package projekt;

public class Guarana extends Roslina {
        public Guarana(int a, int b, Swiat s){
            sila=0;
            piktogram = 'G';
            nastepnyX = -1;
            nastepnyY = -1;
            inicjatywa = 0;
            wiek = 1;
            imie="Guarana";
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

        s.aktualizuj_informacje(agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", " + agresor.zwroc_X() + ") zjada " + this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X() + ")");
        agresor.sila+=3;
        s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
        s.wszystkiestworzenia.remove(indeks_broniacego);
        if(s.stworzenia_tablica[indeks_broniacego]!=null)
            s.stworzenia_tablica[indeks_broniacego]=null;
        return false;
    }
}
