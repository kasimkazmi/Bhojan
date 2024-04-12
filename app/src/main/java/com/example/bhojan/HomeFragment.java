package com.example.bhojan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FirebaseUser user;
    private FirestoreRecyclerOptions<Recipe> options;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
    private List<Recipe> recipeList;
    private RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);



           // Get the user from Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Check if the user is not null
        if (user != null) {
            // Get the username from the user object
            String username = user.getDisplayName();

            // Display the username and login time in the UI
            TextView greeting = rootView.findViewById(R.id.greeting_textView);
            TextView loginTime = rootView.findViewById(R.id.login_time_textView);
            TextView personalizegreeting = rootView.findViewById(R.id.personalized_greeting_textView);

            // Set the greeting text with the username
            greeting.setText("Greetings " + username + "!");

            // Set the login time text with the login time
            loginTime.setText(new SimpleDateFormat("EEEE, MMM d", Locale.getDefault()).format(new Date()));

            // Set the personalized greeting text based on the time of day and meal type
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            String mealType;
            if (hour < 12) {
                mealType = "breakfast";
            } else if (hour < 17) {
                mealType = "lunch";
            } else if (hour < 20) {
                mealType = "dinner";
            } else {
                mealType = "snack";
            }

            String personalizedGreetingText;
            switch (mealType) {
                case "breakfast":
                    personalizedGreetingText = "What's for breakfast today?";
                    break;
                case "lunch":
                    personalizedGreetingText = "What's for lunch today?";
                    break;
                case "dinner":
                    personalizedGreetingText = "What's for dinner tonight?";
                    break;
                default:
                    personalizedGreetingText = "What's a good snack for now?";
                    break;
            }

            personalizegreeting.setText(personalizedGreetingText);

        } else {
            // Log a message to show that the user is null
            Log.d("HomeFragment", "User is null");
        }


        return rootView;
    }


}