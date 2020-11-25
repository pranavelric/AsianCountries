package com.asia.asiancountries.ui.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asia.asiancountries.R;
import com.asia.asiancountries.data.dataBase.entities.Country;
import com.asia.asiancountries.ui.home.adapter.CountriesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private CountriesAdapter countriesAdapter;
    private List<Country> mCountries;
    private FloatingActionButton fab;
    private LinearLayout emptyLay;
    private Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(getApplication())).get(HomeViewModel.class);
        homeViewModel.netWorkRequest();

        mCountries = new ArrayList<>();
        countriesAdapter = new CountriesAdapter(this, mCountries);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        emptyLay = findViewById(R.id.empty_lay);
        fetch = findViewById(R.id.fetch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this
        ));
        recyclerView.setAdapter(countriesAdapter);


        homeViewModel.getAllCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {

                if (countries.size() > 0) {

                    emptyLay.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    countriesAdapter.setAllCountries(countries);
                    countriesAdapter.notifyDataSetChanged();
                } else {
                    emptyLay.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.netWorkRequest();
            }
        });

    }


    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete all data from local database")
                .setIcon(R.drawable.ic_baseline_delete_24)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        homeViewModel.deleteAllCountries();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }


}