package anona.matanglawin;


import java.util.HashSet;

public class TerminalObject {
     String place;
     HashSet<String> jeep = new HashSet<String>();
     double lati, longi;
     int steps;

     public TerminalObject(String place, HashSet<String> jeep, double lati, double longi) {
          this.place = place;
          this.jeep = jeep;
          this.lati = lati;
          this.longi = longi;
     }
}
