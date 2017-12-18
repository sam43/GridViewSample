package com.example.asmsayem.gridviewsample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmsayem.gridviewsample.models.PhotoInfoModel;
import com.example.asmsayem.gridviewsample.models.ResponsePathsPhoto;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;
    final private int PICK_IMAGE = 1;
    final private int CAPTURE_IMAGE = 2;
    Button btn_change, btn_delete, btn_upload;
    TextView tv_upload_info;
    ImageView img_user_photo;
    LinearLayout ll_chng_dlt;
    Bitmap upImageBitmap;
    ProgressDialog progressDialog;
    GlobalClasses cls;
    String userID = "241028", decodeID = "NSu6L6", isCVposted = "True", isResumeUpdate = "false";
    private String folderName;
    private String folderId, status;
    private String imageName, img_base64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExistingPhotoResponse();
        InitializeViews();
        OnCLickListeners();
    }

    private void OnCLickListeners() {
        btn_change.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }


    private void InitializeViews() {
        cls = new GlobalClasses();
        btn_change = (Button) findViewById(R.id.btn_change);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        tv_upload_info = (TextView) findViewById(R.id.tv_upload_info);
        img_user_photo = (ImageView) findViewById(R.id.img_user_ups);
        ll_chng_dlt = (LinearLayout) findViewById(R.id.ll_chng_dlt);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setTitle("Saving...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                ShowChooserAlert();
                break;
            case R.id.btn_delete:
                status = "delete";
                UploadDeletePtoto(status);
                btn_upload.setVisibility(View.VISIBLE);
                ll_chng_dlt.setVisibility(View.GONE);
                break;
            case R.id.btn_upload:
                status = "upload";
                progressDialog.show();
                img_base64 = bitmapToString();
                Log.d("****ImagBase64", "onClick: " + img_base64);
                UploadDeletePtoto(status);
                finish();
            default:
                break;
        }
    }

    private void UploadDeletePtoto(String status) {
        MybdjobsAPI.Factory.getInstance()
                .uploadPhoto(img_base64, userID, decodeID, folderName, folderId, imageName, isResumeUpdate, status)
                .enqueue(new Callback<ResponsePathsPhoto>() {
                    @Override
                    public void onResponse(Call<ResponsePathsPhoto> call, Response<ResponsePathsPhoto> response) {
                        progressDialog.dismiss();
                        ResponsePathsPhoto photo = response.body();
                        if (response.isSuccessful()) {
                            Log.d("***UploadedPhoto", "onResponse: " + photo.getMessgae());
                            cls.Tostyfy(MainActivity.this, "" + photo.getMessgae());
                        } else {
                            Log.d("***UploadedPhoto", "onResponse: " + photo.getMessgae());
                            cls.Tostyfy(MainActivity.this, "" + photo.getMessgae());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePathsPhoto> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.e("photoAPI", t.getMessage());
                    }
                });
    }

    private void ExistingPhotoResponse() {
        MybdjobsAPI.Factory.getInstance()
                .getPhotoInfo(userID, decodeID)
                .enqueue(new Callback<PhotoInfoModel>() {
                    @Override
                    public void onResponse(Call<PhotoInfoModel> call, Response<PhotoInfoModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getMessageType().equals("1")) {

                                folderName = response.body().getFolderName();
                                folderId = response.body().getFolderId();
                                imageName = response.body().getImageName();

                            } else {
                                cls.Tostyfy(MainActivity.this, "Something wrong!");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoInfoModel> call, Throwable t) {

                        progressDialog.dismiss();
                        Log.e("photoAPI", t.getMessage());
                    }
                });
    }

    private void ShowChooserAlert() {

        final CharSequence[] charSequence = new CharSequence[]{"Gallery", "Take Photo", "Import from Facebook", "Import from Google +", "Import from LinkedIn"};
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Choose Photo from... ");
        alert.setItems(charSequence, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, charSequence[which], Toast.LENGTH_SHORT).show();
                if (charSequence[which] == "Gallery") {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, PICK_IMAGE);
                    btn_upload.setVisibility(View.VISIBLE);
                    ll_chng_dlt.setVisibility(View.GONE);
                    cls.Tostyfy(MainActivity.this, "" + charSequence[which]);
                } else if (charSequence[which] == "Take Photo") {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CAPTURE_IMAGE);
                    }
                    //RequestPermissionAndOpenCamera();
                    btn_upload.setVisibility(View.VISIBLE);
                    ll_chng_dlt.setVisibility(View.GONE);

                    cls.Tostyfy(MainActivity.this, "" + charSequence[which]);
                } else if (charSequence[which] == "Facebook") {
                    cls.Tostyfy(MainActivity.this, "" + charSequence[which]);
                } else if (charSequence[which] == "Google +") {
                    cls.Tostyfy(MainActivity.this, "" + charSequence[which]);
                } else if (charSequence[which] == "LinkedIn") {
                    cls.Tostyfy(MainActivity.this, "" + charSequence[which]);
                }
            }
        });
        alert.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {

                //btnUpload.setEnabled(true);
                // Get the Image from data
                Uri selectedImage = data.getData();
                upImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                upImageBitmap = BitmapFactory.decodeStream(imageStream);
                upImageBitmap = getResizedBitmap(upImageBitmap, 800);
                img_user_photo.setImageBitmap(upImageBitmap);

            } else if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                upImageBitmap = (Bitmap) extras.get("data");
                upImageBitmap = getResizedBitmap(upImageBitmap, 800);
                img_user_photo.setImageBitmap(upImageBitmap);
            } else {
                cls.Tostyfy(this, "You haven't picked any Image!");
            }
        } catch (Exception e) {
            cls.Tostyfy(this, "Something went wrong!");
        }
    }

    public String bitmapToString() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        upImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        return Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
