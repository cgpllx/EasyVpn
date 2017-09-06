package cc.easyandroid.easyvpn.tunnel.httpconnect;

import android.net.Uri;

import cc.easyandroid.easyvpn.tunnel.Config;

import java.net.InetSocketAddress;

public class HttpConnectConfig extends Config {
    public String UserName;
    public String Password;

    public static HttpConnectConfig parse(String proxyInfo) {
        HttpConnectConfig config = new HttpConnectConfig();
        Uri uri = Uri.parse(proxyInfo);
        String userInfoString = uri.getUserInfo();
        if (userInfoString != null) {
            String[] userStrings = userInfoString.split(":");
            config.UserName = userStrings[0];
            if (userStrings.length >= 2) {
                config.Password = userStrings[1];
            }
        }
        config.host =  uri.getHost();
        config.port =  uri.getPort();
        config.ServerAddress = new InetSocketAddress(config.host, config.port);
        return config;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return this.toString().equals(o.toString());
    }

    @Override
    public String toString() {
        return String.format("http://%s:%s@%s", UserName, Password, ServerAddress);
    }
}
