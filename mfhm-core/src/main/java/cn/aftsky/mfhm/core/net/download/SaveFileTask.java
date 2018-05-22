package cn.aftsky.mfhm.core.net.download;

import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import cn.aftsky.mfhm.core.net.callback.IResponseListener;
import cn.aftsky.mfhm.core.util.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by MaoHonglu on 2018/5/21.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IResponseListener RESPONSELISTENER;

    SaveFileTask(IResponseListener responseListener) {
        this.RESPONSELISTENER = responseListener;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        RESPONSELISTENER.onSuccess(file.getPath());
        RESPONSELISTENER.onRequestEnd();
    }
}
