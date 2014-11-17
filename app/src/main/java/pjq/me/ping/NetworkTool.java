package pjq.me.ping;

import android.util.Log;

import net.qiujuer.genius.nettool.DnsResolve;
import net.qiujuer.genius.nettool.NetModel;
import net.qiujuer.genius.nettool.Ping;

/**
 * Created by pjq on 11/17/14.
 */
public class NetworkTool {
    private static final String TAG = NetworkTool.class.getSimpleName();

    public static void ping(final PingListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Ping ping = new Ping("sg1.flyhide.com");
                ping.start();
                if (ping.getError() != NetModel.SUCCEED) {
                    Log.i(TAG, "Exception, " + ping.toString());
                    listener.onResult(ping);
                } else {
                    Log.i(TAG, ping.toString());
                    listener.onResult(ping);
                }
            }
        }).start();
    }

    public static void dns() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DnsResolve dnsResolve = new DnsResolve("www.baidu.com");
                dnsResolve.start();
                if (dnsResolve.getError() != NetModel.SUCCEED) {
                    Log.i(TAG, "DNS Exception");
                } else {
                    Log.i(TAG, "DNS " + dnsResolve.toString());
                }
            }
        }).start();
    }

    public static interface PingListener{
        void onResult(Object result);
    }
}
