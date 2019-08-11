package com.bai.psychedelic.cloudmusic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bai.psychedelic.cloudmusic.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRvHeight;

    public MusicListAdapter(Context context,RecyclerView recyclerView){
        mContext = context;
        mRv = recyclerView;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music,viewGroup,false);
        return new ViewHolder(mItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setRecycleViewHeight();
    }


    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 获取itemview的高度和数量，相乘得到recycleView高度
     */
    private void setRecycleViewHeight(){
        if (isCalcaulationRvHeight || mRv == null) return;
        isCalcaulationRvHeight = true;
        ViewGroup.LayoutParams itemViewLp = (RecyclerView.LayoutParams)mItemView.getLayoutParams();
        int itemCount = getItemCount();
        int recyclerViewHeight = itemViewLp.height * itemCount;
        ViewGroup.LayoutParams rvLp = mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
