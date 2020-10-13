package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.events.RemoveFromFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileNeighbourActivity extends AppCompatActivity {

    private static final String TAG = "ProfileNeighbourActivit";
    @BindView(R.id.favButton)
    public FloatingActionButton mFavButton;
    @BindView(R.id.neighbourLocation)
    TextView mAddress;
    @BindView(R.id.neighbourName)
    TextView mName;
    @BindView(R.id.profile_neighbourTitleName)
    TextView mTitleName;
    @BindView(R.id.neighbourPhone)
    TextView mPhone;
    @BindView(R.id.neighbourSocial)
    TextView mSocial;
    @BindView(R.id.aboutMeTextView)
    TextView mAboutMe;
    @BindView(R.id.toolbar2)
    Toolbar mToolbar;
    private Neighbour mNeighbour;
    private int mNeighbourPos;
    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private List<Neighbour> mNeighbours = mApiService.getNeighbours();
    private View.OnClickListener FavListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mNeighbour.getIsFav()) {
                EventBus.getDefault().post(new AddFavoriteEvent(mNeighbour));
                mFavButton.setImageResource(R.drawable.ic_baseline_star_24);
            } else {
                EventBus.getDefault().post(new RemoveFromFavEvent(mNeighbour));
                mFavButton.setImageResource(R.drawable.ic_baseline_star_24_notfav);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_page);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: Profile page created.");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getIncomingIntent();

        mFavButton.setOnClickListener(FavListener);


        mToolbar.setNavigationOnClickListener(v -> {
            this.finish();
        });

        if (mNeighbour.getIsFav()) {
            mFavButton.setImageResource(R.drawable.ic_baseline_star_24);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("Neighbour")) {
            Log.d(TAG, "getIncomingIntent: Intent has been received.");
            mNeighbour = (Neighbour) getIntent().getSerializableExtra("Neighbour");
            setNeighbourName(mNeighbour.getName());
            setNeighbourLocation(mNeighbour.getAddress());
            setNeighbourPhone(mNeighbour.getPhoneNumber());
            setNeighbourAvatar(mNeighbour.getAvatarUrl());
            setNeighbourAbout(mNeighbour.getAboutMe());
            setNeighbourSocial(mNeighbour.getName().toLowerCase());
            mNeighbourPos = getIntent().getIntExtra("Position", 0);
        }

    }

    private void setNeighbourName(String name) {
        Log.d(TAG, "setNeighbourName: Neighbour name has been set from the intent.");
        mName.setText(name);
        mTitleName.setText(name);
    }

    private void setNeighbourLocation(String location) {
        Log.d(TAG, "setNeighbourLocation: Neighbour location has been set from the intent.");
        mAddress.setText(location);
    }

    private void setNeighbourPhone(String phone) {
        Log.d(TAG, "setNeighbourPhone: Neighbour phone has been set from the intent.");
        mPhone.setText(phone);
    }

    private void setNeighbourAbout(String about) {
        Log.d(TAG, "setNeighbourAbout: Neighbour about has been set from the intent.");
        mAboutMe.setText(about);
    }

    private void setNeighbourAvatar(String imageUrl) {
        ImageView profilePic = findViewById(R.id.profilePic);

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(profilePic);
    }

    private void setNeighbourSocial(String social) {
        mSocial.setText("www.facebook.fr/" + social + "");
    }


}
