package yinlei.baseadapter.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.LinkedList;
import java.util.List;

import yinlei.baseadapter.R;
import yinlei.baseadapter.bean.NewsBean;
import yinlei.baseadapter.widget.BaseRecyclerAdapter;

public class NewsAdapter extends BaseRecyclerAdapter<NewsBean.News, NewsAdapter.MyViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    List<NewsBean.News> mNewses;

    public NewsAdapter(Context context, LinkedList mItemList) {
        super(context, mItemList);
        mNewses = mItemList;
    }

    @Override
    public void setOnRecyclerViewListener(BaseRecyclerAdapter.OnRecyclerViewListener l) {
        super.setOnRecyclerViewListener(l);
    }

    private OnItemClickLinstener mClickLinstener;

    public void setClickLinstener(OnItemClickLinstener clickLinstener) {
        mClickLinstener = clickLinstener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        NewsBean.News News = mItemLists.get(position);
        // 新闻图片
        //Glide.with(mContext).load(News.getResId()).into(holder.image);
        holder.title.setText(News.getTitle());
        holder.count.setText(News.getCount() + "");
        holder.time.setText(News.getTime());
        holder.position = position;

    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView time;
        TextView count;
        RelativeLayout rl_item;
        LinearLayout adContent;
        CardView card_view;
        int position;

        public MyViewHolder(final View NewsView) {
            super(NewsView);
            title = (TextView) NewsView.findViewById(R.id.news_title);
            image = (ImageView) NewsView.findViewById(R.id.news_image);
            time = (TextView) NewsView.findViewById(R.id.news_time);
            count = (TextView) NewsView.findViewById(R.id.news_count);
            adContent = (LinearLayout) NewsView.findViewById(R.id.news_content);
            card_view = (CardView) NewsView.findViewById(R.id.card_view);
            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickLinstener != null){
                        mClickLinstener.OnItemClick(v,getLayoutPosition(),mNewses.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickLinstener{
        void OnItemClick(View view, int position, NewsBean.News news);
    }

}