package yinlei.baseadapter.commenadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: CommenAdapter.java
 * @author: myName
 * @date: 2016-07-17 09:39
 */

public abstract class CommenAdapter<T> extends RecyclerView.Adapter<CommenViewHolder> {

    //这个具体的T要交给我们的子类去实现
    private List<T> mTList;

    //布局ID
    private int layoutId;

    public CommenAdapter(List<T> TList) {
        mTList = TList;
    }

    //由此传入布局，优化了之前在构造函数的super中直接传入布局 防止了用户没有传入布局而产生空指针异常
    public abstract int getLayoutId();


    @Override
    public CommenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(getLayoutId(), null);
        return new CommenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommenViewHolder holder, int position) {
        convert(holder, mTList.get(position));
    }

    protected abstract void convert(CommenViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mTList.size();
    }

    /**
     * 从列表中删除某项
     * @param t
     */
    public void removeItem(T t){
        int position = mTList.indexOf(t);
        mTList.remove(position);
        notifyItemRemoved(position);
    }

    public void addData(List<T> datas){
        addData(0,datas);
    }

    public void addData(int position,List<T> list){

        if(list !=null && list.size()>0) {
            for (T t:list) {
                mTList.add(position, t);
                notifyItemInserted(position);
            }

        }
    }

    public void clear(){
//        int itemCount = datas.size();
//        datas.clear();
//        this.notifyItemRangeRemoved(0,itemCount);

        if(mTList==null || mTList.size()<=0)
            return;

        for (Iterator it = mTList.iterator(); it.hasNext();){

            T t = (T) it.next();
            int position = mTList.indexOf(t);
            it.remove();
            notifyItemRemoved(position);
        }
    }
    public void refreshData(List<T> list){

        clear();
        if(list !=null && list.size()>0){
            int size = list.size();
            for (int i=0;i<size;i++){
                mTList.add(i,list.get(i));
                notifyItemInserted(i);
            }

        }
    }

    public void loadMoreData(List<T> list){

        if(list !=null && list.size()>0){

            int size = list.size();
            int begin = mTList.size();
            for (int i=0;i<size;i++){
                mTList.add(list.get(i));
                notifyItemInserted(i+begin);
            }
        }
    }

    private OnItemClickListener mOnItemClickListener = null;


    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }





}
