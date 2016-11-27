package scrige.photoview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Scrige on 2016/11/26 0026.
 */

public class ImageViewBoard extends ImageView {

    int mColor;

    public ImageViewBoard(Context context) {
        this(context, null);
    }

    public ImageViewBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBoardColor(int color) {

        this.mColor = color;
       postInvalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = canvas.getClipBounds();

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

    }


}
