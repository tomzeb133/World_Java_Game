package projekt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Swiat extends javax.swing.JFrame implements ActionListener {
    int N, M;
    char[][] plansza;
    Vector<String> informacje;
    boolean czlowiekmartwy = false;
    String kierunek_ruchu_czlowieka;
    public Vector<Organizm> wszystkiestworzenia = new Vector<>();
    public Organizm[] stworzenia_tablica = null;
    Przycisk[][] mapa;
    JLabel[] legenda;
    JLabel naglowek, tarcza;
    JLabel[] komentator;
    JButton gora, dol, prawo, lewo, zapis, tarcza_alzura;
    JRadioButton wilk,owca,lis,zolw,antylopa, cyberowca,czlowiek,trawa,mlecz,guarana,wilcze_jagody,barszcz_sosnowskiego;
    JButton ok;
    JDialog dialog;
    Random random = new Random();

    public void wstaw_organizm(int y, int x, char znaczek) {
        if (y < N && y >= 0 && x < N && x >= 0)
            plansza[y][x] = znaczek;
    }

    public void aktualizuj_informacje(String s) {
        informacje.add(s);
    }

    public void dodaj_organizm(Organizm org) {

        //std::vector<Zwierze>::iterator it;
        Organizm it = (Organizm) wszystkiestworzenia.iterator();
        it = wszystkiestworzenia.firstElement();
        int index = 0;

        if (wszystkiestworzenia.size() == 0) {
            wszystkiestworzenia.add(org);
        } else {
            //znajdz indeks pierwszy gdzie insert
            while ((wszystkiestworzenia.get(index).inicjatywa > org.inicjatywa) || (wszystkiestworzenia.get(index).inicjatywa == org.inicjatywa && wszystkiestworzenia.get(index).wiek >= org.wiek)) {
                index++;
            }

            //it += index;
            wszystkiestworzenia.insertElementAt(org, index);
        }
        System.out.println();
        for (int y = 0; y < wszystkiestworzenia.size(); y++) {
            System.out.println(Integer.toString(y) + ". " + wszystkiestworzenia.get(y).zwroc_imie() + ", ini = " + wszystkiestworzenia.get(y).zwroc_inicjatywa() + " wiek = " + wszystkiestworzenia.get(y).zwroc_wiek());
        }
        System.out.println();

    }

    public void zapis_do_pliku()
            throws IOException {
        String tresc = "";
        for (int t = 0; t < komentator.length; t++)
            tresc += "\n" + komentator[t].getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter(".\\zdarzenia.txt"));
        writer.write(tresc);
        writer.close();
    }

    public void przydzial() {
        Organizm organizm = null;
        int czy_powstanie_zycie = 0, los = 0, losowyX_Czlowieka, losowyY_Czlowieka;
        losowyY_Czlowieka = random.nextInt(N);
        losowyX_Czlowieka = random.nextInt(M);
        organizm = new Czlowiek(losowyY_Czlowieka, losowyX_Czlowieka, this);
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < M; b++) {
                if (a != losowyY_Czlowieka && b != losowyX_Czlowieka) {
                    czy_powstanie_zycie = random.nextInt() % 3 + 1;
                    if (czy_powstanie_zycie == 1 || czy_powstanie_zycie == 2) {
                        los = random.nextInt() % 11 + 1;
                        organizm = null;
                        switch (los) {
                            case 1:
                                organizm = new Wilk(a, b, this);
                                break;
                            case 2:
                                organizm = new Owca(a, b, this);
                                break;
                            case 3:
                                organizm = new Lis(a, b, this);
                                break;
                            case 4:
                                organizm = new Zolw(a, b, this);
                                break;
                            case 5:
                                organizm = new Antylopa(a, b, this);
                                break;
                            case 6:
                                organizm = new Trawa(a, b, this);
                                break;
                            case 7:
                                organizm = new Mlecz(a, b, this);
                                break;
                            case 8:
                                organizm = new Guarana(a, b, this);
                                break;
                            case 9:
                                organizm = new Wilcze_jagody(a, b, this);
                                break;
                            case 10:
                                organizm = new Barszcz_sosnowskiego(a, b, this);
                                break;
                            case 11:
                                organizm = new Cyberowca(a, b, this);
                                break;
                        }
                    }
                }
            }
        }
    }

    public Swiat() {
        N=Integer.parseInt(JOptionPane.showInputDialog("Podaj N: "));
        M=Integer.parseInt(JOptionPane.showInputDialog("Podaj M: "));
        /*Scanner scan = new Scanner(System.in);
        System.out.print("UWAGA! Nalezy podac wymiary co najmniej 10 x 10 (zalecany wymiar)");
        System.out.print("Podaj N:   ");
        N = scan.nextInt();
        System.out.print("Podaj M:   ");
        M = scan.nextInt();*/


        this.setTitle("Tomasz Żebrowski - 180338");
        int umiejscowienie_niektorych_elementow = 0;
        informacje = new Vector();

       // N = 20;
       // M = 20;
        umiejscowienie_niektorych_elementow = 30 * M + 20 + 100;
        plansza = new char[N][M];
        setSize(1400, 800);
        setLayout(null);
        mapa = new Przycisk[N][M];
        for (int n = 0; n < N; n++)
            for (int m = 0; m < M; m++) {
                plansza[n][m] = ' ';
                mapa[n][m] = new Przycisk(n, m);
                Swiat adres=this;
                mapa[n][m].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object obj = e.getSource();
                        if(obj instanceof  Przycisk) {
                            System.out.println("X :" + ((Przycisk) obj).x + " Y : " + ((Przycisk)obj).y);
                            int iksy=((Przycisk) obj).x;
                            int igreki=((Przycisk) obj).y;
                            dodajAkcje(igreki, iksy, adres);
                        }
                    }
                });
                mapa[n][m].setBackground(Color.white);
                mapa[n][m].setLocation(100 + 30 * m, 100 + 30 * n);
                mapa[n][m].setSize(30, 30);
                mapa[n][m].setBorder(BorderFactory.createLineBorder(Color.black));
                add(mapa[n][m]);
            }
        przydzial();
        //new Czlowiek(0, 0, this);
        //new Owca(1, 0 ,this);
        //new Antylopa(0, 0, this);
        //new Zolw(2, 0, this);
        //new Zolw(0, 2, this);
        //new Mlecz(0, 0, this);
        //  new Trawa(4, 4, this);
        //new Guarana(4, 0, this);
        //new Barszcz_sosnowskiego(0, 0, this);
        //new Cyberowca(2, 2, this);
        //new Wilk(1, 0, this);
        //new Wilk(0, 1, this);
        //new Wilk(1, 1, this);
        //new Wilcze_jagody(0, 4 ,this);
        //new Mlecz(2, 2, this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gora = new JButton();
        gora.setSize(75, 75);
        gora.setLocation(umiejscowienie_niektorych_elementow + 250, 100);
        gora.setBackground(Color.LIGHT_GRAY);
        gora.setForeground(Color.black);
        gora.setText("Góra");
        gora.addActionListener(this);
        this.add(gora);

        dol = new JButton();
        dol.setSize(75, 75);
        dol.setLocation(umiejscowienie_niektorych_elementow + 250, 180);
        dol.setBackground(Color.LIGHT_GRAY);
        dol.setForeground(Color.black);
        dol.setText("Dół");
        dol.addActionListener(this);
        this.add(dol);

        prawo = new JButton();
        prawo.setSize(75, 75);
        prawo.setLocation(umiejscowienie_niektorych_elementow + 330, 180);
        prawo.setBackground(Color.LIGHT_GRAY);
        prawo.setForeground(Color.black);
        prawo.setText("Prawo");
        prawo.addActionListener(this);
        this.add(prawo);

        lewo = new JButton();
        lewo.setSize(75, 75);
        lewo.setLocation(umiejscowienie_niektorych_elementow + 120 + 50, 180);
        lewo.setBackground(Color.LIGHT_GRAY);
        lewo.setForeground(Color.black);
        lewo.setText("Lewo");
        lewo.addActionListener(this);
        this.add(lewo);

        tarcza_alzura = new JButton();
        tarcza_alzura.setSize(150, 50);
        tarcza_alzura.setLocation(umiejscowienie_niektorych_elementow, 500);
        tarcza_alzura.setBackground(Color.LIGHT_GRAY);
        tarcza_alzura.setForeground(Color.black);
        tarcza_alzura.setText("Tarcza alzura");
        tarcza_alzura.addActionListener(this);
        this.add(tarcza_alzura);

        zapis = new JButton();
        zapis.setSize(200, 40);
        zapis.setLocation(umiejscowienie_niektorych_elementow + 250, 500);
        zapis.setBackground(Color.LIGHT_GRAY);
        zapis.setForeground(Color.black);
        zapis.setText("Zapis do pliku");
        zapis.addActionListener(this);
        this.add(zapis);

        tarcza = new JLabel();
        tarcza.setBounds(umiejscowienie_niektorych_elementow, 560, 150, 50);
        tarcza.setForeground(Color.BLACK);
        zapis.setBackground(Color.LIGHT_GRAY);
        tarcza.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tarcza.setText("gotowa do uzycia");
        tarcza.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.add(tarcza);

        legenda = new JLabel[13];
        legenda[0] = new JLabel("LEGENDA: ");
        legenda[1] = new JLabel("WILK ");
        legenda[2] = new JLabel("OWCA ");
        legenda[3] = new JLabel("LIS ");
        legenda[4] = new JLabel("ZOLW ");
        legenda[5] = new JLabel("ANTYLOPA ");
        legenda[6] = new JLabel("TRAWA ");
        legenda[7] = new JLabel("MLECZ ");
        legenda[8] = new JLabel("GUARANA ");
        legenda[9] = new JLabel("JAGODY ");
        legenda[10] = new JLabel("BARSZCZ ");
        legenda[11] = new JLabel("CYBEROWCA ");
        legenda[12] = new JLabel("CZLOWIEK ");

        JPanel kwadrat[] = new JPanel[12];
        for (int y = 0; y < 12; y++)
            kwadrat[y] = new JPanel();
        kwadrat[0].setBackground(new Color(102, 102, 102)); // WILK
        kwadrat[1].setBackground(new Color(51, 204, 255)); // OWCA
        kwadrat[2].setBackground(new Color(255, 102, 0)); // LIS
        kwadrat[3].setBackground(new Color(0, 0, 153)); // ZOLW
        kwadrat[4].setBackground(new Color(153, 102, 0)); // ANTYLOPA
        kwadrat[5].setBackground(new Color(0, 102, 0)); // TRAWA
        kwadrat[6].setBackground(new Color(255, 204, 0)); // MLECZ
        kwadrat[7].setBackground(new Color(153, 0, 0)); // GUARANA
        kwadrat[8].setBackground(new Color(102, 0, 153)); // WILCZE JAGODY
        kwadrat[9].setBackground(Color.PINK); // BARSZCZ SOSNOWSKIEGO
        kwadrat[10].setBackground(new Color(51, 0, 0)); // CYBEROWCA
        kwadrat[11].setBackground(new Color(0, 0, 0)); // CZLOWIEK

        for (int y = 0; y < 13; y++) {
            legenda[y].setBounds(umiejscowienie_niektorych_elementow, 100 + 20 * y, 300, 17);
            legenda[y].setForeground(Color.BLACK);
            legenda[y].setFont(new Font("Verdana", Font.PLAIN, 17));
            this.add(legenda[y]);
            if (y != 12) {
                kwadrat[y].setSize(17, 17);
                kwadrat[y].setBounds(umiejscowienie_niektorych_elementow + 120, 120 + y * 20, 17, 17);
                this.add(kwadrat[y]);
            }
        }

        naglowek = new JLabel();
        naglowek.setSize(75, 20);
        naglowek.setLocation(umiejscowienie_niektorych_elementow + 120 + 50, 260);
        naglowek.setText("Komentator");
        this.add(naglowek);

        komentator = new JLabel[10];
        for (int x = 0; x < 10; x++) {
            komentator[x] = new JLabel();
            komentator[x].setSize(400, 20);
            komentator[x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            komentator[x].setLocation(umiejscowienie_niektorych_elementow + 120 + 50, 280 + x * 20);
            komentator[x].setText(" brak");
            this.add(komentator[x]);
        }
        this.rysujSwiat();
        for (int x = 0; x < wszystkiestworzenia.size(); x++)
            System.out.println(wszystkiestworzenia.get(x).imie + " (" + wszystkiestworzenia.get(x).zwroc_Y() + ", " + wszystkiestworzenia.get(x).zwroc_X() + ")");
        System.out.println();
        //dodajAkcje();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();

        if (zrodlo == zapis) {
            try {
                this.zapis_do_pliku();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (zrodlo == tarcza_alzura) {
            for (int h = 0; h < wszystkiestworzenia.size(); h++)
                if (wszystkiestworzenia.get(h) instanceof Czlowiek)
                    ((Czlowiek) wszystkiestworzenia.get(h)).ustaw_tarcza_alzura(this);
        }
        //else if(zrodlo==mapa)
        else {
            if (zrodlo == gora)
                kierunek_ruchu_czlowieka = "gora";
            else if (zrodlo == dol)
                kierunek_ruchu_czlowieka = "dol";
            else if (zrodlo == prawo)
                kierunek_ruchu_czlowieka = "prawo";
            else if (zrodlo == lewo)
                kierunek_ruchu_czlowieka = "lewo";

            for (int q = 0; q < 10; q++)
                komentator[q].setText("brak");

            if (stworzenia_tablica != null)
                stworzenia_tablica = null;
            int rozmiar = 0;
            rozmiar = wszystkiestworzenia.size();
            stworzenia_tablica = new Organizm[rozmiar];
            for (int x = 0; x < rozmiar; x++)
                stworzenia_tablica[x] = wszystkiestworzenia.get(x);
            for (int x = 0; x < rozmiar; x++) {
                if (stworzenia_tablica[x] != null) {
                    stworzenia_tablica[x].akcja(this);
                    stworzenia_tablica[x].kolizja(this);
                    if (stworzenia_tablica[x] != null) { // drugi raz sprawdzam ten warunek, bo w kolziji obecny organizm mógł zginąć
                        stworzenia_tablica[x].rysowanie(this);
                        if (stworzenia_tablica[x] instanceof Zwierze)
                            stworzenia_tablica[x].sprawdz_czy_nie_ma_obok_barszczu(this);

                    }
                }
            }
            for (int x = 0; x < wszystkiestworzenia.size(); x++)
                System.out.println(wszystkiestworzenia.get(x).imie + " (" + wszystkiestworzenia.get(x).zwroc_Y() + ", " + wszystkiestworzenia.get(x).zwroc_X() + ")");
            for (int q = 0; q < wszystkiestworzenia.size(); q++)
                if (wszystkiestworzenia.get(q) instanceof Czlowiek) {
                    tarcza.setText(((Czlowiek) wszystkiestworzenia.get(q)).zwroc_stan_tarczy());

                }
        /*System.out.println();
        for(int q=0; q<N; q++) {
            for(int w=0; w<M; w++)
                System.out.print(Character.toString(plansza[q][w]) + Character.toString(' '));
            System.out.println();
        }*/
            rysujSwiat();
        /*for(int y=0; y<wszystkiestworzenia.size(); y++)
            wszystkiestworzenia.get(y).kontroluj_narodzenie(false);*/
            System.out.println("Liczba org: " + Integer.toString(wszystkiestworzenia.size()));
            System.out.println();
        }
    }

    public String zwroc_kierunek_ruchu_czlowieka() {
        return kierunek_ruchu_czlowieka;
    }

    public void rysujSwiat() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (Character.compare(plansza[n][m], ' ') == 0)
                    mapa[n][m].setBackground(Color.WHITE);
                else if (plansza[n][m] == 'C')
                    mapa[n][m].setBackground(new Color(0, 0, 0));
                else if (plansza[n][m] == 'W')
                    mapa[n][m].setBackground(new Color(102, 102, 102));
                else if (plansza[n][m] == 'Z')
                    mapa[n][m].setBackground(new Color(0, 0, 153));
                else if (plansza[n][m] == 'L')
                    mapa[n][m].setBackground(new Color(255, 102, 0));
                else if (plansza[n][m] == 'A')
                    mapa[n][m].setBackground(new Color(153, 102, 0));
                else if (plansza[n][m] == 'O')
                    mapa[n][m].setBackground(new Color(51, 204, 255));
                else if (plansza[n][m] == 'T')
                    mapa[n][m].setBackground(new Color(0, 102, 0));
                else if (plansza[n][m] == 'M')
                    mapa[n][m].setBackground(new Color(255, 204, 0));
                else if (plansza[n][m] == 'G')
                    mapa[n][m].setBackground(new Color(153, 0, 0));
                else if (plansza[n][m] == 'J')
                    mapa[n][m].setBackground(new Color(102, 0, 153));
                else if (plansza[n][m] == 'B')
                    mapa[n][m].setBackground(Color.PINK);
                else if (plansza[n][m] == 'Y')
                    mapa[n][m].setBackground(new Color(51, 0, 0));
            }
        }
        for (int q = 0; q < informacje.size(); q++) {
            if (q < 10)
                komentator[q].setText(informacje.get(q));
        }
        informacje.clear();

    }

    public void wyczysc_pole(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) {
            plansza[y][x] = ' ';
            mapa[y][x].setBackground(Color.WHITE);
        }
    }

    public char piktogram(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M)
            return plansza[y][x];
        else
            return '&';
    }

    public int zwroc_N() {
        return N;
    }

    public int zwroc_M() {
        return M;
    }

    public void ustaw_czlowiekmartwy() {
        czlowiekmartwy = true;
        System.out.print("\n\n\nCzlowiek umarl, Game Over");
    }

    public void dodajAkcje(int y, int x, Swiat s) {
        dialog = new JDialog();
        wilk = new JRadioButton("wilk");
        owca = new JRadioButton("owca");
        lis = new JRadioButton("lis");
        zolw = new JRadioButton("zolw");
        antylopa = new JRadioButton("antylopa");
        cyberowca = new JRadioButton("cyberowca");
        czlowiek = new JRadioButton("czlowiek");
        trawa = new JRadioButton("trawa");
        mlecz = new JRadioButton("mlecz");
        guarana = new JRadioButton("guarana");
        wilcze_jagody = new JRadioButton("wilcze jagody");
        barszcz_sosnowskiego = new JRadioButton("barscz sosnowskiego");
        ok = new JButton("Ok");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // System.out.println("Rompus");
                dialog.dispose();
                if(s.piktogram(y, x)==' '){
                    if(wilk.isSelected()==true)
                        new Wilk(y, x, s);
                    else if(owca.isSelected()==true)
                        new Owca(y, x, s);
                    else if(lis.isSelected()==true)
                        new Lis(y, x, s);
                    else if(zolw.isSelected()==true)
                        new Zolw(y, x, s);
                    else if(antylopa.isSelected()==true)
                        new Antylopa(y, x, s);
                    else if(cyberowca.isSelected()==true)
                        new Cyberowca(y, x, s);
                    else if(czlowiek.isSelected()==true)
                        new Czlowiek(y, x, s);
                    else if(trawa.isSelected()==true)
                        new Trawa(y, x, s);
                    else if(mlecz.isSelected()==true)
                        new Mlecz(y, x, s);
                    else if(guarana.isSelected()==true)
                        new Guarana(y, x, s);
                    else if(wilcze_jagody.isSelected()==true)
                        new Wilcze_jagody(y, x, s);
                    else if(barszcz_sosnowskiego.isSelected()==true)
                        new Barszcz_sosnowskiego(y, x, s);

                    s.rysujSwiat();
                }
                else
                    System.out.println("Zajęte pole :(");
            }
        });

        dialog.add(wilk);
        dialog.add(owca);
        dialog.add(lis);
        dialog.add(zolw);
        dialog.add(antylopa);
        dialog.add(cyberowca);
        dialog.add(czlowiek);
        dialog.add(trawa);
        dialog.add(mlecz);
        dialog.add(guarana);
        dialog.add(wilcze_jagody);
        dialog.add(barszcz_sosnowskiego);
        dialog.add(ok);
        ok.addActionListener(this);
        dialog.setLayout(new FlowLayout());
        dialog.setVisible(true);
        dialog.setSize(200,450);
    }
}
