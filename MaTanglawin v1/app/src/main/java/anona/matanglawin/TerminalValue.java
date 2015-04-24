package anona.matanglawin;


import java.util.HashSet;

public class TerminalValue {
     static HashSet<TerminalObject> terminal = new HashSet<>();
     static double latitude, longitude;
     static final double radius = 6372.8;

     public TerminalValue(double latitude, double longitude) {
          this.latitude = latitude;
          this.longitude = longitude;
     }

     public static void main(String[] args) {
          populate();
          //haversine();
     }

     public static void populate() {
          HashSet<String> jeep = new HashSet<>();

          jeep.add("SM");
          terminal.add(new TerminalObject("Vinzons", jeep, 14.654337, 121.073016));
          jeep.clear();

          jeep.add("MRT");
          terminal.add(new TerminalObject("Vinzons", jeep, 14.655873, 121.072973));
          jeep.clear();

          jeep.add("Ikot");
          terminal.add(new TerminalObject("CHK", jeep, 14.657783, 121.062438));
          jeep.clear();

          jeep.add("Katipunan");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Toki");
          jeep.add("SM");
          terminal.add(new TerminalObject("AS", jeep, 14.653800, 121.068071));
          jeep.clear();

          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Vinzons", jeep, 14.653696, 121.072748));
          jeep.clear();

          jeep.add("Ikot");
          terminal.add(new TerminalObject("Vinzons", jeep, 14.654703, 121.073156));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Toki");
          terminal.add(new TerminalObject("CS Library", jeep, 14.648890, 121.068983));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Katipunan");
          jeep.add("MRT");
          jeep.add("Philcoa");
          terminal.add(new TerminalObject("Law", jeep, 14.656812, 121.072803));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Infirmary", jeep, 14.659459, 121.071655));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Kalayaan Dorm", jeep, 14.658867, 121.068458));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("SC", jeep, 14.659438, 121.070336));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Coop", jeep, 14.659386, 121.069209));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Engineering", jeep, 14.657705, 121.068458));
          jeep.clear();

          jeep.add("Toki");
          terminal.add(new TerminalObject("NIP", jeep, 14.649692, 121.073683));
          jeep.clear();

          jeep.add("Toki");
          terminal.add(new TerminalObject("Math", jeep, 14.648270, 121.072203));
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Toki");
          terminal.add(new TerminalObject("EEEI", jeep, 14.649951, 121.068673));
          jeep.clear();

          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Roxas Ave", jeep, 14.653577, 121.065185));
          jeep.clear();

          jeep.add("MRT");
          jeep.add("Ikot");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.add(new TerminalObject("Oblation", jeep, 14.654926, 121.064273));
          jeep.clear();

          jeep.add("Ikot");
          terminal.add(new TerminalObject("Vanguard", jeep, 14.657677, 121.064970));
          jeep.clear();
     }

     //Haversine Code taken from RosettaCode
     public static double haversine(double lat1, double long1, double lat2, double long2) {
          double latd = Math.toRadians(lat1 - lat2);
          double longd = Math.toRadians(long1 - long2);
          lat1 = Math.toRadians(lat1);
          lat2 = Math.toRadians(lat2);

          double a = Math.sin(latd / 2) * Math.sin(latd / 2) * Math.sin(latd / 2) + Math.sin(longd / 2) * Math.sin(longd / 2) * Math.cos(lat1) * Math.cos(lat2);
          double c = 2 * Math.asin(Math.sqrt(a));
          return (radius * c) / 1000.0;
     }

     //Get number of steps from meters
     //Value from ConvertUnits.com and other online converters
     public static int steps(double meters) {
          return (int)(meters * 1.31233595801);
     }
}
