package com.aqinga.yuekaolianxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/2019:11
 */

public class CircleFragment extends Fragment implements XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circlefragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        init();

        recyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setLoadingListener(this);
    }
    public void init(){
        if (InetgerUtils.isNetworkAvailable(getContext())){
            OkHttpUtils.sendOkHttpRequest("http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(json, Bean.class);
                    final List<Bean.NewsBean> list = bean.getNews();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new MyAdapter(list,getActivity());
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
            });
        }else {
            Toast.makeText(getActivity(), "您的网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRefresh() {
        init();
        adapter.notifyDataSetChanged();
        recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        init();
        adapter.notifyDataSetChanged();
        recyclerView.loadMoreComplete();
    }
}
