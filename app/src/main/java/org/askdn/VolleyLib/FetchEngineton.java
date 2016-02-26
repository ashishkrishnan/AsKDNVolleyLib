package org.askdn.VolleyLib;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ashish on 26/2/16.
 */
public class FetchEngineton {

    public RequestQueue mRequestQueue;
    private Context mContext;
    private ImageLoader mLoader;
    private static FetchEngineton instance;

    FetchEngineton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();

        // Create a imageLoader;
        mLoader = new ImageLoader(mRequestQueue,new LruBitmapCache(LruBitmapCache.getCacheSize(context)));

    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null) {

            Cache cache = new DiskBasedCache(mContext.getCacheDir(),10*1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache,network);
        }
        return mRequestQueue;
    }

    public static synchronized FetchEngineton getInstance(Context context) {
        if(instance==null){
            instance = new FetchEngineton(context);
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        mRequestQueue.add(request);
    }



}
