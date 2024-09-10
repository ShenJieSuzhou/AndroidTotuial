package com.buchuan.picturegallary.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buchuan.picturegallary.R;
import com.buchuan.picturegallary.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    //private List<Person> personList;
    private List<ImageItem> imageList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //binding = FragmentHomeBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化数据
//        personList = new ArrayList<>();
//        for (int i = 0; i < 100; i++)
//        {
//            personList.add(new Person("Item "+(i+1), i+1));
//        }
        // 循环生成 RoboHash URL 并添加到列表
        imageList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String imageUrl = "https://robohash.org/" + i;
            imageList.add(new ImageItem(imageUrl));
        }

        // 设置适配器
        myAdapter = new MyAdapter(imageList, getActivity());
        recyclerView.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}