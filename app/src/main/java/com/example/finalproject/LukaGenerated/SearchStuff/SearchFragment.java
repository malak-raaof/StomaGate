package com.example.finalproject.LukaGenerated.SearchStuff;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.AreaAdapter;
import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.CategoryAdapter;
import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.IngredientAdapter;
import com.example.finalproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


public class SearchFragment extends Fragment {

    private EditText searchInput;
    private TextView resultsView;
    ChipGroup chipGroup;
    private Chip chipIngredient,chipArea,chipCategory;
    private RecyclerView searchReckView;
    CategoryAdapter categoryAdapter;

    IngredientAdapter ingredientAdapter;

    AreaAdapter areaAdapter;

    GridLayoutManager layoutManager;

    SearchPresenter searchPresenter;

    public static final String TAG = "SearchFrag";


    List<String> Names = Arrays.asList(
            "Abdelaziz Zizo", "Abdelrahman Atia", "Abdelrahman Kamel", "Ebram",
            "Adham Biko", "Saad", "Ali", "Aliaa", "Ayat", "Eman Shico",
            "Habiba Bibo", "Jailan gogo", "Malak", "Mario", "Menna",
            "Tag", "Nermeen", "Nour Agami", "Nourhan Noura",
            "Youssef Abdelkader Qatar", "Abo Fayad", "Youssef Leader"
    );


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        searchInput = view.findViewById(R.id.searchInput);
        resultsView = view.findViewById(R.id.resultsView);


        searchReckView = view.findViewById(R.id.searchReckView);
        chipGroup = view.findViewById(R.id.chipGroup);
        layoutManager = new LinearLayoutManager(getContext());
        //LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //oldlist = Arrays.asList(new MobileData().getAllMobiles());
        //adapter = new MyRecyclerAdapter(this, oldlist);
        searchReckView.setLayoutManager(layoutManager);
        searchReckView.setAdapter(categoryAdapter);

        setupFilterChips();

        Observable<String> searchObservable = Observable.create(emitter -> {
            searchInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        });

        Disposable disposable = searchObservable
                .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after typing stops
                .distinctUntilChanged() // Ignore duplicates
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::filterNames) // Apply filtering logic
                .subscribe(resultsView::setText); // Display results

        return view;
    }


    private String filterNames(String query) {
        if (query.isEmpty()) return ""; // Clear text area if empty

        return Names.stream()
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.joining("\n")); // Join results with line breaks
    }

}