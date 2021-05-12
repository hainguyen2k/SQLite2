package com.example.a18020331_nguyenxuanhai_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listview;
    ArrayAdapter arrayAdapter;
    public List<Place> places;
    public EditText edtInput;
    public Button btnadd;
    public ImageButton ibtndel, ibtnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
//        Place place1 = new Place(1,"Đà Lạt");
//        Place place2 = new Place(2,"Buôn Mê Thuột");
//        Place place3 = new Place(3,"Cần Thơ");
//        Place place4 = new Place(4,"Phú Quốc");
//        Place place5 = new Place(5,"Lý Sơn");
//        Place place6 = new Place(6,"Cần Giờ");
//        Place place7 = new Place(7,"Côn Đảo");
//        Place place8 = new Place(8,"Vũng Tàu");
//        databaseHandler.addPlace(place1);
//        databaseHandler.addPlace(place2);
//        databaseHandler.addPlace(place3);
//        databaseHandler.addPlace(place4);
//        databaseHandler.addPlace(place5);
//        databaseHandler.addPlace(place6);
//        databaseHandler.addPlace(place7);
//        databaseHandler.addPlace(place8);

        places = databaseHandler.getAllPlace();
        listview = findViewById(R.id.listview);
        edtInput = findViewById(R.id.edtinput);
        arrayAdapter = new ArrayAdapter(this, R.layout.items, R.id.tvName, places);
        listview.setAdapter(arrayAdapter);


        btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String name = edtInput.getText().toString();
                for (int i = 0; i < places.size(); i++) {
                    if (places.get(i).getName() != name && edtInput.length() != 0) {
                        Place place = new Place(name);
                        databaseHandler.addPlace(place);
                        arrayAdapter.add(place);
                        Toast.makeText(MainActivity.this, "Thêm place thành công!!!", Toast.LENGTH_SHORT).show();
                        flag++;
                        break;
                    }
                    else
                        flag = 0;
                }
                if(flag == 0)
                    Toast.makeText(MainActivity.this, "Tên place không hợp lệ hoặc bị trùng!!!", Toast.LENGTH_SHORT).show();
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ibtndel = (ImageButton) view.findViewById(R.id.ibtndel);
                ibtndel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHandler.deleteTravel(places, position);
                        Toast.makeText(MainActivity.this, "Xoá place thành công!!!", Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                });
                ibtnupdate = view.findViewById(R.id.ibtnupdate);
                String name = edtInput.getText().toString();
                ibtnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHandler.updateTravel(name,places, position);
                        recreate();
                    }
                });
            }
        });

    }
}