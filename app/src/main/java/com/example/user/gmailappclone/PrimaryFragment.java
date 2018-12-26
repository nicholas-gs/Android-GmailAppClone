package com.example.user.gmailappclone;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.gmailappclone.Adapter.PrimaryRVAdapter;
import com.example.user.gmailappclone.Model.Email;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class PrimaryFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DividerItemDecoration dividerItemDecoration;
    private FloatingActionButton floatingActionButton;
    private PrimaryRVAdapter adapter;
    private Handler handler = new Handler();
    private Context context;
    private ArrayList<Email> emailArrayList = new ArrayList<>();
    private static final String databaseUrl = "https://api.jsonbin.io/b/5c223c46412d482eae54bc6a";
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.primary_layout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        initializeWidgets(view);

        requestQueue = Volley.newRequestQueue(getContext());
        parseJsonToAdapter(databaseUrl);

        adapter = new PrimaryRVAdapter(getContext(), emailArrayList);
        recyclerView.setAdapter(adapter);

        setSwipeToRefresh();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: Write a new email
            }
        });
    }

    private void parseJsonToAdapter(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + jsonObject);
                                int emailId = jsonObject.getInt("emailId");
                                boolean isImportant = jsonObject.getBoolean("isImportant");
                                boolean isRead = jsonObject.getBoolean("isRead");
                                String imageUrl = jsonObject.getString("imageUrl");
                                String from = jsonObject.getString("from");
                                String subject = jsonObject.getString("subject");
                                String message = jsonObject.getString("message");
                                String timeStamp = jsonObject.getString("timestamp");

                                Email email = new Email(emailId, isRead, isImportant, imageUrl, from, subject,
                                        message, timeStamp);

                                emailArrayList.add(email);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        delaySwipeRefresh();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                delaySwipeRefresh();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void initializeWidgets(View view) {
        recyclerView = view.findViewById(R.id.primary_fragment_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.primary_fragment_swiprerefreshlayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorTimestamp),
                ContextCompat.getColor(context, R.color.colorIconTintSelected));
        floatingActionButton = view.findViewById(R.id.primary_fragment_fab);

        dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    /**
     * Add functionality to the swipeRefreshLayout
     */
    private void setSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emailArrayList.clear();
                adapter.notifyDataSetChanged();
                parseJsonToAdapter(databaseUrl);
            }
        });
    }

    /**
     * Delay the swipe refresh icon from disappearing
     */
    private void delaySwipeRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }
}
