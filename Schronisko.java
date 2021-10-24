package lab1;

public class Schronisko {
    public Schronisko(){
    }
    
    synchronized int otwarcie(int numer){
        System.out.println(numer + ". Schronisko owawrte.");
        return 1;
    }
}
