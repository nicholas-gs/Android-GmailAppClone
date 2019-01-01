package com.example.user.gmailappclone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.gmailappclone.MainActivity;
import com.example.user.gmailappclone.R;

public class NewEmailFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    public drawerLockerListener listener;
    private Context context;

    private ImageView dropDownImageView;
    private LinearLayout ccContainer;
    private LinearLayout bccContainer;
    private View dividerOne, dividorTwo;

    public interface drawerLockerListener {
        void lockDrawer(boolean locked);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((NewEmailFragment.drawerLockerListener) getActivity()).lockDrawer(true);
        return inflater.inflate(R.layout.new_email_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = ((MainActivity) getActivity()).findViewById(R.id.main_toolbar);
        context = getContext();
        dropDownImageView = view.findViewById(R.id.new_email_fragment_dropdown);
        ccContainer = view.findViewById(R.id.new_email_cc_container);
        dividerOne = view.findViewById(R.id.new_email_divider_one);
        dividorTwo = view.findViewById(R.id.new_email_divider_two);
        bccContainer = view.findViewById(R.id.new_email_bcc_container);

        dropDownImageView.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.new_email_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(context, "Back pressed", Toast.LENGTH_SHORT).show();
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.new_email_addfromcontacts:
                Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_email_confidentialmode:
                Toast.makeText(context, "4", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_email_fragment_dropdown:
                ccContainer.setVisibility(View.VISIBLE);
                bccContainer.setVisibility(View.VISIBLE);
                dividerOne.setVisibility(View.VISIBLE);
                dividorTwo.setVisibility(View.VISIBLE);
        }
    }
}
