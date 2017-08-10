package com.example.nourelislamsaidi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nourelislamsaidi.adapters.LangAdapter;
import com.example.nourelislamsaidi.listeners.LanguesRecyclerViewListener;
import com.example.nourelislamsaidi.numbertowords.R;

import java.util.ArrayList;
import java.util.List;

public class LangListActivity extends AppCompatActivity {

    private static final String LANGUE = "langue";
    private static final String CURRANCY = "currancy";
    private static final int LANG_LIST = 1;
    private static final int CURRANCY_LIST = 2;
    private static boolean mIsLangue = false;
    private static boolean mIsCurrancy = false;
    private RecyclerView mLangRecyclerView;
    private LangAdapter mAdapter;
    private List<String> mDataList = new ArrayList<>();
    private String textChecked;
    private Activity mActivity;

    @NonNull
    public static Intent newIntent(Activity activity, String langues, int id) {
        Intent intent;
        intent = new Intent(activity, LangListActivity.class);
        switch (id) {
            case LANG_LIST:
                mIsLangue = true;
                intent.putExtra(LANGUE, langues);
                break;

            case CURRANCY_LIST:
                mIsCurrancy = true;
                intent.putExtra(CURRANCY, langues);
                break;
        }
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mActivity = this;
        mLangRecyclerView = (RecyclerView) findViewById(R.id.lang_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLangRecyclerView.setLayoutManager(mLayoutManager);
        mLangRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getExtraIntent();
        mAdapter = new LangAdapter(mDataList, textChecked);
        mLangRecyclerView.setAdapter(mAdapter);

        mLangRecyclerView.addOnItemTouchListener(new LanguesRecyclerViewListener(getBaseContext(), new LanguesRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent;
                if (mDataList.contains("Euro")) {

                    intent = MainActivity.newMainIntent(mActivity, mDataList.get(position), 2, position);
                } else {
                    intent = MainActivity.newMainIntent(mActivity, mDataList.get(position), 1, position);

                }
                startActivity(intent);
            }
        }));

        prepareData();
    }

    private void prepareData() {
        if (mIsLangue) {
            mDataList.add("Français");
            mDataList.add("Arabe");
            mIsLangue = false;
            mAdapter.notifyDataSetChanged();
        }

        if (mIsCurrancy) {
            mDataList.add("Euro");
            mDataList.add("Dollar");
            mDataList.add("Dinar Algérien");
            mDataList.add("Yen Japonais");
            mDataList.add("Franc Suisse");
            mDataList.add("Yuan Renmimbi");
            mDataList.add("Livre Sterling");
            mDataList.add("Lira Turque");
            mDataList.add("Rouble Russe");
            mDataList.add("Roupie Indienne");
            mDataList.add("Réal Brésilien");
            mDataList.add("Rand Sud-africain");
            mIsCurrancy = false;
            mAdapter.notifyDataSetChanged();
        }
    }

    private void getExtraIntent() {
        Intent intent = getIntent();
        String langueChecked = intent.getStringExtra(LANGUE);
        String currancyChecked = intent.getStringExtra(CURRANCY);

        if (langueChecked != null) {
            textChecked = langueChecked;
        }

        if (currancyChecked != null) {
            textChecked = currancyChecked;
        }

    }
}
