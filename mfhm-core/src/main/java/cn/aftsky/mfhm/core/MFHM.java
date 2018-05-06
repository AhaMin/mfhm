package cn.aftsky.mfhm.core;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by MaoHonglu on 2018/5/5.
 */

public class MFHM {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getMfhmConfigs();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

//    public static <T> T getConfiguration(Object key) {
//        return getConfigurator().getConfiguration(key);
//    }
}
