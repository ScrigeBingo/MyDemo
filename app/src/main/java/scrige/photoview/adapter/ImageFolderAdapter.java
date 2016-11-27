package scrige.photoview.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import scrige.photoview.R;
import scrige.photoview.bean.ImageFolderBean;


/**
 * 图片选择目录适配器
 */
public class ImageFolderAdapter extends BaseRecyclerAdapter<ImageFolderBean, RecyclerView.ViewHolder> {


    public ImageFolderAdapter(Context context, List<ImageFolderBean> list) {
        super(context, list);
    }


    @Override
    public PhotoFolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_folder_item, parent, false);
        return new PhotoFolderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PhotoFolderViewHolder holder = (PhotoFolderViewHolder) viewHolder;
        ImageFolderBean imageFolderBean = list.get(position);
        holder.fileNameTv.setText(imageFolderBean.fileName);
        holder.fileNumsTv.setText(String.format(mContext.getResources().getString(R.string.photo_num), imageFolderBean.pisNum));

        Glide.with(mContext).load(imageFolderBean.path).placeholder(R.drawable.defaultpic).into( holder.imageIv);


        if (mOnClickListener != null) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.onItemClick(view, holder.getAdapterPosition());
                }
            });
        }

    }


    /**
     * 自定义ViewHolder
     */
    protected class PhotoFolderViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIv;
        public TextView fileNameTv;
        public TextView fileNumsTv;
        public CardView mCardView;

        public PhotoFolderViewHolder(View itemView) {
            super(itemView);
            fileNameTv = (TextView) itemView.findViewById(R.id.tv_file_name);
            fileNumsTv = (TextView) itemView.findViewById(R.id.tv_pic_nums);
            imageIv = (ImageView) itemView.findViewById(R.id.iv_icon);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
