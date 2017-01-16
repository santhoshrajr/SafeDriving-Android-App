package com.imperium.autobeacon;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class RestrictionList extends ListFragment {

    String[] restriction_names;
    TypedArray restriction_images;
    String[] statues;
    String[] contactType;
    List<RowItem> rowItems;
    ListView mylistview;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_restriction_list, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rowItems = new ArrayList<RowItem>();

        restriction_names = getResources().getStringArray(R.array.Restriction_names);
        restriction_images = getResources().obtainTypedArray(R.array.Restriction_pics);

        for (int i = 0; i < restriction_names.length; i++) {
            RowItem item = new RowItem(restriction_names[i],
                    restriction_images.getResourceId(i, -1));
            rowItems.add(item);
        }

        CustomAdapter adapter = new CustomAdapter(getActivity(), rowItems);
        setListAdapter(adapter);
        restriction_images.recycle();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}