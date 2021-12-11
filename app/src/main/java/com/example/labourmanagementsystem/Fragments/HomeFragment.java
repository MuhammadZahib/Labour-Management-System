package com.example.labourmanagementsystem.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labourmanagementsystem.Adapters.RVAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rvs;
    ArrayList<PojoModel> list;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        rvs = view.findViewById(R.id.rvs);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        rvs.setLayoutManager(gridLayoutManager);
        ArrayList<PojoModel> list= new ArrayList<>();

        list.add(new PojoModel(R.drawable.broom,"Cleaning/صفائی"));
        list.add(new PojoModel(R.drawable.engineer,"Engineer/انجینئر"));
        list.add(new PojoModel(R.drawable.salon,"Beauty/خوبصورتی"));
        list.add(new PojoModel(R.drawable.hook,"Construction/تعمیر"));
        list.add(new PojoModel(R.drawable.sewing,"Dress Maker/درزی"));
        list.add(new PojoModel(R.drawable.box,"Packaging/پیکیجنگ"));
        list.add(new PojoModel(R.drawable.event,"Event Services/پروگرام "));
        list.add(new PojoModel(R.drawable.padlock,"Security/سیکیورٹی"));
        list.add(new PojoModel(R.drawable.lamp,"Furniture/Fittings"));
        list.add(new PojoModel(R.drawable.lesson,"Lessons/اسباق"));

        RVAdapter adapter= new RVAdapter(list);
        rvs.setAdapter(adapter);


        return view;
    }
}