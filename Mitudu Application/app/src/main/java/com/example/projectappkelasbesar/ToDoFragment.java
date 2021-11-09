package com.example.projectappkelasbesar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

public class ToDoFragment extends Fragment {

    RecyclerView recyclerView;

    public ToDoFragment() {
        // Required empty public constructor
    }
    public static ToDoFragment newInstance() {
        ToDoFragment fragment = new ToDoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        recyclerView = view.findViewById(R.id.rvToDoList);

        ToDoAdapter adapter = new ToDoAdapter(getActivity());
        adapter.setToDoClasses(dbHelper.getProgressList("PROGRESS"));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        ToDoAdapter adapter = new ToDoAdapter(getActivity());
        adapter.setToDoClasses(dbHelper.getProgressList("PROGRESS"));
        adapter.notifyDataSetChanged();
    }
}