package anona.matanglawin;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements OnClickListener, OnInitListener {

    Context context;
    private TextToSpeech talkie;
    private int MY_DATA_CHECK_CODE = 0;
    ArrayList<Source> sources;

    Source curr;
    int pressctr;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        sources = new ArrayList<Source>();
        populateSource();

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.mainlayout);

        rl.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  curr = new Source();
                  curr = getSource("DCS");
                  speakWords("You are currently at " + curr.source);

                  pressctr = 0;
                  size = curr.terminals.size();

             }
        });



        /*LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new MTListener(getApplicationContext());
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    //speakWords("You pressed Button Down");

                    pressctr++;
                    pressctr = pressctr%size;
                    Paths p = new Paths();
                    p = curr.terminals.get(pressctr);
                    speakWords(p.terminal + p.destination);
                    //Toast toast = Toast.makeText(context,"Pressed the Down Button",Toast.LENGTH_SHORT );
                    //toast.show();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_UP) {
                    //speakWords("You pressed Button Up");

                     pressctr--;
                     pressctr = pressctr%size;
                     Paths p = new Paths();
                     p = curr.terminals.get(pressctr);
                     speakWords(p.terminal + p.destination);
                    //Toast toast = Toast.makeText(context, "Pressed the Up Button", Toast.LENGTH_SHORT);
                    //toast.show();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    //respond to button clicks
    public void onClick(View v) {

        /*get the text entered
        EditText enteredText = (EditText)findViewById(R.id.enter);
        String words = enteredText.getText().toString();
        speakWords(words);*/
    }

    //speak the user text
    private void speakWords(String speech) {

        //CharSequence[] speechch = String[] {speech};
        //char seq, int, bundle
        talkie.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                talkie = new TextToSpeech(this, this);
            } else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if (talkie.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                talkie.setLanguage(Locale.US);
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();

        }
    }

    public void populateSource(){
         Source a = new Source("DCS");
         a.addPaths("NIGS","Ikot");
         a.addPaths("NIGS","Toki");
         a.addPaths("FC","LRT/Katipunan");
         a.addPaths("FC","SM North");
         a.addPaths("FC","MRT/Pantranco");

         Source b = new Source("CHK");
         b.addPaths("CHK","Ikot");
         b.addPaths("CHK","Toki");

         Source c = new Source("Math Building");
         c.addPaths("Math Building","Toki");
         c.addPaths("NIGS","Ikot");
         c.addPaths("NIP","Katipunan");

         sources.add(a);
         sources.add(b);
         sources.add(c);
    }

    public Source getSource(String src){
         for(int i=0; i<sources.size();i++){
              if(sources.get(i).source.equals(src)){
                   return sources.get(i);
              }
         }
         Source sc = new Source();
         return sc;
    }
}
