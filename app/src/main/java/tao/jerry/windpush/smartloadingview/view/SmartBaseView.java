package tao.jerry.windpush.smartloadingview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/12/3.
 */
public abstract class SmartBaseView extends View {
    private Bitmap mBgBitmap = null;
    private Bitmap mLoadBitmap = null;
    private Rect mBgRect =null;
    private Rect mLoadingRect=null;
    private Rect mViewRect =null;
    private Paint paint=new Paint();

    public SmartBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SmartBaseView(Context context) {
        super(context);
        init();
    }

    public SmartBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBgBitmap = BitmapFactory.decodeResource(getContext().getResources(), bgImageResource());
        mLoadBitmap = BitmapFactory.decodeResource(getContext().getResources(), loadingImageResource());
        mBgRect=new Rect(0,0,mBgBitmap.getWidth(),mBgBitmap.getHeight());
        mViewRect = new Rect(0,0,viewWidth(),viewHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(mBgBitmap, mBgRect, mViewRect, null);
        if (mLoadingRect!=null) {
            RectF rectF = new RectF(mLoadingRect);
            canvas.drawRoundRect(rectF,60,60,paint);
            canvas.drawBitmap(mLoadBitmap, mLoadingRect, mLoadingRect, null);
        }
//        canvas.drawRect( mViewRect,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(viewWidth(), viewHeight());
    }

    public void upDateProgress(int progressVaule){
        int currentRight = (int)(mLoadBitmap.getWidth()*progressVaule/100.0);
        mLoadingRect=new Rect(0,0,currentRight>mLoadBitmap.getWidth()? mLoadBitmap.getWidth():currentRight,mLoadBitmap.getHeight());
        invalidate();
    }

    public abstract int bgImageResource();

    public abstract int loadingImageResource();

    public abstract int viewWidth();

    public abstract int viewHeight();

    public void release() {
        mBgBitmap.recycle();
        mBgBitmap=null;
        mLoadBitmap.recycle();
        mLoadBitmap=null;
    }
}
