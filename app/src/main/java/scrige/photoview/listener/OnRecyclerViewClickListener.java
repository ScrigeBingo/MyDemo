package scrige.photoview.listener;

import android.view.View;

/**

 * recyclerView item点击监听时间
 * Created by wj on 2016/9/12.
 */
public interface OnRecyclerViewClickListener {

    /**
     * 点击item监听时间
     * @param view View
     * @param position position
     */
    void onItemClick(View view, int position);


}
