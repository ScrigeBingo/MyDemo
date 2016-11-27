package scrige.photoview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import scrige.photoview.R;
import scrige.photoview.adapter.ImageFolderAdapter;
import scrige.photoview.bean.ImageFolderBean;
import scrige.photoview.listener.OnRecyclerViewClickListener;
import scrige.photoview.utils.ImageSelectObservable;
import scrige.photoview.utils.ImageUtils;

/**
 * 本地图片浏览 list列表
 */
public class FolderListActivity extends Activity implements Callback, OnRecyclerViewClickListener {


    /**
     * 图片所在文件夹适配器
     */
    private ImageFolderAdapter mFloderAdapter;
    /**
     * 图片列表
     */
    ArrayList<ImageFolderBean> mImageFolderList;
    /**
     * 消息处理
     */
    private Handler mHandler;

    private final int MSG_PHOTO = 10;

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(this);
        mImageFolderList = new ArrayList<>();

        setContentView(R.layout.photo_folder_main);
        initView();

        ImageUtils.loadLocalFolderContainsImage(this, mHandler, MSG_PHOTO);
        ImageSelectObservable.getInstance().addSelectImagesAndClearBefore((ArrayList<ImageFolderBean>) getIntent().getSerializableExtra("list"));

        mFloderAdapter = new ImageFolderAdapter(this, mImageFolderList);
        mRecyclerView.setAdapter(mFloderAdapter);
        mFloderAdapter.setOnClickListener(this);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.lv_photo_folder);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_PHOTO:
                mImageFolderList.clear();
                mImageFolderList.addAll((Collection<? extends ImageFolderBean>) msg.obj);
                mFloderAdapter.notifyDataSetChanged();
                break;
        }
        return false;
    }


    @Override
    public void onItemClick(View view, int position) {
        File file = new File(mImageFolderList.get(position).path);


        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra("data", file.getParentFile().getAbsolutePath());
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageSelectObservable.getInstance().clearSelectImgs();
        ImageSelectObservable.getInstance().clearFolderImages();
    }
}
