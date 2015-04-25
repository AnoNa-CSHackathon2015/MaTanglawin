package anona.matanglawin;


import java.util.*;

public class TerminalValue {
     static HashMap<String, ArrayList<String>> terminal = new HashMap<>();
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
          ArrayList<String> jeep = new ArrayList<>();

          jeep.add("SM");
          terminal.put("Vinzons SM", jeep);
          jeep.clear();

          jeep.add("MRT");
          terminal.put("Vinzons MRT", jeep);
          jeep.clear();

          jeep.add("Ikot");
          terminal.put("CHK", jeep);
          jeep.clear();

          jeep.add("Katipunan");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Toki");
          jeep.add("SM");
          terminal.put("FC", jeep);
          jeep.clear();

          jeep.add("Katipunan");
          terminal.put("UPIS", jeep);
          jeep.clear();

          jeep.add("Ikot");
          terminal.put("Vinzons Ikot", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Toki");
          terminal.put("CS Library", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Katipunan");
          jeep.add("MRT");
          jeep.add("Philcoa");
          terminal.put("Law", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Infirmary", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Kalayaan Dorm", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("SC", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Coop", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Engineering", jeep);
          jeep.clear();

          jeep.add("Toki");
          terminal.put("NIP", jeep);
          jeep.clear();

          jeep.add("Toki");
          terminal.put("Math", jeep);
          jeep.clear();

          jeep.add("Ikot");
          jeep.add("Toki");
          terminal.put("EEEI", jeep);
          jeep.clear();

          jeep.add("MRT");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Roxas Ave", jeep);
          jeep.clear();

          jeep.add("MRT");
          jeep.add("Ikot");
          jeep.add("Philcoa");
          jeep.add("Katipunan");
          terminal.put("Oblation", jeep);
          jeep.clear();

          jeep.add("Ikot");
          terminal.put("Vanguard", jeep);
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

     public static ArrayList<SuggestionObj> suggestion(double sourceLat, double sourceLong) {
          ArrayList<SuggestionObj> suggestions = new ArrayList<>();
          PlaceMapping map = new PlaceMapping();
          double destLat, destLong;
          String dest;
          int steps;

          Set terminalSet = terminal.entrySet();
          Iterator terminalIter = terminalSet.iterator();
          while (terminalIter.hasNext()) {
               dest = (String)terminalIter.next();
               destLat = map.placeSet.get(dest).get(0);
               destLong = map.placeSet.get(dest).get(1);

               steps = steps(haversine(sourceLat, sourceLong, destLat, destLong));

               if (steps <= 50)
                    suggestions.add(new SuggestionObj(dest, steps));
          }

          return suggestions;
     }
}
