package com.example.bhojan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhojan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private FirebaseUser user;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create the static data for the RecyclerView
        List<CardItem> cardItems = new ArrayList<>();
        cardItems.add(new CardItem(R.drawable.thali, "Thali Set"));
        cardItems.add(new CardItem(R.drawable.soup, "Soup Set"));

        cardItems.add(new CardItem(R.drawable.chicken, "Chicken"));

        cardItems.add(new CardItem(R.drawable.fries, "Fries Set"));
        cardItems.add(new CardItem(R.drawable.pasta, "Pasta"));
        cardItems.add(new CardItem(R.drawable.momo, "Momos"));
        cardItems.add(new CardItem(R.drawable.noodles, "Noodles "));
        cardItems.add(new CardItem(R.drawable.pakoda, "Pakoda"));


        // Add more items as needed

        // Create and set the adapter for the RecyclerView
        adapter = new MyAdapter(cardItems);
        recyclerView.setAdapter(adapter);
        // Get the user from Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Check if the user is not null
        if (user != null) {
            // Get the username from the user object
            String username = user.getDisplayName();

            // Display the username and login time in the UI
            TextView greeting = rootView.findViewById(R.id.greeting_textView);
            TextView loginTime = rootView.findViewById(R.id.login_time_textView);
            TextView personalizeGreeting = rootView.findViewById(R.id.personalized_greeting_textView);

            // Set the greeting text with the username
            greeting.setText("Greetings " + username + "!");

            // Set the login time text with the login time
            loginTime.setText(new SimpleDateFormat("EEEE, MMM d", Locale.getDefault()).format(new Date()));

// Set the personalized greeting text based on the time of day and meal type
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            String personalizedGreetingText;

            if (hour >= 5 && hour < 12) {
                personalizedGreetingText = "Good morning! What's for breakfast today?";
            } else if (hour >= 12 && hour < 17) {
                personalizedGreetingText = "Good afternoon! What's for lunch today?";
            } else if (hour >= 17 && hour < 20) {
                personalizedGreetingText = "Good evening! What's for dinner tonight?";
            } else {
                personalizedGreetingText = "Good evening! What's a good snack for now?";
            }

// Update the greeting text based on the meal type
            personalizeGreeting.setText(personalizedGreetingText);
        } else {
            // Log a message to show that the user is null
            Log.d("HomeFragment", "User is null");
        }

        return rootView;
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<CardItem> cardItems;

        public MyAdapter(List<CardItem> cardItems) {
            this.cardItems = cardItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommanded_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CardItem cardItem = cardItems.get(position);
            holder.imageView.setImageResource(cardItem.getImageResId());
            holder.textView.setText(cardItem.getText());
        }

        @Override
        public int getItemCount() {
            return cardItems.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.item_image);
                textView = itemView.findViewById(R.id.item_text);
            }
        }
    }

    private static class CardItem {
        private int imageResId;
        private String text;

        public CardItem(int imageResId, String text) {
            this.imageResId = imageResId;
            this.text = text;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getText() {
            return text;
        }

    }

}
