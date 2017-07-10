package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    public static boolean reduceImageFileSize(final String filePath, int max_file_size_bytes, int quality, int ratio) {

        File file = new File(filePath);

        if (!file.exists() || file.length() == 0 || max_file_size_bytes == 0) {
            return false;
        }

        int scale = 1;
        BitmapFactory.Options o = new BitmapFactory.Options();
        //o.inJustDecodeBounds = true;
        //o.inSampleSize = scale;

        Log.d(TAG, "[reduceImageFileSize] to reduce. " + file.length() + " must be smaller than " + max_file_size_bytes);

        if (file.length() > max_file_size_bytes) {
            Log.d(TAG, "[reduceImageFileSize] file size is larger than maximum.");


            Bitmap photo = getBitmapFromFile(filePath);

            float imageWidth = photo.getWidth();
            float imageHeight = photo.getHeight();

            float newImageWidth = 100, newImageHeight = 100;


            try {
                file = new File(filePath);

                while (file.length() > max_file_size_bytes && newImageWidth > 0 && newImageHeight > 0) {
                    Log.d(TAG, "[reduceImageFileSize] resizing photo... (" + file.length() + " > " + max_file_size_bytes + ") ratio: " + ratio + " (" + ((100f - scale * ratio) / 100f) * imageWidth + " x " + ((100f - scale * ratio) / 100f) * imageHeight + ")");

                    newImageWidth = ((100f - scale * ratio) / 100f) * imageWidth;
                    newImageHeight = ((100f - scale * ratio) / 100f) * imageHeight;

                    Bitmap photo2 = Bitmap.createScaledBitmap(photo, Math.round(newImageWidth), Math.round(newImageHeight), false);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    photo2.compress(Bitmap.CompressFormat.JPEG, quality, bytes);

                    File f = new File(filePath);
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    fo.close();

                    scale++;


                    // OLD
                    /*
                    scale++;
                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                    o2.inSampleSize = scale;

                    Bitmap bitmap = BitmapFactory.decodeFile(filePath, o2);
                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(filePath);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                        out.flush();
                        out.close();
                        bitmap.recycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    */
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "[reduceImageFileSize] resized (" + file.length() + " <= " + max_file_size_bytes + ")");
        }
        return true;
    }

    public static Bitmap getScaledBitmap(String filePath, int maxSize) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Bitmap imageBitmap = BitmapFactory.decodeStream(fis, null, options);

            int outWidth;
            int outHeight;
            int inWidth = imageBitmap.getWidth();
            int inHeight = imageBitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, outWidth, outHeight, false);
            return resizedBitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap getBitmapFromResources(Context context, int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        return bitmap;
    }


    public static Bitmap getBitmapFromUrl(String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap getBitmapFromAssets(Context context, String fileAddress) {
        AssetManager assetManager = context.getAssets();

        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = assetManager.open(fileAddress);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromFile(String fileAddress) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(fileAddress, options);
        return bitmap;
    }

    public static void saveBitmapToFile(Bitmap bitmap, String fileAddress) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileAddress);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            bitmap.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            Bitmap mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return mBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void rotateImageIfIsRequired(String filePath) {
        try {
            // Rotate picture if necessary
            Bitmap fileBitmap = getBitmapFromFile(filePath);
            Bitmap fileBitmap2 = rotateBitmap(fileBitmap, getOrientationOfImageFile(filePath));
            if (fileBitmap != null) {
                saveBitmapToFile(fileBitmap, filePath);
            }
            if (fileBitmap != null && !fileBitmap.isRecycled()) {
                fileBitmap.recycle();
            }

            if (fileBitmap2 != null && !fileBitmap2.isRecycled()) {
                fileBitmap2.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getOrientationOfImageFile(String fileAddress) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(fileAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        int rotate = 0;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotate = 270;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotate = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotate = 180;
                break;
        }
        return rotate;
    }
}
