package anona.matanglawin;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
import android.widget.Toast;


public class MTListener implements LocationListener {
     Context context;

     public MTListener(Context context) {
          this.context = context;
     }

     @Override
     public void onLocationChanged(Location location) {
          double latitude = location.getLatitude();
          double longitude = location.getLongitude();

          TerminalValue terminals = new TerminalValue(latitude, longitude);
          terminals.populate();

          String output = latitude + "\n" + longitude;
          Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
     }

     @Override
     public void onProviderEnabled(String gps) {
     }

     @Override
     public void onProviderDisabled(String gps) {
     }

     @Override
     public void onStatusChanged(String gps, int status, Bundle extra){
     }
}
