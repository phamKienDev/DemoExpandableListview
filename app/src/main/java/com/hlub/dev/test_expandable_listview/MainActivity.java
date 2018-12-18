package com.hlub.dev.test_expandable_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListview;
    List<String> listDataHeader;
    HashMap<String, List<User>> listDataChilder;
    CustomExpandableListView customExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        customExpandableListView = new CustomExpandableListView(this, listDataHeader, listDataChilder);
        expandableListview.setAdapter(customExpandableListView);

//        //bat su  kien
        click_group();
        click_childer();
//        //dong group
        close_group();
//
//        //open
        open_group();
    }

    private void open_group() {
        expandableListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(MainActivity.this, "open " + listDataHeader.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void close_group() {
        expandableListview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, "close " + listDataHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_childer() {
        expandableListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childerPosition, long l) {
                Toast.makeText(MainActivity.this, listDataChilder.get(listDataHeader.get(groupPosition)).get(childerPosition).getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void click_group() {
        expandableListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Toast.makeText(MainActivity.this, listDataHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void addControl() {
        expandableListview = findViewById(R.id.expandableListview);
        listDataHeader = new ArrayList<>();
        listDataChilder = new HashMap<>();

        listDataHeader.add("Phim tháng 10");
        listDataHeader.add("Phim tháng 11");
        listDataHeader.add("Phim tháng 12");

        List<User> phimthang10 = new ArrayList<>();
        phimthang10.add(new User(1,"Kiên"));
        phimthang10.add(new User(2,"Mạnh"));
        phimthang10.add(new User(3,"Tư"));

//        List<String> phimthang11 = new ArrayList<>();
//        phimthang11.add("tháng 12_01");
//        phimthang11.add("tháng 11_02");
//        phimthang11.add("tháng 11_03");
//
//        List<String> phimthang12 = new ArrayList<>();
//        phimthang12.add("tháng 12_01");
//        phimthang12.add("tháng 12_02");
//        phimthang12.add("tháng 12_03");


        listDataChilder.put(listDataHeader.get(0), phimthang10);
        listDataChilder.put(listDataHeader.get(1), phimthang10);
        listDataChilder.put(listDataHeader.get(2), phimthang10);
    }
}
