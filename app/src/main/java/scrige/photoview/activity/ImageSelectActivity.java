package scrige.photoview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import scrige.photoview.R;
import scrige.photoview.adapter.ImageGridApter;
import scrige.photoview.bean.ImageFolderBean;
import scrige.photoview.listener.OnRecyclerViewClickListener;
import scrige.photoview.utils.ImageSelectObservable;
import scrige.photoview.utils.ImageUtils;

/**
 * 系统相册选择
 */
public class ImageSelectActivity extends Activity implements Callback, OnRecyclerViewClickListener, Observer {


    /**
     * 返回消息what
     */
    private final int MSG_PHOTO = 11;

    /**
     * 图片选择适配器
     */
    private ImageGridApter mAdapter;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.photo_gridview_main);
        ImageSelectObservable.getInstance().addObserver(this);
        mHandler = new Handler(this);
        initView();
        initData();

    }



    /**
     * <li>初始化数据</li>
     */
    private void initData() {
        ImageUtils.queryGalleryPicture(this, getIntent().getStringExtra("data"), mHandler, MSG_PHOTO);

    }

    /**
     * <li>初始化view</li>
     */
    private void initView() {


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_photo_folder);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new ImageGridApter(this, ImageSelectObservable.getInstance().getFolderAllImages());
        mAdapter.setOnClickListener(this);
        recyclerView.setAdapter(mAdapter);


    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_PHOTO:
                ImageSelectObservable.getInstance().addFolderImagesAndClearBefore((Collection<? extends ImageFolderBean>) msg.obj);
                mAdapter.notifyDataSetChanged();
                break;
        }
        return false;
    }


    @Override
    public void onItemClick(View v, int position) {


        if (position >= 0) {


            Intent intent = new Intent(this, PreviewImageActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
            overridePendingTransition(R.anim.common_scale_small_to_large, 0);


        }

    }


    @Override
    public void update(Observable o, Object arg) {
        mAdapter.notifyDataSetChanged();
    }
}
