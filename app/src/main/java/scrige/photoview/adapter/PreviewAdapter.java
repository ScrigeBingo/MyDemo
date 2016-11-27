package scrige.photoview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import scrige.photoview.R;
import scrige.photoview.bean.ImageFolderBean;

/**
 * Created by Scrige on 2016/11/25 0025.
 */

public class PreviewAdapter extends PagerAdapter {


    private List<ImageFolderBean> photos;
    Context mContext;
    boolean isHeadViewShow;
    LinearLayout mTitleView;
    View mFooterView;

    public PreviewAdapter(Context context, List<ImageFolderBean> photoList, boolean isHeadViewShow, LinearLayout mTitleView, View mFooterView) {
        super();
        this.photos = photoList;
        this.mContext = context;
        this.isHeadViewShow = isHeadViewShow;
        this.mTitleView = mTitleView;
        this.mFooterView = mFooterView;


    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.preview_image_item, container, false);
        ImageView bigPhotoIv = (ImageView) view.findViewById(R.id.iv_image_item);
        bigPhotoIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isHeadViewShow) {
                    hideControls();
                } else {
                    showControls();
                }
            }
        });


        Glide.with(mContext).load(photos.get(position).path).into(bigPhotoIv);
        container.addView(view);
        return view;
    }

    /**
     * <br>显示顶部，底部view动画 </br>
     */
    private void showControls() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        isHeadViewShow = true;
        mTitleView.startAnimation(animation);
        mTitleView.setVisibility(View.VISIBLE);
        mFooterView.startAnimation(animation);
        mFooterView.setVisibility(View.VISIBLE);
    }

    /**
     * <br> 隐藏顶部，底部view 动画</br>
     */
    private void hideControls() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        isHeadViewShow = false;
        mTitleView.startAnimation(animation);
        mTitleView.setVisibility(View.GONE);

        mFooterView.startAnimation(animation);
        mFooterView.setVisibility(View.GONE);
    }


}
