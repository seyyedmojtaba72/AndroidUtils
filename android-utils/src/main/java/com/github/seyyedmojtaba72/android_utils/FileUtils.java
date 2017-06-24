package com.github.seyyedmojtaba72.android_utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by SeyyedMojtaba on 6/24/17.
 */

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static long getFileSize(String address) {
        File file = new File(address);
        long file_size = 0;
        if (file.exists()) {
            file_size = file.length();
        }
        return file_size;
    }

    public static String getFileDirectory(String address) {
        try {
            return address.substring(0, address.lastIndexOf('/')) + "/";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileName(String fileAddress) {
        try {
            return fileAddress.substring(1 + fileAddress.lastIndexOf('/'), fileAddress.length());
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileSingleName(String fileAddress) {
        try {
            return fileAddress.substring(1 + fileAddress.lastIndexOf('/'), fileAddress.lastIndexOf('.'));
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileExtention(String fileAddress) {
        return fileAddress.substring(1 + fileAddress.lastIndexOf('.'), fileAddress.length());
    }

    public static boolean copyFile(File srcFile, File dstFile, boolean replace) {
        Log.d(TAG, "copying file from " + srcFile + " to " + dstFile);

        if (!srcFile.exists()) {
            Log.d("TAG", "[copyFile] failed. source file not exists. file path: " + srcFile.getAbsolutePath());

            return false;
        }

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstFile.getAbsolutePath())).exists()) {
            new File(getFileDirectory(dstFile.getAbsolutePath())).mkdirs();
        }

        try {
            InputStream in = new FileInputStream(srcFile.getAbsolutePath());
            OutputStream out = new FileOutputStream(dstFile.getAbsolutePath());

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean copyFile(String srcAddress, String dstAddress, boolean replace) {
        Log.d(TAG, "copying file from " + srcAddress + " to " + dstAddress);

        File srcFile = new File(srcAddress);
        if (!srcFile.exists()) {
            Log.d("TAG", "[copyFile] failed. source file not exists. file path: " + srcFile.getAbsolutePath());

            return false;
        }

        File dstFile = new File(dstAddress);

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstAddress)).exists()) {
            new File(getFileDirectory(dstAddress)).mkdirs();
        }

        try {
            InputStream in = new FileInputStream(srcAddress);
            OutputStream out = new FileOutputStream(dstAddress);

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean copyFileFromAssets(Context context, String srcAddress, String dstAddress, boolean replace) {
        Log.d(TAG, "copying file from " + srcAddress + " to " + dstAddress);
        AssetManager assetManager = context.getAssets();
        File dstFile = new File(dstAddress);

        if (dstFile.exists()) {
            if (replace) {
                dstFile.delete();
            } else {
                return false;
            }
        }

        if (!new File(getFileDirectory(dstAddress)).exists()) {
            new File(getFileDirectory(dstAddress)).mkdirs();
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(srcAddress);
            out = new FileOutputStream(dstAddress);

            copyFile(in, out);
            in.close();
            out.close();

        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
            return false;
        } catch (IOException localIOException1) {
            localIOException1.printStackTrace();
            return false;
        }

        return true;

    }

    public static Uri getUriFromFile(Context context, String filePath) {

        File file = new File(filePath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }

        return uri;

    }

    public static boolean copyFile(InputStream in, OutputStream out) throws IOException {
        Log.d(TAG, "copying file from input stream to output steam...");
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) > 0) {
            out.write(buffer, 0, read);
        }
        return true;
    }

    public static void saveUriToFile(Uri mImageUri, String path) {
        String sourceFilename = mImageUri.getPath();
        String destinationFilename = path;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);
        } catch (IOException e) {

        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {

            }
        }
    }

    public static void deleteDirectoryTree(File fileOrDirectory) {
        File[] arrayOfFile = null;
        int i = 0;
        if (fileOrDirectory.isDirectory()) {
            arrayOfFile = fileOrDirectory.listFiles();
            i = arrayOfFile.length;
        }
        for (int j = 0; ; j++) {
            if (j >= i) {
                fileOrDirectory.delete();
                return;
            }
            deleteDirectoryTree(arrayOfFile[j]);
        }
    }

    public static boolean exportZip(String src_path, String src_file_name, String dst_path, boolean replace) {
        InputStream is;
        ZipInputStream zis;
        try {
            String filename;
            is = new FileInputStream(src_path + src_file_name);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                // zapis do souboru
                filename = ze.getName();

                if (!replace && new File(src_path + filename).exists()) {
                    continue;
                }

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(dst_path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(dst_path + filename);

                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
