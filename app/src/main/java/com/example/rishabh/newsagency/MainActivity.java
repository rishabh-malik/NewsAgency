package com.example.rishabh.newsagency;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity {
 int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    EditText week,weekend,maga;
    String weekt,weekendt,magat;
    int weektt,weekendtt,magatt,total;

    int billtoi,billht,billmaga;
    boolean isht,istoi;

    public void calc(View view)
    {

        week=(EditText)findViewById(R.id.editText);
        weekt=week.getText().toString();
        weektt=Integer.parseInt(weekt);

        weekend=(EditText)findViewById(R.id.editText2);
        weekendt=weekend.getText().toString();
        weekendtt=Integer.parseInt(weekendt);

        maga=(EditText)findViewById(R.id.editText3);
        magat=maga.getText().toString();
        magatt=Integer.parseInt(magat);



        CheckBox toi=(CheckBox)findViewById(R.id.checkBox);
        istoi= toi.isChecked();

        CheckBox ht=(CheckBox)findViewById(R.id.checkBox2);
        isht= ht.isChecked();


        if(istoi){
            billtoi=((weektt*5)+(weekendtt*7));
        }

        if(isht){
            billht=((weektt*4)+(weekendtt*6));
        }
        billmaga=magatt*30;
        total= billht+ billtoi+billmaga;
        t=total;
        setContentView(R.layout.activity_main);
        TextView tv=(TextView)findViewById(R.id.textView5);
        tv.setText("Total amount to be paid: "+total);



    }


    public void whatsapp(View view){
        PackageManager pm=getPackageManager();
        try{
            Intent waIntent=new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text="Malik News Agency" +
                    "Your bill for this month is: Rs "+total+"." +
                    " You can pay your bill through paytm." +
                    " To pay paytm on this no. 9810233253";
            PackageInfo info=pm.getPackageInfo("com.whatsapp",PackageManager.GET_META_DATA);
            waIntent.putExtra(Intent.EXTRA_TEXT,text);
            startActivity(Intent.createChooser(waIntent,"Share with"));
        }
        catch (PackageManager.NameNotFoundException e)
        {
            Toast.makeText(this, "Whatsapp not installed", Toast.LENGTH_SHORT).show();

        }
    }

}
