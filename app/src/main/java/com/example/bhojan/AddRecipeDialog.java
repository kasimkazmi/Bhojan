package com.example.bhojan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;

public class AddRecipeDialog extends DialogFragment {

    private static final int REQUEST_CODE_IMAGE = 1;

    public static final String KEY_DISH_NAME = "dishName";
    public static final String KEY_MEAL_TYPE = "mealType";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";

    private Context context;
    private OnAddClickListener onAddClickListener;
    private EditText dishNameEditText;
    private EditText mealTypeEditText;
    private EditText ingredientsEditText;
    private EditText descriptionEditText;
    private ImageView uploadedImageView;
    private Bitmap uploadedImage;

    public AddRecipeDialog(Context context, OnAddClickListener onAddClickListener) {
        this.context = context;
        this.onAddClickListener = onAddClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_recipe_dialog, null);

        // Initialize views
        dishNameEditText = view.findViewById(R.id.dish_name_edit_text);
        mealTypeEditText = view.findViewById(R.id.meal_type_edit_text);
        ingredientsEditText = view.findViewById(R.id.ingredients);
        descriptionEditText = view.findViewById(R.id.description);
        uploadedImageView = view.findViewById(R.id.uploaded_image);
        Button uploadImageButton = view.findViewById(R.id.upload_image_button);

        // Set click listener for upload image button
        uploadImageButton.setOnClickListener(v -> {
            // Open image picker
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_IMAGE);
        });

        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Retrieve input values
                        String dishName = dishNameEditText.getText().toString().trim();
                        String mealType = mealTypeEditText.getText().toString().trim();
                        String ingredients = ingredientsEditText.getText().toString().trim();
                        String description = descriptionEditText.getText().toString().trim();

                        // Validate inputs and call the listener if valid
                        if (validateInputs(dishName, mealType, ingredients, description)) {
                            onAddClickListener.onAddClick(dishName, mealType, ingredients, description, uploadedImage);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        Dialog dialog = builder.create();

        // Set the dialog window to resize when the keyboard is shown
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            // Retrieve the selected image URI
            Uri imageUri = data.getData();
            try {
                // Convert the URI to a Bitmap
                uploadedImage = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                uploadedImageView.setImageBitmap(uploadedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateInputs(String dishName, String mealType, String ingredients, String description) {
        if (TextUtils.isEmpty(dishName)) {
            showToast("Please enter a dish name");
            return false;
        }

        if (TextUtils.isEmpty(mealType)) {
            showToast("Please enter a meal type");
            return false;
        }

        if (TextUtils.isEmpty(ingredients)) {
            showToast("Please enter the ingredients");
            return false;
        }

        if (TextUtils.isEmpty(description)) {
            showToast("Please enter a description");
            return false;
        }

        if (uploadedImage == null) {
            showToast("Please upload an image");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public interface OnAddClickListener {
        void onAddClick(String dishName, String mealType, String ingredients, String description, Bitmap image);
    }
}