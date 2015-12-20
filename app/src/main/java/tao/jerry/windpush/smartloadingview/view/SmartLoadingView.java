package tao.jerry.windpush.smartloadingview.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2015/12/17.
 */
public class SmartLoadingView extends PopupWindow {
    private static final int DIRECTION_CENTER = 0;
    private SmartBaseView mContentView = null;
    private Context mContext = null;
    private int mBgResource, mLoadingResource;
    private int mLoadingWidth;
    private int mLoadingHeight;


    public SmartLoadingView(Context context, int bgResource, int loadingResource) {
        this.mContext = context;
        this.mBgResource = bgResource;
        this.mLoadingResource = loadingResource;
        initLoadingStyle();
    }

    public SmartLoadingView(Context context, int direction) {
        this.mContext = context;
        initLoadingStyle();
    }

    private void initLoadingStyle() {
        this.setContentView(getLoadingView());
    }

    public void showAtCenter() {
        this.showAtLocation(((Activity) mContext).findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    public View getmContentView() {
        return mContentView;
    }

    public void setmContentView(SmartBaseView mContentView) {
        this.mContentView = mContentView;
    }

    public int getmBgResource() {
        return mBgResource;
    }

    public void setmBgResource(int mBgResource) {
        this.mBgResource = mBgResource;
    }

    public int getmLoadingResource() {
        return mLoadingResource;
    }

    public void setmLoadingResource(int mLoadingResource) {
        this.mLoadingResource = mLoadingResource;
    }

    public int getmLoadingWidth() {
        return mLoadingWidth;
    }

    public void setmLoadingWidth(int mLoadingWidth) {
        this.mLoadingWidth = mLoadingWidth;
    }

    public int getmLoadingHeight() {
        return mLoadingHeight;
    }

    public void setmLoadingHeight(int mLoadingHeight) {
        this.mLoadingHeight = mLoadingHeight;
    }

    private View getLoadingView() {
        mContentView = new SmartBaseView(mContext) {
            @Override
            public int bgImageResource() {
                return mBgResource;
            }

            @Override
            public int loadingImageResource() {
                return mLoadingResource;
            }

            @Override
            public int viewWidth() {
                return mLoadingWidth == 0 ? 300 : mLoadingWidth;
            }

            @Override
            public int viewHeight() {
                return mLoadingHeight == 0 ? 300 : mLoadingHeight;
            }
        };
        return mContentView;
    }

    public void updateProgress(int progressVaule) {
        if (mContentView == null) {
            return;
        }
        mContentView.upDateProgress(progressVaule);
    }
}
