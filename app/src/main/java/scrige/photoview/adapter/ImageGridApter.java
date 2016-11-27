package scrige.photoview.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
public class ImageGridApter extends BaseRecyclerAdapter<ImageFolderBean, RecyclerView.ViewHolder> {


    /**
     * 已选图片列表
     */
    private List<ImageFolderBean> mlist;


    public ImageGridApter(Context context, List<ImageFolderBean> list) {
        super(context, list);


        this.mlist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_grid_item, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GridViewHolder viewHolder = (GridViewHolder) holder;
        ImageFolderBean imageBean = list.get(position);
        imageBean.position = viewHolder.getAdapterPosition();

        viewHolder.picIv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(mContext).load(imageBean.path).placeholder(R.drawable.defaultpic).into(viewHolder.picIv);

         /*点击监听*/
        setOnItemClickListener(viewHolder.mCardView, viewHolder.getAdapterPosition());

    }


    /**
     * item点击监听，查看大图返回选择图片
     *
     * @param view     点击view
     * @param position 点击位置
     */
    private void setOnItemClickListener(View view, final int position) {
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

        public ImageView picIv;
        public CardView mCardView;

        public GridViewHolder(View convertView) {
            super(convertView);

            picIv = (ImageView) convertView.findViewById(R.id.iv_pic);
            mCardView = (CardView) convertView.findViewById(R.id.card_view);
        }
    }
}
