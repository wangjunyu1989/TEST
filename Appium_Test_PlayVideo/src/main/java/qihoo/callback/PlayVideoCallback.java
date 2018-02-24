package qihoo.callback;

/**
 * Created by wangjunyu on 2018/2/6.
 */

public abstract interface PlayVideoCallback {
    public abstract void playSuc(int paramInt, String paramString);
    public abstract void playFail(int paramInt, String paramString);
}
