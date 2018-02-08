package tester.inlogic.sk.supertester2000;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
        button = findViewById(R.id.button);

        addListenerOnbutton();


        startService(new Intent(this, TestService.class));
    }


    public void addListenerOnbutton() {

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("sk.inlogic.jewelbubblesmania");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }

                pause1(7000);
                //Toast.makeText(getApplicationContext(), "Waiting over", Toast.LENGTH_SHORT).show();

                int[] posForPress = getScreenResolution(getApplicationContext());

                pause1(3000);
                try {
                    runShellCommand("adb shell input tap" + 1536/2 + " " + 2048/2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //simulatepress(1536 / 2, 2048 / 20 + 30, arg0);

//                Toast.makeText(getApplicationContext(), posForPress[0] + " " + posForPress[1],
//                        Toast.LENGTH_SHORT).show();

//                for (int i = 0; i < 300; i++) {
//                    pause1(100);
//                    int x = rand.nextInt(posForPress[0]) + 1;
//                    int y = rand.nextInt(posForPress[1]) + 1;
//                    System.out.println("clicked " + "X:" + x + " Y:" + y);
//                }


            }
        });
    }


    public void pause1(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {

        }
    }

    public void simulatepress(long x, long y, View view) {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;
        int metaState = 0;

        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
        );

        view.dispatchTouchEvent(motionEvent);
        //Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT).show();
    }

    private static int[] getScreenResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return new int[]{width, height};
    }

    private void runShellCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }


}
