package cn.lvkang.com.actionbar;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
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
        if (actionBar!= null){
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
    }
    //加载Menu文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater可以得到MenuInflater的对象
        // 在调用他的inflate方法就可以创建菜单了.
        //inflate接收两个参数，1，传入布局文件  2，将菜单项添加到那个Menu对象中，
        getMenuInflater().inflate(R.menu.toolbar,menu);
        //返回true就会让对象显示出来.
        return true;
    }

    //处理Menu的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "返回  ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除  ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置   ", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}

