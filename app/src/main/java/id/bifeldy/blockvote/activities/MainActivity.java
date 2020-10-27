package id.bifeldy.blockvote.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import id.bifeldy.blockvote.R;
import id.bifeldy.blockvote.helpers.LangApp;

public class MainActivity extends LangApp {

    // Appbar, Toolbar, Drawer
    private NavigationView navigationView;
    private NavController navController;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Top Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Side menu navigation
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_gallery,
            R.id.nav_slideshow,
            R.id.nav_ethereum
        ).setOpenableLayout(drawer).build();

        // Navigation controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // -- Custom functions
        appRun();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle action bar menu item
        if (id == R.id.action_main_help) {
        } else if (id == R.id.action_main_about) {
            finish();
        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    public void appRun() {

        // Floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action [MAIN_ACTIVITY]", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        });

        // Drawer header
        View header = navigationView.getHeaderView(0);
        ImageView userImg = header.findViewById(R.id.nav_user_img);
        TextView userName = header.findViewById(R.id.nav_user_name);
        TextView userEmail = header.findViewById(R.id.nav_user_email);

        // Drawer menu items
        Menu menu = navigationView.getMenu();
        MenuItem misc_ethereum = menu.findItem(R.id.nav_ethereum);

        // Dummy Data
        userName.setText("Basilius Bias Astho Christyono");
        userEmail.setText("bifeldy@gmail.com");
        misc_ethereum.setTitle("0 WEI");
    }
}
