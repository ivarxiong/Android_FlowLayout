package demo.jaina.textandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.core.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowLayout flowLayout1 = findViewById(R.id.flow_layout_1);

        FlowLayout flowLayout2 = findViewById(R.id.flow_layout_2);
        flowLayout2.setFlowGravity(Gravity.TOP|Gravity.RIGHT);



        FlowLayout flowLayout3 = findViewById(R.id.flow_layout_3);
        flowLayout3.setFlowGravity(Gravity.LEFT|Gravity.BOTTOM);


        FlowLayout flowLayout4 = findViewById(R.id.flow_layout_4);
        flowLayout4.setFlowGravity(Gravity.RIGHT|Gravity.BOTTOM);

    }
}
