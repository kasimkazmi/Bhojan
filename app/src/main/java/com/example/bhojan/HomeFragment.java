package com.example.bhojan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class HomeFragment extends Fragment {
    private TextView greeting;
    private TextView loginTime;
    private TextView personalizegreeting;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        greeting = rootView.findViewById(R.id.greeting);
        loginTime = rootView.findViewById(R.id.login_time);
        personalizegreeting = rootView.findViewById(R.id.personalizegreeting);

        // Initialize the search view
        searchView = rootView.findViewById(R.id.search_view);

        // Get the current user from Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Check if the user is not null
        if (user != null) {
            // Get the username from the user object
            String username = user.getDisplayName();

            // Display the username and login time in the UI

            // Set the greeting text with the username
            greeting.setText("Greetings " + username + "!");

            // Set the login time text with the login time
            this.loginTime.setText(new SimpleDateFormat("EEEE, MMM d", Locale.getDefault()).format(new Date()));

            // Set the personalized greeting text based on the time of day and meal type
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            String mealType = "";
            if (hour >= 0 && hour < 12) {
                mealType = "breakfast";
            } else if (hour >= 12 && hour < 16) {
                mealType = "lunch";
            } else if (hour >= 16 && hour < 20) {
                mealType = "dinner";
            } else {
                mealType = "snack";
            }

            String personalizedGreetingText = "";
            if (mealType.equals("breakfast")) {
                personalizedGreetingText = "What's for breakfast today?";
            } else if (mealType.equals("lunch")) {
                personalizedGreetingText = "What's for lunch today?";
            } else if (mealType.equals("dinner")) {
                personalizedGreetingText = "What's for dinner tonight?";
            } else {
                personalizedGreetingText = "What's a good snack for now?";
            }

            personalizegreeting.setText(personalizedGreetingText);

        } else {
            // Log a message to show that the user is null
            Log.d("HomeFragment", "User is null");
        }

        // Set up a search query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query changes
                return false;
            }
        });

        return rootView;
    }
}