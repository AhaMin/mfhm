package cn.aftsky.mfhm.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by MaoHonglu on 2018/5/5.
 */

public class MFHM {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getMfhmConfigs();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplicationContext(){
//        System.out.println("这里输出的值为：ConfigType.APPLICATION_CONTEXT.name()="+ConfigType.APPLICATION_CONTEXT.name());
//        HashMap<String,Object> MFHM_CONFIGS=getConfigurations();
//        System.out.println("这里输出的值为：MFHM_CONFIGS.get(ConfigType.APPLICATION_CONTEXT.name())="+MFHM_CONFIGS.get(ConfigType.APPLICATION_CONTEXT.name()));
//        System.out.println("这里输出的值为："+(Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name()));
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

//    public static <T> T getConfiguration(Object key) {
//        return getConfigurator().getConfiguration(key);
//    }
}
