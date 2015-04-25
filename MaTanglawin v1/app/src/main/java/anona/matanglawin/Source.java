package anona.matanglawin;

import java.util.ArrayList;

/**
 * Created by Z370 on 4/25/2015.
 */
public class Source {
     String source;
     ArrayList<Paths> terminals;

     public Source(){

     }

     public Source(String s){
          source = s;
          terminals = new ArrayList<Paths>();
     }

     public void addPaths(String term, String route){
          Paths p = new Paths(term,route);
          terminals.add(p);
     }
}
