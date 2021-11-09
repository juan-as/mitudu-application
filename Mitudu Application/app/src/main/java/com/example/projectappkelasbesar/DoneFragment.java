package com.example.projectappkelasbesar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DoneFragment extends Fragment {

    RecyclerView recyclerView;

    public DoneFragment() {
        // Required empty public constructor
    }
    public static DoneFragment newInstance() {
        DoneFragment fragment = new DoneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        recyclerView = view.findViewById(R.id.rvToDoList);

        DoneAdapter adapter = new DoneAdapter();
        adapter.setToDoClasses(dbHelper.getProgressList("DONE"));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return view;
    }
}