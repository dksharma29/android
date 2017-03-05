package app.example.date;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   TextView date;int mYear,mMonth,mDay;String d;
    EditText d1,d2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (TextView) findViewById(R.id.dates);
        d1 = (EditText) findViewById(R.id.date1);
        d2 = (EditText) findViewById(R.id.date2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Date> dates = getDates(d1.getText().toString(), d2.getText().toString());
                    d = "";
                    SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
                    if (dates.size() == 0) {
                        Toast.makeText(getApplicationContext(), "Start date should be less than end date", Toast.LENGTH_SHORT).show();
                    }
                    for (Date dt : dates)
                        d = d + f1.format(dt) + "\n";

                    date.setText("" + d);
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid date format",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
        private static List<Date> getDates(String dateString1, String dateString2)
        {
            ArrayList<Date> dates = new ArrayList<Date>();
            DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");

            Date date1 = null;
            Date date2 = null;

            try {
                date1 = df1 .parse(dateString1);
                date2 = df1 .parse(dateString2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);


            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            while(!cal1.after(cal2))
            {
                dates.add(cal1.getTime());
                cal1.add(Calendar.DATE, 1);
            }
            return dates;
        }
    }

