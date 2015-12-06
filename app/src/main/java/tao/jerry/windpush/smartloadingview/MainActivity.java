package tao.jerry.windpush.smartloadingview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import tao.jerry.windpush.smartloadingview.view.SmartBaseView;


public class MainActivity extends ActionBarActivity {
    SmartBaseView smartBaseView,smartBaseView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smartBaseView = new SmartBaseView(this) {
            @Override
            public int bgImageResource() {
                return R.drawable.testbg2;
            }

            @Override
            public int loadingImageResource() {
                return R.drawable.testbg2;
            }

            @Override
            public int viewWidth() {
                return 200;
            }

            @Override
            public int viewHeight() {
                return 20;
            }
        };
        ViewGroup mShowDiv = (ViewGroup) findViewById(R.id.div_show);
        mShowDiv.addView(smartBaseView);
        smartBaseView2=new SmartBaseView(this) {
            @Override
            public int bgImageResource() {
                return R.drawable.bgtwo;
            }

            @Override
            public int loadingImageResource() {
                return R.drawable.bgtwo;
            }

            @Override
            public int viewWidth() {
                return 200;
            }

            @Override
            public int viewHeight() {
                return 20;
            }
        };
        mShowDiv.addView(smartBaseView2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                while (a < 100) {
                    a++;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = a;
                    MainActivity.this.myHandler.sendMessage(message);
                    if (a==99){
                        a=0;
                    }
                }
            }
        }).start();

    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    smartBaseView.upDateProgress(msg.arg1);
                    smartBaseView2.upDateProgress(msg.arg1);
                    break;
            }
            super.handleMessage(msg);
        }
    };


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
}
