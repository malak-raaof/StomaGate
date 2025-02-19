package com.example.finalproject.LukaGenerated.SearchStuff.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.LukaGenerated.Generic.LocalDataSource;
import com.example.finalproject.LukaGenerated.Generic.MealRepository;
import com.example.finalproject.LukaGenerated.Generic.Network.RemoteDataSource;
import com.example.finalproject.LukaGenerated.HomeStuff.RandomMealAdapter;
import com.example.finalproject.LukaGenerated.RandomMeal;
import com.example.finalproject.LukaGenerated.SearchStuff.Interfaces.SearchInterface;
import com.example.finalproject.LukaGenerated.SearchStuff.Interfaces.SearchSectionInterface;
import com.example.finalproject.LukaGenerated.SearchStuff.OnMealClickListener;
import com.example.finalproject.LukaGenerated.SearchStuff.Presenters.SearchPresenter;
import com.example.finalproject.LukaGenerated.SearchStuff.Presenters.SearchSectionPresenter;
import com.example.finalproject.LukaGenerated.SearchStuff.SearchObject;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;


public class SearchSectionFragment extends Fragment implements SearchSectionInterface, OnMealClickListener {


    RecyclerView searchSectionReckView;
    RandomMealAdapter myAdapter;
    SearchSectionPresenter presenter;
    EditText searchSectionInput;
    TextView txtSectionTitle;
    View view;
    SearchObject searchObject;

    public SearchSectionFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_section, container, false);
        searchObject = SearchSectionFragmentArgs.fromBundle(getArguments()).getSearchObject();
        presenter = new SearchSectionPresenter(MealRepository.getInstance(RemoteDataSource.getInstance(),
                        LocalDataSource.getInstance(getContext())), (SearchSectionInterface) view);

        presenter.getAllMeals(searchObject);

        txtSectionTitle =view.findViewById(R.id.txtSectionTitle);
        searchSectionInput = view.findViewById(R.id.searchSectionInput);
        txtSectionTitle.setText(searchObject.getItemName() +"'s Meals");
        searchSectionReckView = view.findViewById(R.id.searchSectionReckView);

        searchSectionReckView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        searchSectionReckView.setHasFixedSize(true);
        myAdapter = new RandomMealAdapter(getActivity(), new ArrayList<>(), this,false);
        searchSectionReckView.setAdapter(myAdapter);

        searchSectionInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchByMeal(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }



    @Override
    public void onMealClick(RandomMeal meal) {
        Navigation.findNavController(view).navigate(SearchSectionFragmentDirections.actionSearchSectionFragmentToFoodInfoFragment(meal));

    }

    @Override
    public void showMeals(List<RandomMeal> mealList) {
        myAdapter.updateData((ArrayList<RandomMeal>) mealList);
    }

}