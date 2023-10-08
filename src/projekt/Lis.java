package projekt;

import java.util.Random;

public class Lis extends Zwierze {
    Lis(int a, int b, Swiat s1)
    {
        sila = 3;
        piktogram = 'L';
        inicjatywa = 7;
        wiek = 1;
        imie="Lis";
        polozenie(a, b);
        s1.wstaw_organizm(zwroc_Y(), zwroc_X(), piktogram);
        s1.wszystkiestworzenia.add(this);
    }

    @Override
    public void akcja(Swiat s) {
        boolean gora_nie_wolno=false, dol_nie_wolno=false, prawo_nie_wolno=false, lewo_nie_wolno=false;
        Random random = new Random();
        while (true) {
            losowanie = random.nextInt(4) + 1;
            if (losowanie == 1 && Y != 0 && gora_nie_wolno==false) //gora
                ustaw_potencjalnewspolrzedne(Y-1, X);
            else if(losowanie==1 && Y==0){
                gora_nie_wolno=true;
                continue;
            }
            else if (losowanie == 2 && Y != s.zwroc_N()-1 && dol_nie_wolno==false) //dol
                ustaw_potencjalnewspolrzedne(Y+1, X);
            else if(losowanie==2 && Y==s.zwroc_N()-1){
                dol_nie_wolno=true;
                continue;
            }
            else if (losowanie == 3 && X != s.zwroc_M()-1 && prawo_nie_wolno==false)  //prawo
                ustaw_potencjalnewspolrzedne(Y, X+1);
            else if(losowanie==3 && X==s.zwroc_M()-1){
                prawo_nie_wolno=true;
                continue;
            }
            else if (losowanie == 4 && X != 0 && lewo_nie_wolno==false)  //lewo
                ustaw_potencjalnewspolrzedne(Y, X-1);
            else if(losowanie==4 && X==0){
                lewo_nie_wolno=true;
                continue;
            }
            else if(gora_nie_wolno==true && dol_nie_wolno==true && prawo_nie_wolno==true && lewo_nie_wolno==true) {
                ustaw_potencjalnewspolrzedne(-1, -1);
                break;
            }
            boolean czy_wszedl=false;
            for(int i=0; i<s.wszystkiestworzenia.size(); i++)
                if(s.wszystkiestworzenia.get(i).zwroc_Y()==zwroc_potencjalnyY() && s.wszystkiestworzenia.get(i).zwroc_X()==zwroc_potencjalnyX()){
                    czy_wszedl=true;
                    if(sila>=s.wszystkiestworzenia.get(i).zwroc_sila())
                        break;
                    else{
                        if(losowanie==1)
                            gora_nie_wolno=true;
                        else if(losowanie==2)
                            dol_nie_wolno=true;
                        else if(losowanie==3)
                            prawo_nie_wolno=true;
                        else
                            lewo_nie_wolno=true;
                    }

                }
            if(czy_wszedl==false)
                break;
        }
    }
}
