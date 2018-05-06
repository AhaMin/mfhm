package cn.aftsky.mfhm.core;

import java.util.WeakHashMap;

/**
 * Created by MaoHonglu on 2018/5/5.
 * 存储各种配置项和配置项的信息
 */


public class Configurator {

    private static final WeakHashMap<String,Object> MFHM_CONFIGS=new WeakHashMap<>();

    private Configurator(){
        MFHM_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    static  Configurator getInstance(){
        return Holder.INSTANCE;
    }

    /*使用静态内部类来实现线程安全的懒汉式单例模式*/
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<String,Object> getMfhmConfigs(){
        return MFHM_CONFIGS;
    }

    public final Configurator withApiHost(String host){
        MFHM_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T)MFHM_CONFIGS.get(key);
    }

    public final void configure(){
        MFHM_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private void checkConfiguration(){
        final boolean isReady=(boolean) MFHM_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }

    }


}
