package vo.cvcompany.com.myapplication.Module;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by kh on 7/30/2017.
 */

public class MySingleton {

    private static MySingleton mySingleton ;
    private Context context;

    private MySingleton(Context context){
        this.context = context;
    }

    public synchronized static MySingleton getMySingleton(Context context){
        if(mySingleton ==null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public <T> void addVolley(Request<T> request){
        Network network = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024*1024);
        RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        requestQueue.add(request);

    }
}
