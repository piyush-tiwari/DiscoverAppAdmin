package com.example.discoverappadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class UploadPdfActvity extends AppCompatActivity {

    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private String downloadUrl = "";
    private ProgressDialog pd;

    private Button uploadPdfBtn;
    private EditText pdfTitle;
    private CardView addPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf_actvity);

        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);

        addPdf = findViewById(R.id.addPdf);
        pdfTitle =  findViewById(R.id.pdfTitle);
        uploadPdfBtn = findViewById(R.id.uploadPdfBtn);

        addPdf.setOnClickListener((view) -> { openGallery(); });
    }

    private void openGallery() {
        Intent intent = new Intent();

        //yaha pe change krna hai pdf upload nhi ho rahi
        intent.setType("pdf/docs/ppt");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf File"), REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ && resultCode == RESULT_OK) {
            pdfData = data.getData();

            Toast.makeText(this, ""+pdfData, Toast.LENGTH_SHORT).show();
        }
    }
}