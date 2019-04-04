package cn.lvkang.com.actionbar;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fruit[] fruits = {
            new Fruit("哈哈哈", R.drawable.a1),
            new Fruit("嘻嘻嘻", R.drawable.a2),
            new Fruit("呵呵呵", R.drawable.a3),
            new Fruit("呜呜呜", R.drawable.a4),
            new Fruit("嘎嘎嘎", R.drawable.a5),
            new Fruit("哼哼哼", R.drawable.a6),
            new Fruit("急急急", R.drawable.a7),
            new Fruit("吼吼吼", R.drawable.a8),
            new Fruit("咳咳咳", R.drawable.a9),
            new Fruit("啧啧啧", R.drawable.a10),
    };
    private List<Fruit> list = new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.draw_layout);

        NavigationView navView = findViewById(R.id.nav_view);

        //得到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //让导航按钮显示出来
            //导航的按钮就叫做HomeAsUp按钮,默认图标是一个返回的箭头,含义是返回到上一个活动
            //这里对他的样式和作用都做了修改
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮的图标
            actionBar.setHomeAsUpIndicator(R.drawable.arrow);
        }

        navView.setCheckedItem(R.id.nav_1);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入当前界面的任意布局就行,然后是内容，最后是时间
                Snackbar.make(v, "是否删除", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                list.clear();
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //卡片布局管理器，显示两列
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(list,MainActivity.this);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeColors(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行刷新操作
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        Random random = new Random();
        list.clear();
        for (int i = 0; i < 50; i++) {
            int index = random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    //加载Menu文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater可以得到MenuInflater的对象
        // 在调用他的inflate方法就可以创建菜单了.
        //inflate接收两个参数，1，传入布局文件  2，将菜单项添加到那个Menu对象中，
        getMenuInflater().inflate(R.menu.toolbar, menu);
        //返回true就会让对象显示出来.
        return true;
    }

    //处理Menu的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}

