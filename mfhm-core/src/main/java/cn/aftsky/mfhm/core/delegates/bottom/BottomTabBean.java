package cn.aftsky.mfhm.core.delegates.bottom;

/**
 * 底部导航栏子项
 * 采用ICON加文字的方式实现
 * Created by MaoHonglu on 2018/5/29.
 */

public class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
