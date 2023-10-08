package projekt;

abstract public class Organizm {
    int X, Y;
    int nastepnyX=-1, nastepnyY=-1, potencjalnyY=-1, potencjalnyX=-1;
    int sila, inicjatywa, wiek;
    String imie;
    char piktogram;
    int losowanie;
     boolean nowo_narodzony=false;

    public void polozenie(int a, int b) {
        Y = a;
        X = b;
    }
    public void kontroluj_narodzenie(boolean war){ nowo_narodzony=war;};
    public boolean zwroc_narodzenie(){ return nowo_narodzony;};
    public int zwroc_X() {
        return X;
    }
    public int zwroc_Y() {
        return Y;
    }
    public int zwroc_nastepnyX() {
        return nastepnyX;
    }
    public int zwroc_nastepnyY() {
        return nastepnyY;
    }
    public int zwroc_potencjalnyX() {
        return potencjalnyX;
    }
    public int zwroc_potencjalnyY() { return potencjalnyY; }
    public int zwroc_sila() {
        return sila;
    }
    public String zwroc_imie() {
        return imie;
    }
    public int zwroc_wiek() {
        return wiek;
    }
    public void postarzej_sie() {
        wiek++;
    }
    public int zwroc_inicjatywa() {
        return inicjatywa;
    }

    public void ustaw_nastepnewspolrzedne(int y, int x) {
        nastepnyY = y;
        nastepnyX = x;
    }
    public void ustaw_potencjalnewspolrzedne(int y, int x) {
        potencjalnyY = y;
        potencjalnyX = x;
    }
    public void sprawdz_czy_nie_ma_obok_barszczu(Swiat s){
        if(this instanceof Cyberowca)
            ;
        else {
            boolean barszcz_w_poblizu = false;
            if (Y != 0) {
                if (X != 0)
                    if (s.piktogram(Y - 1, X - 1) == 'B')
                        barszcz_w_poblizu = true;
                if (s.piktogram(Y - 1, X) == 'B')
                    barszcz_w_poblizu = true;
                if (X != s.zwroc_N() - 1)
                    if (s.piktogram(Y - 1, X + 1) == 'B')
                        barszcz_w_poblizu = true;
            }
            if (X != 0)
                if (s.piktogram(Y, X - 1) == 'B')
                    barszcz_w_poblizu = true;
            if (X != s.zwroc_M() - 1)
                if (s.piktogram(Y, X + 1) == 'B')
                    barszcz_w_poblizu = true;
            if (Y != s.zwroc_N() - 1) {
                if (X != 0)
                    if (s.piktogram(Y + 1, X - 1) == 'B')
                        barszcz_w_poblizu = true;
                if (s.piktogram(Y + 1, X) == 'B')
                    barszcz_w_poblizu = true;
                if (X != s.zwroc_N() - 1)
                    if (s.piktogram(Y + 1, X + 1) == 'B')
                        barszcz_w_poblizu = true;
            }
            if (barszcz_w_poblizu == true) {
                s.aktualizuj_informacje(imie + "(" + Y + ", " + X + ") wszedl na terytorium barszczu i zginal");
                s.wyczysc_pole(Y, X);
                int moj_indeks = 0;
                for (int t = 0; t < s.wszystkiestworzenia.size(); t++)
                    if (s.wszystkiestworzenia.get(t) == this) {
                        moj_indeks = t;
                        break;
                    }
                s.wszystkiestworzenia.remove(moj_indeks);
            }
        }
    }
    abstract public void akcja(Swiat s);
    abstract public void kolizja(Swiat s);
    abstract public void rysowanie(Swiat s) ;
    abstract public boolean obrona(Organizm agresor, Swiat s);
}
