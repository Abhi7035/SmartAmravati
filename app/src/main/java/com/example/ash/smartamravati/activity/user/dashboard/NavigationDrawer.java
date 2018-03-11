package com.example.ash.smartamravati.activity.user.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ash.smartamravati.R;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.AdminContactFragment;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.CallCenterFragment;
import com.example.ash.smartamravati.activity.other.CircleTransform;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.GalleryFragment;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.HomeFragment;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.NotificationFragment;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.OnlineAppServicesStatusFragment;
import com.example.ash.smartamravati.activity.user.login.Page3;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.ServicesFragment;
import com.example.ash.smartamravati.activity.user.dashboard.sidemenu.SettingFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FirebaseAuth firebaseAuth;

    StorageReference mStorageRef;

    private  String name;
    private  String email;
    private  String userID;
    private  String imageURL;
    NavigationView navigationView =null;
    Toolbar toolbar = null;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        setContentView(R.layout.activity_navigation_drawer);

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_Home, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

            View hView =  navigationView.getHeaderView(0);
            final TextView username = (TextView)hView.findViewById(R.id.textView);
            final TextView userEmail = (TextView)hView.findViewById(R.id.textView1);
            final ImageView userProfileImage = (ImageView)hView.findViewById(R.id.imageView);


            DatabaseReference mRootRefer = FirebaseDatabase.getInstance().getReference();
            DatabaseReference mChildrefer = mRootRefer.child("Users").child(userID).child("user Name");
            mChildrefer.keepSynced(true);
            DatabaseReference mChildrefer1 = mRootRefer.child("Users").child(userID).child("user Email");
            mChildrefer1.keepSynced(true);
            DatabaseReference mChildrefer2 = mRootRefer.child("Users").child(userID).child("Profile pic url");

            mChildrefer.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    name = dataSnapshot.getValue().toString();
                    username.setText(name);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mChildrefer1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    email = dataSnapshot.getValue().toString();
                    userEmail.setText(email);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mChildrefer2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    imageURL= dataSnapshot.getValue().toString();
                    Glide.with(getApplicationContext()).load(imageURL).bitmapTransform(new CircleTransform(getApplicationContext())).into(userProfileImage);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





            navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Page3.class));


            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.nav_notification) {

            NotificationFragment fragment = new NotificationFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_gallery) {

            GalleryFragment fragment = new GalleryFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_services) {

            OnlineAppServicesStatusFragment fragment = new OnlineAppServicesStatusFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.list_Services) {

            ServicesFragment fragment = new ServicesFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.action_settings) {

            SettingFragment fragment = new SettingFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_call) {

            CallCenterFragment fragment = new CallCenterFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_mail) {

            AdminContactFragment fragment = new AdminContactFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();

        }else {

            HomeFragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Home, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
