package cn.aftsky.mfhm.main.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by MaoHonglu on 2018/5/12.
 */

public class FontMFHMModule implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return MFHMIcons.values();
    }
}
