package projekt;

public class Barszcz_sosnowskiego extends Roslina {
        public Barszcz_sosnowskiego(int a, int b, Swiat s){
            sila=0;
            piktogram = 'B';
            nastepnyX = -1;
            nastepnyY = -1;
            inicjatywa = 0;
            wiek = 1;
            imie="Barszcz Sosnowskiego";
            polozenie(a, b);
            s.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
            s.wszystkiestworzenia.add(this);
        }
    @Override
    public boolean obrona(Organizm agresor, Swiat s) {
        int indeks_atakujacego = 0, indeks_broniacego=0;
        for (int k = 0; k < s.wszystkiestworzenia.size(); k++)
            if (s.wszystkiestworzenia.get(k) == agresor)
                indeks_atakujacego = k;
            else if(s.wszystkiestworzenia.get(k)==this)
                indeks_broniacego=k;

        if(agresor instanceof Cyberowca)
            s.aktualizuj_informacje(agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", " + agresor.zwroc_X() + ") zjada " + this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X() + ")");
        else {
            s.aktualizuj_informacje(agresor.zwroc_imie() + "(" + agresor.zwroc_Y() + ", " + agresor.zwroc_X() + ") truje sie " + this.zwroc_imie() + "(" + this.zwroc_Y() + ", " + this.zwroc_X() + ") i ginie");
            s.wyczysc_pole(agresor.zwroc_Y(), agresor.zwroc_X());
            s.wszystkiestworzenia.remove(indeks_atakujacego);
        }
        s.wyczysc_pole(this.zwroc_Y(), this.zwroc_X());
        s.wszystkiestworzenia.remove(indeks_broniacego);
        return false;
    }
}
