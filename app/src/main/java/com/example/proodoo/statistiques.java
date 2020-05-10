package com.example.proodoo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class statistiques extends AppCompatActivity {
    PieChartView pieChartView,pieChartView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

        pieChartView = findViewById(R.id.chart);

        List  pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.BLUE).setLabel("M1: 10 dn"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("M2: 4 dn"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("M3: 18 dn"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("M4: 28 dn"));

        PieChartData  pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Vente Art1").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

        pieChartView2 = findViewById(R.id.chart2);

        List  pieData2 = new ArrayList<>();
        pieData2.add(new SliceValue(25, Color.BLUE).setLabel("M1: 10 dn"));
        pieData2.add(new SliceValue(15, Color.GRAY).setLabel("M2: 4 dn"));
        pieData2.add(new SliceValue(30, Color.RED).setLabel("M3: 18 dn"));
        pieData2.add(new SliceValue(40, Color.MAGENTA).setLabel("M4: 28 dn"));

        PieChartData  pieChartData2 = new PieChartData(pieData2);
        pieChartData2.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData2.setHasCenterCircle(true).setCenterText1("Vente Art2").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView2.setPieChartData(pieChartData2);
    }
}
