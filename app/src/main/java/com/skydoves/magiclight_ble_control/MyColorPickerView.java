package com.skydoves.magiclight_ble_control;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.skydoves.colorpickerview.ColorPickerView;

import static java.lang.Math.abs;

public class MyColorPickerView extends FrameLayout {

    private int selectedColor;
    private Point selectedPoint;
    private ImageView imageView;
    private ImageView selector;
    private Drawable imageViewDrawable;
    private Drawable selectorDrawable;
    protected ColorPickerView.ColorListener mColorListener;

    public MyColorPickerView(@NonNull Context context) {
        super(context);
    }

    public MyColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
        onCreate();
    }

    public MyColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttrs(attrs);
        onCreate();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyColorPickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        getAttrs(attrs);
        onCreate();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                onFirstLayout();
            }
        });
    }

    private void onFirstLayout() {
        selectedPoint = new Point(getMeasuredWidth()/2, getMeasuredHeight()/2);
        onTouchReceived(
                MotionEvent.obtain(System.currentTimeMillis(),
                        System.currentTimeMillis() + 100,
                        MotionEvent.ACTION_UP,
                        getMeasuredWidth() / 2,
                        getMeasuredHeight() / 2,
                        0)
        );
        loadListeners();
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ColorPickerView);
        try {
            if (a.hasValue(R.styleable.ColorPickerView_src))
                imageViewDrawable = a.getDrawable(R.styleable.ColorPickerView_src);
            if (a.hasValue(R.styleable.ColorPickerView_selector))
                selectorDrawable = a.getDrawable(R.styleable.ColorPickerView_selector);
        } finally {
            a.recycle();
        }
    }

    private void onCreate() {
        imageView = new ImageView(getContext());
        if (imageViewDrawable != null)
            imageView.setImageDrawable(imageViewDrawable);

        FrameLayout.LayoutParams wheelParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wheelParams.gravity = Gravity.CENTER;
        addView(imageView, wheelParams);

        selector = new ImageView(getContext());
        if (selectorDrawable != null) {
            selector.setImageDrawable(selectorDrawable);

            FrameLayout.LayoutParams thumbParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            thumbParams.gravity = Gravity.CENTER;
            addView(selector, thumbParams);
        }
    }

    private void loadListeners() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        selector.setPressed(true);
                        return onTouchReceived(event);
                    case MotionEvent.ACTION_MOVE:
                        selector.setPressed(true);
                        return onTouchReceived(event);
                    default:
                        selector.setPressed(false);
                        return false;
                }
            }
        });
    }

    public  Point getSelectedPoint(int rr, int gg, int bb)
    {
        Point colorPoint  = new Point();

        int pupColor = 0, redClr = 0, greenClr = 0, blueClr = 0;
        int total_width = imageView.getWidth();
        int total_height = imageView.getHeight();

        colorPoint.x = imageView.getWidth()/2 - selector.getWidth()/2;
        colorPoint.y = imageView.getHeight()/2 - selector.getHeight()/2;

        if(rr==gg && gg==bb)
        {
            return colorPoint;
        }

        if (rr == 255) {
            if (gg > bb) {
                gg = gg - bb;    bb = 0;}
            else {
                bb = bb - gg;     gg = 0;}
        }
        else if (gg == 255) {
            if (rr > bb) {
                rr = rr - bb;    bb = 0;}
            else {
                bb = bb - rr;     rr = 0;}
        }
        else if (bb == 255) {
            if (gg > rr) {
                gg = gg - rr;    rr = 0;}
            else {
                rr = rr - gg;     gg = 0;}
        }

        for (int y = 0; y < total_height; y++) {
            for (int x = 0; x < total_width; x++) {
                int xx = x - colorPoint.x;
                int yy = y - colorPoint.y;
                int r = (int) Math.sqrt((xx) * xx + yy * yy);
                if(r >= colorPoint.x - 5 && r <= colorPoint.x + 5)
                {
                    pupColor = getColorFromBitmap(x + 42 , y + 42 );
                    redClr = getLedBytes(pupColor, 100)[1] & 0xFF;
                    greenClr = getLedBytes(pupColor, 100)[2] & 0xFF;
                    blueClr = getLedBytes(pupColor, 100)[3] & 0xFF;
                    int dist = (int) Math.sqrt((redClr - rr) * (redClr - rr) + (greenClr - gg) * (greenClr - gg) + (blueClr - bb) * (blueClr - bb));
                    if(dist < 1 && abs(redClr - rr)< 1 && abs(greenClr - gg)< 1 && abs(blueClr - bb) < 1) {
                        colorPoint.x = x;
                        colorPoint.y = y;
                        return colorPoint;
                    }
                }
            }
        }
        return colorPoint;
    }

    private byte[] getLedBytes(int newColor, int value) {
        byte[] rgb = new byte[7];
        int color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)(value * ((color >> 16) & 0xFF) / 100);
        if((rgb[1]& 0xFF) < 4) rgb[1] = 0;
        rgb[2]= (byte)(value * ((color >> 8) & 0xFF) / 100);
        if((rgb[2]& 0xFF) < 4) rgb[2] = 0;
        rgb[3] = (byte)(value * ((color >> 0) & 0xFF) / 100);
        if((rgb[3]& 0xFF) < 4) rgb[3] = 0;
        rgb[4] = 0x00;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
    }

    public  void setSelectedPoint(Point pt)
    {
        selector.setX(pt.x);
        selector.setY(pt.y);
        selectedColor = getColorFromBitmap(pt.x + 42, pt.y + 42);
        fireColorListener(getColor());
    }

    public  Point getSelectedPoint()
    {
        Point pt = new Point();
        pt.x = (int)selector.getX();
        pt.y = (int)selector.getY();
        return  pt;
    }

    private boolean onTouchReceived(MotionEvent event) {
        Point snapPoint = new Point((int)event.getX(), (int)event.getY());
        selectedColor = getColorFromBitmap(snapPoint.x, snapPoint.y);

        // check is selector pointed position is transparent field?
        int x,y,center_x, center_y, x1, y1;
        center_x = (int) (this.getWidth() / 2) - (selector.getMeasuredWidth()/2 );
        center_y = (int) (this.getHeight() / 2) - (selector.getMeasuredHeight()/2 );
      //  if(getColor() != Color.TRANSPARENT)
        {
            x = snapPoint.x - (selector.getMeasuredWidth()/2);
            y = snapPoint.y - (selector.getMeasuredHeight()/2);
            if(x!=center_x && y!=center_y) {
                x1 = (x - center_x);
                y1 = (y - center_y);
                int r = (int) Math.sqrt(x1 * x1 + y1 * y1);
                if(r>center_x/2) {
                    float x2 = center_x * x1 / r;
                    float y2 = center_x * y1 / r;
                    selector.setX(x2+center_x);
                    selector.setY(y2+center_x);
                    selectedColor = getColorFromBitmap(x2+center_x + 42 , y2+center_x + 42);
                } else {
                    if(r<center_x/4 ) {
                        selector.setX(x);
                        selector.setY(y);
                        selectedColor = 0;
                    } else
                    {
                        float x2 = (float) ((center_x/2.3) * x1 / r);
                        float y2 = (float) ((center_x/2.3) * y1 / r);
                        selector.setX(x2+center_x);
                        selector.setY(y2+center_x);
                        selectedColor = 0;
                    }

                }
            }
//            selectedPoint = new Point(snapPoint.x, snapPoint.y);
            fireColorListener(getColor());
        }
        /*else{
            selector.setX(center_x);
            selector.setY(center_y);
            selectedColor = getColorFromBitmap(selectedPoint.x , selectedPoint.y);
            fireColorListener(selectedColor);
        }*/
        return true;
    }

    private int getColorFromBitmap(float x, float y) {
        if (imageViewDrawable == null) return 0;

        Matrix invertMatrix = new Matrix();
        imageView.getImageMatrix().invert(invertMatrix);

        float[] mappedPoints = new float[]{x, y};
        invertMatrix.mapPoints(mappedPoints);

        if (imageView.getDrawable() != null && imageView.getDrawable() instanceof BitmapDrawable &&
                mappedPoints[0] > 0 && mappedPoints[1] > 0 &&
                mappedPoints[0] < imageView.getDrawable().getIntrinsicWidth() && mappedPoints[1] < imageView.getDrawable().getIntrinsicHeight()) {

            invalidate();
            return ((BitmapDrawable) imageView.getDrawable()).getBitmap().getPixel((int) mappedPoints[0], (int) mappedPoints[1]);
        }
        return 0;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    private void fireColorListener(int color) {
        if (mColorListener != null) {
            mColorListener.onColorSelected(color);
        }
    }

    public void setColorListener(ColorPickerView.ColorListener colorListener) {
        mColorListener = colorListener;
    }

    public interface ColorListener {
        void onColorSelected(int color);
    }

    public int getColor() {
        return selectedColor;
    }
//
//    public String getColorHtml(){
//        return String.format("%06X", (0xFFFFFF & selectedColor));
//    }
//
//    public int[] getColorRGB() {
//        int[] rgb = new int[3];
//        int color = (int) Long.parseLong(String.format("%06X", (0xFFFFFF & selectedColor)), 16);
//        rgb[0] = (color >> 16) & 0xFF; // hex to int : R
//        rgb[1] = (color >> 8) & 0xFF; // hex to int : G
//        rgb[2] = (color >> 0) & 0xFF; // hex to int : B
//        return rgb;
//    }
//
//    public void setSelectorPoint(int x, int y) {
//        selector.setX(x);
//        selector.setY(y);
//        selectedPoint = new Point(x, y);
//        selectedColor = getColorFromBitmap(x, y);
//        fireColorListener(getColor());
//    }
//
//    public void setPaletteDrawable(Drawable drawable) {
//        removeView(imageView);
//        imageView = new ImageView(getContext());
//        imageViewDrawable = drawable;
//        imageView.setImageDrawable(imageViewDrawable);
//        addView(imageView);
//
//        removeView(selector);
//        addView(selector);
//
//        selector.setX(getMeasuredWidth()/2 - selector.getWidth()/2);
//        selector.setY(getMeasuredHeight()/2- selector.getHeight()/2);
//    }
//
//    public void setSelectorDrawable(Drawable drawable) {
//        selector.setImageDrawable(drawable);
//    }
//
//    public void selectCenter() {
//        setSelectorPoint(getMeasuredWidth()/2 - selector.getWidth(), getMeasuredHeight()/2- selector.getHeight());
//    }
}
