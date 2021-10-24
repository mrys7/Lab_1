package lab1;
import java.util.ArrayList;
import java.util.Scanner;

class Trapez extends Thread {
    public double pocz, kon, n, wynik;
    public Trapez(double poczatek, double koniec)
    {
        this.pocz = poczatek;
        this.kon = koniec;
        this.wynik = 0;
        this.n = 50;
    }
    public void run()
    {
        double kawalek = (this.kon - this.pocz) / this.n;
        for(int i = 1; i < n; i++) {
            this.wynik += Trapez.funkcja(this.pocz + i * kawalek);
        }
        this.wynik += ((Trapez.funkcja(this.pocz) + Trapez.funkcja(this.kon)) / 2);
        this.wynik *= kawalek;
    }
    public static double funkcja(double x){
        return (x*x*Math.sqrt(1+x))/(x*x + 1);
    }
}

class Simp extends Thread {

    public double pocz, kon, n, wynik;
    public Simp(double poczatek, double koniec)
    {
        this.pocz = poczatek;
        this.kon = koniec;
        this.wynik = 0;
        this.n = 50;
    }
    public void run()
    {
        double s = 0, x, kawalek = (this.pocz - this.kon) / this.n;
        for (int i = 1; i < n; i++) {
            x = this.pocz + i * kawalek;
            s += Trapez.funkcja(x - kawalek / 2);
            this.wynik += Trapez.funkcja(x);
        }
        s += Trapez.funkcja(this.kon - kawalek / 2);
        this.wynik = Math.abs((kawalek / 6) * (Trapez.funkcja(this.pocz) + Trapez.funkcja(this.kon) + 2 * this.wynik + 4 * s));
    }
}

class Prostokat extends Thread {

    public double pocz, kon, n, wynik;
    public Prostokat(double poczatek, double koniec)
    {
        this.pocz = poczatek;
        this.kon = koniec;
        this.wynik = 0;
        this.n = 50;
    }
    public void run()
    {
        double kawalek = (this.kon - this.pocz) / this.n;
        for (int i=1; i<=n; i++) {
            this.wynik += Trapez.funkcja(this.pocz + i * kawalek);
        }
        this.wynik*= kawalek;
    }
}

public class Lab1 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Calkowanie");
        System.out.println("2. Symulator");
        int wybor = scan.nextInt();
        switch(wybor){

            case 1:
        //zadanie 1
        ArrayList<Trapez> Trapezy = new ArrayList<>();
        ArrayList<Prostokat> Prostokaty = new ArrayList<>();
        ArrayList<Simp> Simpsony = new ArrayList<>();
        double a = 0, b = 2, wynik = 0;
        int n = 10;
        double krok = (b - a) / (double)n;

        for (double i = a; i < b - krok; i += krok) {
           Trapez t = new Trapez(i, i+krok);
            Trapezy.add(t);
            t.start();
            try {
                t.join();
            }catch(Exception e) {
            System.out.println("Blad w trapezach");
            }
        }
        for(Trapez t : Trapezy) {
            wynik += t.wynik;
        }
        System.out.println("Metoda trapezow    = " + wynik);
        wynik = 0;

        for (double i = a; i < b - krok; i += krok) {
            Prostokat t = new Prostokat(i, i+krok);
            Prostokaty.add(t);
            t.start();
            try {
                t.join();
            }catch(Exception e) {
            System.out.println("Blad w prostokatach");
            }
        }
        for(Prostokat t : Prostokaty) {
            wynik += t.wynik;
        }
        System.out.println("Metoda prostokatow = " + wynik);
        wynik = 0;
        
        for (double i = a; i < b - krok; i += krok) {
            Simp t = new Simp(i, i+krok);
            Simpsony.add(t);
            t.start();
            try {
                t.join();
            }catch(Exception e) {
            System.out.println("Blad w simpsonach");
            }
        }
        for(Simp t : Simpsony) {
            wynik += t.wynik;
        }
        System.out.println("Metoda simpsona    = " + wynik);
        break;
            case 2:
        //zadanie 2
        Schronisko schronisko = new Schronisko();
        for(int i = 0; i < 15; i ++)
            new Symulator(i, 99, schronisko).start();
        break;               
        }
        
    }
    
}
