package com.example.dawn.seller.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dawn.car.R;
import com.example.dawn.car.domain.RefreshableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanxiao on 2015/10/30.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewOrderFragment extends Fragment
{
    @Nullable

    RefreshableView refreshableView;
    private String[] test=new String[]{"1","2","3","4","5"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_goods_inf_list,container,false);
        refreshableView = (RefreshableView)view.findViewById(R.id.refreshable_view);


        final List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i=0;i<test.length;i++)
        {
            Map<String ,Object> listItem =new HashMap<>();
            listItem.put("buyerCompany",test[i]);
            listItem.put("buyerModel",test[i]);
            listItem.put("orderDate",test[i]);
            listItem.put("pricedNum",test[i]);
            listItem.put("buyerAccessory1",test[i]);
            listItems.add(listItem);
        }

        //将读取出的数据利用SimpleAdapter加载到list1里面
        SimpleAdapter simpleAdapter= new SimpleAdapter(getActivity(),
                listItems,
                R.layout.sell_item_1,
                new String[]{"buyerCompany","buyerModel","orderDate","pricedNum",
                        "buyerAccessory1"},
                new int[]{R.id.company_tv,R.id.model_tv,R.id.orderDate_tv,R.id.model_number,
                        R.id.accessory_tv}
        );
        final ListView list =(ListView) view.findViewById(R.id.list);//注意加view!!!!!!!!!!!!!
        list.setAdapter(simpleAdapter);



        assert refreshableView != null;
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);





        return view;
    }
}
