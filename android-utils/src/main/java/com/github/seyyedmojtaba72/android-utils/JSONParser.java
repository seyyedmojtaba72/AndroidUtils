package com.developars.utils.android;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JSONParser {
    private static String TAG = "JSONParser";

    public static String getFromString(String jsonStr, String task) {
        JSONTask localJSONTask = new JSONTask(jsonStr, task);
        try {
            String str = localJSONTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return str;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return "";
        } catch (ExecutionException localExecutionException) {
            while (true) localExecutionException.printStackTrace();
        }
    }

    public static String getFromFile(String fileAddress, String task) {
        try {
            String str = new JSONTask(Fetcher.FetchFile(fileAddress), task).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return str;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return "";
        } catch (ExecutionException localExecutionException) {
            localExecutionException.printStackTrace();
            return "";
        } catch (Exception localException) {
            localException.printStackTrace();
            return "";
        }
    }

    public static String getFromURL(String urlAddress, String task) {
        try {
            String str = new JSONTask(Fetcher.FetchURL(urlAddress), task).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return str;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return "";
        } catch (ExecutionException localExecutionException) {
            localExecutionException.printStackTrace();
            return "";
        } catch (Exception localException) {
            localException.printStackTrace();
            return "";
        }
    }

    public static List<String> getListFromFile(String fileAddress, String task, String var) {
        try {
            List<String> localList = new JSONListTask(Fetcher.FetchFile(fileAddress), task, var).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return localList;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return new ArrayList<String>();
        } catch (ExecutionException localExecutionException) {
            localExecutionException.printStackTrace();
            return new ArrayList<String>();
        } catch (Exception localException) {
            localException.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public static List<String> getListFromString(String jsonStr, String task, String var) {
        JSONListTask localJSONListTask = new JSONListTask(jsonStr, task, var);
        try {
            List<String> localList = localJSONListTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return localList;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return new ArrayList<String>();
        } catch (ExecutionException localExecutionException) {
            localExecutionException.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public static List<String> getListFromURL(String urlAddress, String task, String var) {
        try {
            List<String> localList = new JSONListTask(Fetcher.FetchURL(urlAddress), task, var).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]).get();
            return localList;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            return new ArrayList<String>();
        } catch (ExecutionException localExecutionException) {

            localExecutionException.printStackTrace();
            return new ArrayList<String>();
        } catch (Exception localException) {
            localException.printStackTrace();
            return new ArrayList<String>();
        }
    }

    static class JSONListTask extends AsyncTask<String, String, List<String>> {
        String jsonStr;
        String task;
        String var;
        private List<String> list = new ArrayList<String>();

        public JSONListTask(String paramString1, String paramString2, String paramString3) {
            this.jsonStr = paramString1;
            this.task = paramString2;
            this.var = paramString3;
        }

        protected void onPreExecute() {
            //Log.d(JSONParser.TAG, "JSONTask is about to start...");
        }

        protected List<String> doInBackground(String... paramArrayOfString) {
            if ((this.jsonStr == null) || (this.jsonStr.equals("-1")))
                return new ArrayList<>();

            try {
                JSONObject localJSONObject1 = new JSONObject(this.jsonStr);
                //Log.d(JSONParser.TAG, "JSON String = '" + this.jsonStr + "' task = " + this.task);

                JSONArray localJSONArray = localJSONObject1.getJSONArray(this.task);
                for (int i = 0; i < localJSONArray.length(); i++) {
                    try {
                        this.list.add(localJSONArray.getJSONObject(i).getString(this.var));
                    } catch (JSONException e) {
                        this.list.add("");
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }

        protected void onPostExecute(List<String> paramList) {
            //Log.d(JSONParser.TAG, "JSONTask finished.");
        }


    }

    static class JSONTask extends AsyncTask<String, String, String> {
        String jsonStr;
        String task;

        public JSONTask(String jsonStr, String task) {
            this.jsonStr = jsonStr;
            this.task = task;
        }

        protected void onPreExecute() {
            //Log.d(JSONParser.TAG, "JSONTask is about to start...");
        }

        protected String doInBackground(String... paramArrayOfString) {
            try {
                //Log.d(JSONParser.TAG, "JSON String = " + this.jsonStr);
                String str = new JSONObject(this.jsonStr).getString(this.task);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        protected void onPostExecute(String paramString) {
            //Log.d(JSONParser.TAG, "JSONTask finished.");
        }


    }
}