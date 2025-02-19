package com.example.finalproject.LukaGenerated.SearchStuff.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.finalproject.LukaGenerated.Generic.LocalDataSource;
import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.Generic.Network.RemoteDataSource;
import com.example.finalproject.LukaGenerated.Pojos.CategoryPojo.Category;
import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.AreaAdapter;
import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.CategoryAdapter;
import com.example.finalproject.LukaGenerated.SearchStuff.Adapters.IngredientAdapter;
import com.example.finalproject.LukaGenerated.SearchStuff.Interfaces.SearchInterface;
import com.example.finalproject.LukaGenerated.SearchStuff.Presenters.SearchPresenter;
import com.example.finalproject.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    EditText searchInput;

    //private TextView resultsView;
    ChipGroup chipGroup;
    Chip chipIngredient,chipArea,chipCategory;
    RecyclerView searchReckView;
    CategoryAdapter categoryAdapter;

    IngredientAdapter ingredientAdapter;

    AreaAdapter areaAdapter;

    GridLayoutManager layoutManager;

    SearchPresenter searchPresenter;

    String filterBy;

    View view;
    Context context;

    SearchInterface searchInterface;

    public static final String TAG = "SearchFrag";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        searchPresenter = new SearchPresenter(searchInterface,
                MealRepository.getInstance(RemoteDataSource.getInstance(),
                        LocalDataSource.getInstance(getContext())));


        searchInput = view.findViewById(R.id.searchInput);
        chipGroup = view.findViewById(R.id.chipGroup);


        searchReckView = view.findViewById(R.id.searchReckView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        searchReckView.setLayoutManager(layoutManager);

        //LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //oldlist = Arrays.asList(new MobileData().getAllMobiles());
        //adapter = new MyRecyclerAdapter(this, oldlist);
        //resultsView = view.findViewById(R.id.resultsView);
        //searchReckView.setAdapter(categoryAdapter);

        categoryAdapter = new CategoryAdapter(context,new ArrayList<>());
        searchReckView.setAdapter(categoryAdapter);

        setupFilterChips();

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                searchPresenter.searchBySection(filterBy,text);
            }

            @Override
            public void afterTextChanged(Editable s) {}


        });

//        Disposable disposable = searchObservable
//                .debounce(300, TimeUnit.MILLISECONDS) // Wait 300ms after typing stops
//                .distinctUntilChanged() // Ignore duplicates
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(this::filterNames) // Apply filtering logic
//                .subscribe(resultsView::setText); // Display results

        return view;
    }


    private void setupFilterChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View child = chipGroup.getChildAt(i);

            if (child instanceof Chip) {  //
                Chip chip = (Chip) child;
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (chip.getText().toString().equals("Category")) {
                                filterBy = "Category";
                                searchPresenter.getCategory();
                            } else if (chip.getText().toString().equals("Ingredient")) {
                                filterBy = "Ingredient";
                                searchPresenter.getIngredient();
                            } else {
                                filterBy = "Area";
                                searchPresenter.getArea();
                            }
                        }
                    }
                });
            }
        }
    }

    public void updateCategoryList(List<Category> categories) {
        categoryAdapter.setList(categories); // 🔹 Make sure this method exists in your adapter
    }




}