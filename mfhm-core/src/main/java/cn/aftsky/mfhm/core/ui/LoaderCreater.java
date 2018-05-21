package cn.aftsky.mfhm.core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * 使用缓存创建loader
 * Created by MaoHonglu on 2018/5/21.
 */

public class LoaderCreater {
    private static final WeakHashMap<String,Indicator> LOADING_MAP=new WeakHashMap<>(); //存储Loader

    static AVLoadingIndicatorView create(String type,Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name){
        if(name ==null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //如果输入的是全路径，则通过反射获取默认的包名
        if(!name.contains(".")){
            final String defaultPacakgeName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPacakgeName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try{
            final Class <?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
