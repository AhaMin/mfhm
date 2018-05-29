package cn.aftsky.mfhm.core.delegates.bottom;

import android.widget.Toast;

import java.util.LinkedHashMap;

import cn.aftsky.mfhm.core.R;
import cn.aftsky.mfhm.core.app.MFHM;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;

/**
 * 底部导航栏创建
 * 采用建造者模式实现
 * Created by MaoHonglu on 2018/5/29.
 */

public class ItemBuilder{
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
