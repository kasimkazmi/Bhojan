package com.example.bhojan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AddRecipeDialog extends Dialog {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SELECT_IMAGE = 2;
    private Activity activity;

    private EditText dishNameEditText;
    private EditText mealTypeEditText;
    private EditText ingredientsEditText;
    private EditText descriptionEditText;
    private ImageView uploadedImageView;
    private Bitmap uploadedImage;
    private OnAddClickListener onAddClickListener;

    public AddRecipeDialog(Context context, Activity activity, OnAddClickListener onAddClickListener) {
        super(context);
        this.activity = activity;
        this.onAddClickListener = onAddClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_recipe_dialog);

        dishNameEditText = findViewById(R.id.dish_name_edit_text);
        mealTypeEditText = findViewById(R.id.meal_type_edit_text);
        ingredientsEditText = findViewById(R.id.ingredients);
        descriptionEditText = findViewById(R.id.description);
        uploadedImageView = findViewById(R.id.uploaded_image);

        Button uploadImageButton = findViewById(R.id.upload_image_button);
        uploadImageButton.setOnClickListener(v -> {
            // Show a dialog to choose between camera and gallery options
            showImageSourceDialog();
        });

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            String dishName = dishNameEditText.getText().toString();
            String mealType = mealTypeEditText.getText().toString();
            String ingredients = ingredientsEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (TextUtils.isEmpty(dishName)) {
                Toast.makeText(getContext(), "Please enter a dish name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(mealType)) {
                Toast.makeText(getContext(), "Please enter a meal type", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(ingredients)) {
                Toast.makeText(getContext(), "Please enter ingredients", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(description)) {
                Toast.makeText(getContext(), "Please enter instructions", Toast.LENGTH_SHORT).show();
            } else if (uploadedImage == null) {
                Toast.makeText(getContext(), "Please upload an image", Toast.LENGTH_SHORT).show();
            } else {
                // Pass the entered information to the listener
                onAddClickListener.onAddClick(dishName, mealType, ingredients, description, uploadedImage);
                dismiss();
            }
        });
    }

    private void showImageSourceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, (dialog, which) -> {
            if (which == 0) {
                launchCamera();
            } else {
                selectFromGallery();
            }
        });
        builder.show();
    }

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(getContext(), "Camera not supported", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_SELECT_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    setImage(imageBitmap);
                }
            } else if (requestCode == REQUEST_SELECT_IMAGE) {
                if (data != null && data.getData() != null) {
                    Uri imageUri = data.getData();
                    try {
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                        setImage(imageBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Failed to load the image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void setImage(Bitmap imageBitmap) {
        uploadedImage = imageBitmap;
        uploadedImageView.setImageBitmap(imageBitmap);
    }

    public interface OnAddClickListener {
        void onAddClick(String dishName, String mealType, String ingredients, String description, Bitmap image);
    }
}