package demo.jaina.textandroid;

import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.Gravity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        //assertEquals("demo.jaina.textandroid", appContext.getPackageName());

        //System.out.println("hello world ");
        //Log.v("xiongyun", "this is test message");
    }

    //@Test
    public void testUri() throws Exception{
        //System.out.println("hello world ");
        Uri uri = Uri.parse("https://etradetest.linkedcare.cn/trade-app/#/orderDetail?lcorderSn=11811150952266596456&lcwaybillcode=201811150001");
        String str = "https://etradetest.linkedcare.cn/trade-app/#/orderDetail?lcorderSn=11811150952266596456&lcwaybillcode=201811150001";
        String sprit_[] = str.split("\\?");
        if(sprit_ != null) {
            for(int i = 0; i < sprit_.length; i++) {
                if(sprit_[i].contains("lcwaybillcode")){
                    String sprit__[] = sprit_[i].split("&");
                    if(sprit__ != null) {
                        for(int j = 0; j < sprit__.length; j++) {
                            if(sprit__[i].contains("lcwaybillcode")){
                                String sprit_r[] = sprit__[j].split("=");
                                if(sprit_r.length > 1) {
                                    Log.v("xiongyun", "code = " + sprit_r[1]);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Test
    public void testGravity() throws Exception{
        int gravity = Gravity.BOTTOM | Gravity.TOP;
        Log.v("xiongyun", "gravity = " + (gravity & Gravity.TOP));
    }

}
