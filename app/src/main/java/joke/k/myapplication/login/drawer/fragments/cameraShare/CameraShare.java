package joke.k.myapplication.login.drawer.fragments.cameraShare;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import joke.k.myapplication.BuildConfig;
import joke.k.myapplication.R;
import okhttp3.internal.Util;

import static android.app.Activity.RESULT_OK;

public class CameraShare extends Fragment {


    String mCurrentPhotoPath;

    public static final int REQUEST_IMAGE_CAPTURE = 12312;
    public static final int CAMERA_PERMISSION_REQUEST = 12322;
    public static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 12322;
    private boolean hasDenied = false;
    public static final String PHOTO_NAME = "my_photo";
    public static final String PHOTOS_DIR = "my_photos";
    String fileName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_share, container, false);
        ButterKnife.bind(this, view);


        return view;
    }



    @OnClick(R.id.imageButtonCamera)
    public void cameraAction(View view){

        checkPermission(getContext());





    }

    private File createImageFiles() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void openCamera() {


        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try{
                photoFile=createImageFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(photoFile!=null) {
                Uri photoUri = FileProvider.getUriForFile(getActivity().getBaseContext(), "joke.k.myapplication.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                startActivityForResult(pictureIntent,
                        REQUEST_IMAGE_CAPTURE);
            }
            }

        }



    private void checkPermission(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // happy path

            checkPermissionWriteExternalStorage(context);
        } else {
            // we don't have the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                // already asked, used denied
                // explain to the user why you need this permission
                showAlertDialogToTheUser();
            } else {
                // used chose "don't ask again" or we are asking for the first time
                if (!hasDenied) {
                    hasDenied = true;
                    requestCameraPermission();
                } else {
                    showAlertDialogToTheUser();
                }
            }
        }
    }

    private void checkPermissionWriteExternalStorage(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // happy path

            openCamera();
        } else {
            // we don't have the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // already asked, used denied
                // explain to the user why you need this permission
                showAlertDialogToTheUser();
            } else {
                // used chose "don't ask again" or we are asking for the first time
                if (!hasDenied) {
                    hasDenied = true;
                    requestWriteExternalStoragePermission();
                } else {
                    showAlertDialogToTheUser();
                }
            }
        }
    }



    private void showAlertDialogToTheUser() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Permission Request");
        dialogBuilder.setMessage("Reject");
        dialogBuilder.setPositiveButton("Grant", (dialogInterface, i) -> {
            // navigate to settings screen where user can grant permissions manually
            goToSettingsScreen();
        });
        dialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            // just dismiss the dialog
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void goToSettingsScreen() {
        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getActivity().getPackageName(), null)));
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
    }

    private void requestWriteExternalStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                // show thumbnail in our image view

                File file = new File(mCurrentPhotoPath);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),Uri.fromFile(file));
                    if(bitmap!=null){

                        String bitmapPath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,"Title",null);
                        Uri bitmapUri=Uri.parse(bitmapPath);

                        Intent shareIntent = new Intent (Intent.ACTION_SEND);
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_STREAM,bitmapUri);
                        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        shareIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        startActivity(Intent.createChooser(shareIntent,"Share via"));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            }
        }




    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = Calendar.getInstance().getTime().toString();
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        fileName =  image.getAbsolutePath();
        return image;
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // user has granted the requested permission
                openCamera();
            } else {
                // user denied, show a message
                Toast.makeText(getContext(), getString(R.string.permission_denied_message), Toast.LENGTH_SHORT).show();
            }
        }
    }




    private void savePhotoInInternalStorage(Bitmap bitmap) {
        try {

            File cachePath = new File(getActivity().getBaseContext().getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
