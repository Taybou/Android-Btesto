package com.example.nourelislamsaidi.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nourelislamsaidi.numbertowords.R;
import com.example.nourelislamsaidi.utils.Utils;
import com.example.nourelislamsaidi.views.LanguageBtnView;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.nourelislamsaidi.activities.LangListActivity.newIntent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    private static final String LANGUE = "langue";
    private static final String CURRANCY = "currancy";
    private static final String SHARED_PREF_KEY = "textKey";
    private static String mLastLanguage;
    private LanguageBtnView mLanguageBtnView;
    private LanguageBtnView mCurrencyBtnView;

    private EditText mEditNumber;
    private TextView mWordAmount;

    private ImageView mClearView;
    private ImageView mTextToSpeechView;
    private ImageView mCopyView;
    private ImageView mSpeechToTextView;

    private TextToSpeech mTextToSpeech;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    Animation mAnim;
    private ClipboardManager mClipboardManager;
    private String mLangueText;
    private String mCurrancyText;

    @NonNull
    public static Intent newMainIntent(Activity activity, String langues, int id) {
        Intent intent = new Intent(activity, MainActivity.class);
        switch (id) {
            case 1:
                intent.putExtra(LANGUE, langues);
                break;
            case 2:
                intent.putExtra(CURRANCY, langues);
                break;

        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        intAmountToSpeech();
        initAnimation();
        initViews();
        setData();

        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_btn:
                mEditNumber.setText("");
                break;
            case R.id.speech_btn:
                onAmountToSpeechClick(mWordAmount.getText().toString());
                break;
            case R.id.copy_btn:
                copyText();
                break;
            case R.id.speech_to_txt_btn:
                promptSpeechInput();
                break;
            case R.id.language_btn:
                startActivity(this, 1);
                break;
            case R.id.currency_btn:
                startActivity(this, 2);

        }
    }

    private void startActivity(Activity activity, int id) {
        Intent intent = null;
        switch (id) {
            case 1:
                intent = newIntent(activity, mLanguageBtnView.getText(), id);
                break;
            case 2:
                intent = newIntent(activity, mCurrencyBtnView.getText(), id);
                break;
        }
        startActivity(intent);
    }

    private void initViews() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_KEY, 0);
        String langueText = sp.getString(LANGUE, "");
        String currancyText = sp.getString(CURRANCY, "");
        mLanguageBtnView = (LanguageBtnView) findViewById(R.id.language_btn);
        mLanguageBtnView.setText(!currancyText.isEmpty() ? langueText : getResources().getString(R.string.default_lang));
        mLanguageBtnView.setOnClickListener(this);

        mCurrencyBtnView = (LanguageBtnView) findViewById(R.id.currency_btn);
        mCurrencyBtnView.setText(!currancyText.isEmpty() ? currancyText : getResources().getString(R.string.default_currancy));
        mCurrencyBtnView.setOnClickListener(this);

        mEditNumber = (EditText) findViewById(R.id.edit_number);
        mWordAmount = (TextView) findViewById(R.id.word_amount);

        mClearView = (ImageView) findViewById(R.id.clear_btn);
        mClearView.setOnClickListener(this);

        mCopyView = (ImageView) findViewById(R.id.copy_btn);
        mCopyView.setOnClickListener(this);

        mTextToSpeechView = (ImageView) findViewById(R.id.speech_btn);
        mTextToSpeechView.setOnClickListener(this);

        mSpeechToTextView = (ImageView) findViewById(R.id.speech_to_txt_btn);
        mSpeechToTextView.setOnClickListener(this);

        animateEditText(true);
        onEditNumberTextChange();
    }

    private void setData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_KEY, 0);
        SharedPreferences.Editor sedt = sp.edit();
        getExtraIntent();
        if (mLangueText != null) {
            if (!mLangueText.isEmpty()) {
                mLanguageBtnView.setText(mLangueText);
                sedt.putString(LANGUE, mLanguageBtnView.getText().toString());
            }
        }
        if (mCurrancyText != null) {
            if (!mCurrancyText.isEmpty()) {
                mCurrencyBtnView.setText(mCurrancyText);
                sedt.putString(CURRANCY, mCurrencyBtnView.getText().toString());
            }
        }
        sedt.commit();
    }

    private void getExtraIntent() {
        Intent intent = getIntent();
        String langueText = intent.getStringExtra(LANGUE);
        String currancy = intent.getStringExtra(CURRANCY);
        mLangueText = langueText;
        mCurrancyText = currancy;
    }

    private void onEditNumberTextChange() {
        mEditNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    animateEditText(false);
                    String frenchWord = Utils.getFrenchWord(s.toString(), mCurrencyBtnView.getText());
                    mWordAmount.setText(frenchWord);
                } else {
                    mWordAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initAnimation() {
        mAnim = new AlphaAnimation(0.0f, 1.0f);
    }

    private void animateEditText(boolean showAnimation) {
        if (showAnimation) {
            mAnim.setDuration(2000);
            mAnim.setStartOffset(20);
            mAnim.setRepeatMode(Animation.REVERSE);
            mAnim.setRepeatCount(10);
            mEditNumber.startAnimation(mAnim);
        } else {
            mEditNumber.clearAnimation();
        }
    }

    private void copyText() {

        if (!mWordAmount.getText().toString().isEmpty()) {
            ClipData clip = ClipData.newPlainText(mEditNumber.getText(), mWordAmount.getText());
            mClipboardManager.setPrimaryClip(clip);
            Toast.makeText(this, R.string.amount_copied, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.no_amount, Toast.LENGTH_SHORT).show();
        }
    }

    private void intAmountToSpeech() {
        mTextToSpeech = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.FRENCH);
                }
            }
        });
    }

    private void onAmountToSpeechClick(String toSpeak) {
        if (!toSpeak.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                mTextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        } else {
            Toast.makeText(this, R.string.no_amount, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        mEditNumber.setText("");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String amount = result.get(0).replaceAll("[^\\d.]", "");
                    if (!amount.isEmpty()) {
                        mEditNumber.setText(amount);
                    } else {
                        Toast.makeText(this, R.string.speech_almount_error, Toast.LENGTH_SHORT).show();
                        mEditNumber.setText("");
                    }
                }
                break;
            }

        }
    }

}
