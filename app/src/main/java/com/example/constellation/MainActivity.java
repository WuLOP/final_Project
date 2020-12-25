package com.example.constellation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //实现类
    RadioGroup mainRg;
    //生命四个按钮对应的Fragment对象
    Fragment starFrag,luckFrag,partnerFrag,meFrag;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRg =findViewById(R.id.main_rg);
        //设置监听点击了哪个单选按钮
        mainRg.setOnCheckedChangeListener(this);
       //创建碎片对象
        starFrag =new StarFragment();
        luckFrag = new LuckFragment();
        partnerFrag =new PartnerFragment();
        meFrag =new MeFragment();
        //将四个FRAGMENT进行动态加载，一起加载到布局中 。replace add/hide/show
        addFragmentPage();
    }
   /**
    * @des 将主页当中的碎片一起加载进入布局 有用的显示，暂时无用的隐藏**/

    private void addFragmentPage() {
        //1创建碎片管理者对象
        manager = getSupportFragmentManager();
        //创建碎片处理事务的对象
        FragmentTransaction transaction = manager.beginTransaction();
        //将四个Fragment统一的添加到布局中
        transaction.add(R.id.main_layout_center,starFrag);
        transaction.add(R.id.main_layout_center,partnerFrag);
        transaction.add(R.id.main_layout_center,luckFrag);
        transaction.add(R.id.main_layout_center,meFrag);
        //隐藏后面的三个
        transaction.hide(partnerFrag);
        transaction.hide(luckFrag);
        transaction.hide(meFrag);
        //提交碎片改变后的事务
        transaction.commit();



    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.main_rb_star:
                transaction.hide(partnerFrag);
                transaction.hide(luckFrag);
                transaction.hide(meFrag);
                transaction.show(starFrag);

                break;
            case R.id.main_rb_partner:
                transaction.show(partnerFrag);
                transaction.hide(luckFrag);
                transaction.hide(meFrag);
                transaction.hide(starFrag);
                break;
            case R.id.main_rb_luck:
                transaction.hide(partnerFrag);
                transaction.show(luckFrag);
                transaction.hide(meFrag);
                transaction.hide(starFrag);
                break;
            case R.id.main_rb_me:
                transaction.hide(partnerFrag);
                transaction.hide(luckFrag);
                transaction.show(meFrag);
                transaction.hide(starFrag);
                break;
                //fragment
        }
        transaction.commit();
    }
}