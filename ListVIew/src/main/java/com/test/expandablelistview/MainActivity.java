package com.test.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView listview;
    private MyAdpater adpater;
    private TextView checked_shop;
    private TextView price;

    List<Shangjia> shangjias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      /*  //点击事件
        boolean isCheked = shangjias.get(0).getShangPinList().get(0).isCheked;
        shangjias.get(0).getShangPinList().get(0).setCheked(!isCheked);
        change();*/


        setContentView(R.layout.activity_main);
        listview = (ExpandableListView) findViewById(R.id.listview);
        adpater = new MyAdpater(this);
        listview.setAdapter(adpater);
        final CheckBox checkAll = (CheckBox) findViewById(R.id.checkAll);
        price = (TextView) findViewById(R.id.price);
        checked_shop = (TextView) findViewById(R.id.checked_shop);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置商品全部选中
                adpater.checkAllShop(checkAll.isChecked());
                //计算选中的价格和数量
                String shopPrice = adpater.getShopPrice();
                //判断商品是否全部选中
                boolean b = adpater.selectAll();

                String[] split = shopPrice.split(",");
                price.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });
        adpater.getAdapterData(new MyAdpater.AdapterData() {
            @Override
            public void Data(View v, String str, boolean b) {
                String[] split = str.split(",");
                price.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });

        checkAll.setChecked(adpater.selectAll());
        adpater.notifyDataSetChanged();

    }


    /*//点击二级列表的时候调用这个方法
    public void change() {

        //// TODO: 2017/9/21  去遍历这个商品,是否所有商品被选中
        boolean isAllShngpingChecked = false;
        Shangjia shangjia = shangjias.get(0);
        for (int i = 0; i < shangjia.getShangPinList().size(); i++) {

        }
        shangjia.setChcked(isAllShngpingChecked);


        //todo 去遍历所有商家
        boolean isAllShngjiaChecked = false;
        for (int i = 0; i < shangjias.size(); i++) {

        }

        //// TODO: 2017/9/21   根据isAllShngjiaChecked通知外边的那个checkbox改变状态

    }

    //点击二级列表的时候调用这个方法
    public void dianjiYIjiLIbiao() {
        Shangjia shangjia = shangjias.get(0);
        boolean isChcked = shangjia.isChcked;

        //// TODO: 2017/9/21 遍历当前商家商品的状态
        List<ShangPin> shangPinList = shangjias.get(0).getShangPinList();
        for (int i = 0; i < shangPinList.size(); i++) {
            shangPinList.get(i).setCheked(!isChcked);
        }
        change();
    }

    //点击全选按钮
    //// TODO: 2017/9/21  在activityd中有一个boolean值
    boolean isAllCheckedActivity = false;

    //根据isAllCheckedActivity  取非
    //// TODO: 2017/9/21   区把商品的所有状态都变了之后 调用   change();
   
*/
}
