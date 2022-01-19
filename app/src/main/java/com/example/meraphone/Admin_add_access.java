package com.example.meraphone;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meraphone.model.Accessories;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class Admin_add_access extends Fragment {
    EditText ed_itemname, ed_cost, ed_modelno, ed_rating, ed_frontcamera, ed_backcamera, ed_desc;
    ImageView img;
    Spinner sp_category, sp_hybridsimslot, sp_touchscreen, sp_otgcompatible;
    Button submit;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 71;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageinbtye;
    String encodedImage;
    //String encodedImage;
    private Bitmap imagetostore;
    FirebaseFirestore db;


    StorageReference mstorageRef;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    private StorageTask uploadTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_add_access, container, false);


        ed_itemname = view.findViewById(R.id.ed_itemname);
        ed_desc = view.findViewById(R.id.ed_desc);
        ed_cost = view.findViewById(R.id.ed_cost);
        ed_rating = view.findViewById(R.id.ed_rating);

        submit = view.findViewById(R.id.submit);
        img = view.findViewById(R.id.mainitem);
        db = FirebaseFirestore.getInstance();
        mstorageRef = FirebaseStorage.getInstance().getReference("Images");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }


                Accessories product = new Accessories();


                product.setItemName(ed_itemname.getText().toString());

                product.setItemPrice(ed_cost.getText().toString());
                product.setItemRating(ed_rating.getText().toString());
                product.setItemDesc(ed_desc.getText().toString());
                product.setItemImage(a + "." + b);


                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }


                db.collection("Accessories").add(product)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), "Item Added..", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Item Not Added. Try Again...", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }

    private void Fileuploader() {

        //StorageReference ref = mstorageRef.child(System.currentTimeMillis()
        b = getExtension(imguri);
        a = String.valueOf(System.currentTimeMillis());
        StorageReference ref = mstorageRef.child(a
                + "." + getExtension(imguri));
        uploadTask = ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getUploadSessionUri();  //getDownloadUrl();
                        // Toast.makeText(getContext(),
                        //"Image uploaded", Toast.LENGTH_LONG).show();
                        //toserveraswell();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            img.setImageURI(imguri);

        }
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);


    }
}


