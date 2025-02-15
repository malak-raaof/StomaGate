package com.example.finalproject.LukaGenerated;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.R;

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