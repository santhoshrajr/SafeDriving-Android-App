package com.imperium.autobeacon;

/**
 * Created by Sowmya on 6/20/2016.
 */

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;






public class ServerRequest {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    String token;

    public ServerRequest() {

    }

    public JSONObject getJSONFromUrl(String url, HashMap<String, String> params) {

        URL urln;
        String response = "";
        try {

            //String ret = null;
            // Create the SSL connection

            //       Log.d("database", "Doing: " + this.link );
            HttpURLConnection c = null;
            URL u = new URL(url);
            //String result = new String("");
            c = (HttpURLConnection) u.openConnection();


              /*  *//*SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                sc.init(null, null, new java.security.SecureRandom());
                c.setSSLSocketFactory(sc.getSocketFactory());

                // Use this if you need SSL authentication
                String userpass = "user" + ":" + "password";
                String basicAuth = "Basic " + Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
                c.setRequestProperty("Authorization", basicAuth);*//*
                //c.setFixedLengthStreamingMode(this.datas.getBytes().length);
               *//* OutputStream wrs = c.getOutputStream();
                wrs.write(this.datas.getBytes());
                wrs.flush();
                wrs.close();*/
            //int responseCode=conn.getResponseCode();
            /*String basicAuth = "Bearer " + Constants.token;
            c.setRequestProperty ("Authorization", basicAuth);*/
            c.setRequestMethod("POST");
            //* c.setRequestProperty("USER-AGENT", "Mozilla/5.0");*//*
            //  c.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            c.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            c.setDoOutput(true);
            c.setDoInput(true);
            //androidid idnew=new androidid();

            c.setRequestProperty("Content-Length", "" +
                    Integer.toString(getPostDataString(params).getBytes().length));
            c.setRequestProperty("Content-Language", "en-US");
            //c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            //c.setConnectTimeout(10000);
            // c.setReadTimeout(10000);
            //Send request

            DataOutputStream wr = new DataOutputStream(
                    c.getOutputStream());
            wr.writeBytes(getPostDataString(params));
            wr.flush();
            wr.close ();
            c.connect();

            int status = c.getResponseCode();

            if(status==200 || status==201) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line + "\n");
                br.close();
                response = sb.toString();
            }


            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }






        try {
            jObj = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }

    public JSONObject getJSONFromUrldriver(String url, HashMap<String, String> params) {

        URL urln;
        String response = "";
        try {

            //String ret = null;
            // Create the SSL connection

            //       Log.d("database", "Doing: " + this.link );
            HttpURLConnection c = null;
            URL u = new URL(url);
            //String result = new String("");
            c = (HttpURLConnection) u.openConnection();


              /*  *//*SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                sc.init(null, null, new java.security.SecureRandom());
                c.setSSLSocketFactory(sc.getSocketFactory());

                // Use this if you need SSL authentication
                String userpass = "user" + ":" + "password";
                String basicAuth = "Basic " + Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
                c.setRequestProperty("Authorization", basicAuth);*//*
                //c.setFixedLengthStreamingMode(this.datas.getBytes().length);
               *//* OutputStream wrs = c.getOutputStream();
                wrs.write(this.datas.getBytes());
                wrs.flush();
                wrs.close();*/
            //int responseCode=conn.getResponseCode();
            String basicAuth = "Bearer " + Constants.token;
            c.setRequestProperty ("Authorization", Base64.encodeToString(basicAuth.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP));
            c.setRequestMethod("POST");
            //* c.setRequestProperty("USER-AGENT", "Mozilla/5.0");*//*
            //  c.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            c.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            c.setDoOutput(true);
            c.setDoInput(true);
            //androidid idnew=new androidid();

            c.setRequestProperty("Content-Length", "" +
                    Integer.toString(getPostDataString(params).getBytes().length));
            c.setRequestProperty("Content-Language", "en-US");
            //c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            //c.setConnectTimeout(10000);
            // c.setReadTimeout(10000);
            //Send request

            DataOutputStream wr = new DataOutputStream(
                    c.getOutputStream());
            wr.writeBytes(getPostDataString(params));
            wr.flush();
            wr.close ();
            c.connect();

            int status = c.getResponseCode();

            if(status==200 || status==201) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line + "\n");
                br.close();
                response = sb.toString();
            }


            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }






        try {
            jObj = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }

    public JSONObject getJSONFromUrlnoparams(String url) {

        URL urln;
        String response = "";
        try {

            //String ret = null;
            // Create the SSL connection

            //       Log.d("database", "Doing: " + this.link );
            HttpURLConnection c = null;
            URL u = new URL("https://www.google.com/");
            //String result = new String("");
            c = (HttpURLConnection) u.openConnection();


              /*  *//*SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                sc.init(null, null, new java.security.SecureRandom());
                c.setSSLSocketFactory(sc.getSocketFactory());

                // Use this if you need SSL authentication
                String userpass = "user" + ":" + "password";
                String basicAuth = "Basic " + Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
                c.setRequestProperty("Authorization", basicAuth);*//*
                //c.setFixedLengthStreamingMode(this.datas.getBytes().length);
               *//* OutputStream wrs = c.getOutputStream();
                wrs.write(this.datas.getBytes());
                wrs.flush();
                wrs.close();*/
            //int responseCode=conn.getResponseCode();
           /* String basicAuth = "Bearer " + Constants.token;
            c.setRequestProperty ("Authorization", Base64.encodeToString(basicAuth.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP));*/
            c.setRequestMethod("GET");
             c.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            c.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            /*c.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            c.setDoOutput(true);
            c.setDoInput(true);*/
            //androidid idnew=new androidid();

           /* c.setRequestProperty("Content-Length", "" +
                    Integer.toString(getPostDataString(params).getBytes().length));*/
            c.setRequestProperty("Content-Language", "en-US");
            //c.setRequestProperty("Content-length", "0");
           /* c.setUseCaches(false);
            c.setAllowUserInteraction(false);*/
            //c.setConnectTimeout(10000);
            // c.setReadTimeout(10000);
            //Send request

           /* DataOutputStream wr = new DataOutputStream(
                    c.getOutputStream());
           // wr.writeBytes(getPostDataString(params));
            wr.flush();
            wr.close ();*/
            c.connect();

            int status = c.getResponseCode();

            if(status==200 || status==201) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line + "\n");
                br.close();
                response = sb.toString();
            }


            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }






        try {
            jObj = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }

    public JSONObject getJSONFromUrlnoparams1(String url) {

        URL urln;
        String response = "";
        HttpURLConnection c = null;

        try {

            //String ret = null;
            // Create the SSL connection

            //       Log.d("database", "Doing: " + this.link );

            URL u = new URL(url);
            //String result = new String("");
            c = (HttpURLConnection) u.openConnection();
            String basicAuth = "Bearer " + Constants.token;
            c.setRequestProperty ("Authorization", Base64.encodeToString(basicAuth.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP));

            InputStream in = c.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }






        try {
            jObj = new JSONObject();
        } catch (Exception e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jObj;

    }

    JSONObject jobj;
    public JSONObject getJSON(String url, HashMap<String, String> params) {

        Params param = new Params(url,params);
        Request myTask = new Request();
        try{
            jobj= myTask.execute(param).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return jobj;
    }

    public JSONObject getJSONdriver(String url, HashMap<String, String> params) {

        Params param = new Params(url,params);
        Requestdriver myTask = new Requestdriver();
        try{
            jobj= myTask.execute(param).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return jobj;
    }

    private static class Params {
        String url;
        HashMap<String, String> params;
        // String token;


        Params(String url, HashMap<String, String> params) {
            this.url = url;
            this.params = params;
            // this.token=token;

        }
    }

    private class Request extends AsyncTask<Params, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(Params... args) {

            ServerRequest request = new ServerRequest();
            JSONObject json = request.getJSONFromUrl(args[0].url,args[0].params);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

        }

    }

    private class Requestdriver extends AsyncTask<Params, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(Params... args) {

            ServerRequest request = new ServerRequest();
            JSONObject json = request.getJSONFromUrldriver(args[0].url,args[0].params);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

        }

    }

    //
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public JSONObject getdriver(String url) {

       // Params param = new Params(url,params);
        Requestdrivernoparams myTask = new Requestdrivernoparams();
        try{
            jobj= myTask.execute(url).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return jobj;
    }

    private class Requestdrivernoparams extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... args) {

            ServerRequest request = new ServerRequest();
            JSONObject json = request.getJSONFromUrlnoparams(args[0]);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

        }

    }

}