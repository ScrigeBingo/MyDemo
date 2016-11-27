package scrige.photoview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import scrige.photoview.R;
import scrige.photoview.adapter.GalleryAdapter;
import scrige.photoview.adapter.PreviewAdapter;
import scrige.photoview.bean.ImageFolderBean;
import scrige.photoview.listener.OnRecyclerViewClickListener;
import scrige.photoview.utils.ImageSelectObservable;
import scrige.photoview.view.ImageViewBoard;


/**
 * 预览图片
 */
public class PreviewImageActivity extends Activity implements OnClickListener, OnRecyclerViewClickListener {
    private ViewPager mPhotoPager;

    /**
     * 标题栏
     */
    private TextView mTitleView;
    /**选择按钮*/
    /**
     * 控制显示、隐藏顶部标题栏
     */
    private boolean isHeadViewShow = true;
    private View mFooterView;

    /**
     * 需要预览的所有图片
     */
    private List<ImageFolderBean> mImagePathList;

    private RecyclerView mRecyclerView;
    private GalleryAdapter mGalleryAdapter;
    private LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_image_activity);

        initImages();

        initView();

        initAdapter();

		/*全屏*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 初始化图片数组
     */
    private void initImages() {
        mImagePathList = new ArrayList<>();

        mImagePathList.addAll(ImageSelectObservable.getInstance().getFolderAllImages());

    }

    /**
     * 初始化控件
     */
    private void initView() {
        /*标题栏*/
        mTitleView = (TextView) findViewById(R.id.preview_number);
        mLinearLayout = (LinearLayout) findViewById(R.id.tv_photo_title);

        mRecyclerView = (RecyclerView) findViewById(R.id.gallery_preview);


        String title = getIntent().getIntExtra("position", 1) + "/" + mImagePathList.size();
        mTitleView.setText(title);

		/*底部小图预览框*/
        mFooterView = findViewById(R.id.rl_check);
        mFooterView.setOnClickListener(this);

        mPhotoPager = (ViewPager) findViewById(R.id.vp_preview);


    }


    /**
     * adapter的初始化
     */
    private void initAdapter() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mGalleryAdapter = new GalleryAdapter(this, mImagePathList);
        mRecyclerView.setAdapter(mGalleryAdapter);
        mGalleryAdapter.setOnClickListener(this);

        mPhotoPager = (ViewPager) findViewById(R.id.vp_preview);
        PreviewAdapter previewAdapter = new PreviewAdapter(this, mImagePathList, isHeadViewShow, mLinearLayout, mFooterView);
        mPhotoPager.setAdapter(previewAdapter);
        mPhotoPager.setPageMargin(5);
        mPhotoPager.setCurrentItem(getIntent().getIntExtra("position", 0));

        mPhotoPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                String text = (position + 1) + "/" + mImagePathList.size();
                mTitleView.setText(text);

                mRecyclerView.scrollToPosition(position);


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_check:

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ImageSelectObservable.getInstance().updateImageSelectChanged();
        overridePendingTransition(0, R.anim.common_scale_large_to_small);
    }


    /**
     * 图片点击事件
     */
    @Override
    public void onItemClick(View view, int position) {


        ImageViewBoard imageViewBoard = new ImageViewBoard(this);

        imageViewBoard.setBoardColor(Color.BLUE);

        mPhotoPager.setCurrentItem(position);


    }
}

