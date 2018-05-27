package cn.aftsky.mfhm.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import cn.aftsky.mfhm.core.constants.ConfigType;
import okhttp3.Interceptor;

/**
 * Created by MaoHonglu on 2018/5/5.
 * 存储各种配置项和配置项的信息
 */


public class Configurator {

    private static final HashMap<Object,Object> MFHM_CONFIGS=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS= new ArrayList<>();

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

    final HashMap<Object,Object> getMfhmConfigs(){
        return MFHM_CONFIGS;
    }

    public final Configurator withApiHost(String host){
        MFHM_CONFIGS.put(ConfigType.API_HOST,host);
        System.out.println("初始化host"+host+"中...");
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        MFHM_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        MFHM_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);//添加字体
        System.out.println("初始化"+descriptor.ttfFileName()+"中...");
        return this;
    }
    /*初始化字体图标库*/
    private void initIcons(){
        if(ICONS.size()>0){
            //不先取出来没办法关联之后的字体，可以查看源代码，不太理解
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for(int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = MFHM_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) MFHM_CONFIGS.get(key);
    }

    public final void configure(){
        initIcons();
        MFHM_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private void checkConfiguration(){
        final boolean isReady=(boolean) MFHM_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
}
