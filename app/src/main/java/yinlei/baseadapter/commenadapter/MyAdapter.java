package yinlei.baseadapter.commenadapter;



import java.util.List;

import yinlei.baseadapter.R;
import yinlei.baseadapter.bean.NewsBean;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.jiaui.recyclerviewhaderdemo.commenadapter.MyAdapter.java
 * @author: myName
 * @date: 2016-07-17 09:45
 */

public class MyAdapter extends CommenAdapter<NewsBean.News> {


    public MyAdapter(List<NewsBean.News> TList) {
        super(TList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news_recyclerview;
    }

    /**
     * @param holder
     * @param news
     */
    @Override
    protected void convert(CommenViewHolder holder, NewsBean.News news) {
        holder.setText(R.id.news_content, news.getTitle());
    }


}
