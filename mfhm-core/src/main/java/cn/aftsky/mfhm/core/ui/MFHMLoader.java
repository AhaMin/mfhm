package cn.aftsky.mfhm.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.aftsky.mfhm.core.R;
import cn.aftsky.mfhm.core.util.DimenUtil;

/**
 * Created by MaoHonglu on 2018/5/21.
 */

public class MFHMLoader {

    private static final int LOADER_SIZE_SCALE = 8;//缩放比例,Loader的大小为屏幕的 1/8
    private static final int LOADER_OFFSET_SCALE =10;//上下的偏移量
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();//Loader容器
    private static final String DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name();//默认Loader样式

    //用一个dialog去承载Loader
    public static void showLoading(Context context, String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreater.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;

            lp.height=lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for(AppCompatDialog dialog : LOADERS){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();//会执行onCalcle的回调
                }
            }
        }
    }
}
