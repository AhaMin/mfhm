package cn.aftsky.mfhm.main.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by MaoHonglu on 2018/5/12.
 */

public enum MFHMIcons implements Icon{
    icon_scan('\ue518'),
    icon_ali_pay('\ue636');

    private char character;

    MFHMIcons(char character){
        this.character=character;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
