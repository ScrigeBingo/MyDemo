package scrige.photoview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import scrige.photoview.R;
import scrige.photoview.bean.ImageFolderBean;


/**
 * <p>
 * 图片选择适配器
 */
public class GalleryAdapter extends BaseRecyclerAdapter<ImageFolderBean, RecyclerView.ViewHolder> {

    private List<ImageFolderBean> mlist;


    public GalleryAdapter(Context context, List<ImageFolderBean> list) {
        super(context, list);

        this.mlist = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_gallery_item, parent, false);
        return new GridViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ImageFolderBean imageBean = mlist.get(position);

        Glide.with(mContext).load(imageBean.path).placeholder(R.drawable.defaultpic).into(((GridViewHolder) holder).picIv);
        setOnItemClickListener(((GridViewHolder) holder).picIv, holder.getAdapterPosition());


    }


    /**
     * item点击监听，选择的图片边框高亮
     *
     * @param view     点击view
     * @param position 点击位置
     */
    private void setOnItemClickListener(final View view, final int position) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {

                    mOnClickListener.onItemClick(v, position);


                }
            }
        };
        view.setOnClickListener(listener);
    }

    private class GridViewHolder extends RecyclerView.ViewHolder {
        public View containerView;
        public ImageView picIv;

        public GridViewHolder(View convertView) {
            super(convertView);

            containerView = convertView.findViewById(R.id.main_gallery_frame_layout);
            picIv = (ImageView) convertView.findViewById(R.id.iv_gallery_pic);


        }
    }
}
