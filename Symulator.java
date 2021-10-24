package lab1;
import java.util.Random;

public class Symulator extends Thread{
    static int otwarte = 1;
    static int zamkniete = 2;
    static int zaopatrzenie_na_wykonczeniu = 3;
    static int warunki_ekstremalne = 4;
    
    public int numer, stan, zaopatrzenie;
    public Schronisko S;
    public Random r;
    
    public Symulator(int numer, int zaopatrzenie, Schronisko S){
        this.numer = numer;
        this.zaopatrzenie = zaopatrzenie;
        this.stan = 0;
        this.S = S;
        r = new Random();
    }
    
    public void run(){
        boolean petla = true;
        while(petla){
            if(stan == 0){
                stan = S.otwarcie(numer);
            }
            else if (stan == otwarte){
                int szansa_katastrofy = r.nextInt(101);
                if(szansa_katastrofy >= 23 && szansa_katastrofy <= 35 ) stan = warunki_ekstremalne;
                else if(zaopatrzenie > 10){
                    System.out.println(numer + ". Schronisko otwarte, zaopatrzenie w normie. (" + zaopatrzenie + ")");
                    try{
                        Thread.sleep(1000);
                    } catch(Exception e){}
                    zaopatrzenie -= r.nextInt(17)+6;
                }
                else stan = zaopatrzenie_na_wykonczeniu;
            }
            else if (stan == zaopatrzenie_na_wykonczeniu){
                System.out.println(numer + ". Zaopatrzenie na wykonczeniu!");
                stan = zamkniete;
            }
            else if (stan == zamkniete){
                System.out.println(numer + ". Dostawa zaopatrzenia.");
                zaopatrzenie += r.nextInt(50)+30;
                stan = otwarte;
            }
            else if (stan == warunki_ekstremalne){
                System.out.println(numer + ". Katastrofa!");
                petla = false;
            }
        }
    }
    
}
