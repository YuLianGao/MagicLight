package com.skydoves.magiclight_ble_control;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorPickerView;

import static java.lang.Math.abs;

public class MyColorPickerView1 extends FrameLayout {

    private int selectedColor;
    private Point selectedPoint;
    private ImageView imageView;
    private ImageView selector;
    private Drawable imageViewDrawable;
    private Drawable selectorDrawable;
    protected ColorPickerView.ColorListener mColorListener;
    int point_x, point_y, flag =1;
    public static int selected_x=0, selected_y=0;
    public static int center_x, center_y;
    private boolean m_bOutSelected = false;

    private final  String TAG = "MagicLight";
    public MyColorPickerView1(@NonNull Context context) {
        super(context);
    }

    public MyColorPickerView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
        onCreate();
    }

    public MyColorPickerView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttrs(attrs);
        onCreate();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyColorPickerView1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    public void initSelector(int x, int y) {
        flag =0;
        point_y = y;
        point_x = x;
//        init();
        onCreate();
        selector.setX(point_x);
        selector.setY(point_y);
    }

    public Point getSelectorPos() {
        flag =0;
        Point pos = new Point(305,413);
        pos.x = (int)selector.getX() + 1 ;
        pos.y =(int)selector.getY() + 1 ;

        return  pos;

    }

    private void onFirstLayout() {
       /*selectedPoint = new Point(getMeasuredWidth()/2, getMeasuredHeight()/2);
        onTouchReceived(
                MotionEvent.obtain(System.currentTimeMillis(),
                        System.currentTimeMillis() + 100,
                        MotionEvent.ACTION_UP,
                        getMeasuredWidth() / 2,
                        getMeasuredHeight() / 2,
                        0)
        );*/
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

    public  boolean getOutSideSel()
    {
        return m_bOutSelected;
    }
    public  void setSelectedPoint(Point pt)
    {
        selector.setX(pt.x - 305);
        selector.setY(pt.y - 413);
        //selectedColor = getColorFromBitmap(pt.x, pt.y );
      //  fireColorListener(getColor());
    }
    private boolean onTouchReceived(MotionEvent event) {
        Point snapPoint = new Point((int)event.getX(), (int)event.getY());
        selectedColor = getColorFromBitmap(snapPoint.x, snapPoint.y);


        // check is selector pointed position is transparent field?
        int x,y,center_x, center_y, x1, y1;
        center_x = (int) (this.getWidth() / 2) - (selector.getMeasuredWidth()/2 );
        center_y = (int) (this.getHeight() / 2) - (selector.getMeasuredHeight()/2 );
        {
            x = snapPoint.x - (selector.getMeasuredWidth()) ;
            y = snapPoint.y - (selector.getMeasuredHeight()) ;


            //ImageView imageView = ((ImageView)imageView);

           // Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
           // int pixel = bitmap.getPixel(x,y);

            int redValue = Color.red(selectedColor);
            int blueValue = Color.blue(selectedColor);
            int greenValue = Color.green(selectedColor);

                x1 = (x - center_x) ;
                y1 = (y - center_y);
                int r = (int) Math.sqrt(x1 * x1 + y1 * y1) - 28   ;
               if(r > center_x) {
                   m_bOutSelected = true;
                    float x2 = center_x * x1 / r;
                    float y2 = center_x * y1 / r;
                    selector.setX(x2+center_x);
                    selector.setY(y2+center_y);
                    selectedColor = getColorFromBitmap(x2+center_x + 42 , y2+center_y + 42);

                    Log.e(TAG, "x: " + x + "  y:  " + y + "  selectorX: " + (x2+center_x) + "  selectorY: " + (y2+center_y) + "  radial:  " + r);
                }
                else
                {
                        m_bOutSelected = false;
                        selector.setX(x);
                        selector.setY(y);
                        selectedColor = getColorFromBitmap(x + 40  , y + 40);
                        Log.e(TAG, "2:  x: " + x + "  y:  " + y + "  Red: " + redValue + "  Green:  " + greenValue +   "  Blue:  " + blueValue );



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
    private byte[] getLedBytes(int newColor, int value) {
        byte[] rgb = new byte[7];
        int color = (int)Long.parseLong(String.format("%06X", (0xFFFFFF & newColor)), 16);
        rgb[0] = (byte)0x56;
        rgb[1] = (byte)(value * ((color >> 16) & 0xFF) / 100);
        rgb[2]= (byte)(value * ((color >> 8) & 0xFF) / 100);
        rgb[3] = (byte)(value * ((color >> 0) & 0xFF) / 100);
        rgb[4] = 0x00;
        rgb[5] = (byte)0xF0;
        rgb[6] = (byte)0xAA;
        return rgb;
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

    public int getColor() {
        return selectedColor;
    }


}
