[1mdiff --git a/app/src/main/java/cn/lvkang/com/actionbar/MainActivity.java b/app/src/main/java/cn/lvkang/com/actionbar/MainActivity.java[m
[1mindex 342fa9a..3ffe59d 100644[m
[1m--- a/app/src/main/java/cn/lvkang/com/actionbar/MainActivity.java[m
[1m+++ b/app/src/main/java/cn/lvkang/com/actionbar/MainActivity.java[m
[36m@@ -33,8 +33,13 @@[m [mpublic class MainActivity extends AppCompatActivity {[m
             //导航的按钮就叫做HomeAsUp按钮,默认图标是一个返回的箭头,含义是返回到上一个活动[m
             //这里对他的样式和作用都做了修改[m
             actionBar.setDisplayHomeAsUpEnabled(true);[m
[32m+[m
[32m+[m
[32m+[m
             //设置一个导航按钮的图标[m
             actionBar.setHomeAsUpIndicator(R.drawable.arrow);[m
[32m+[m
[32m+[m
         }[m
 [m
         navView.setCheckedItem(R.id.nav_1);[m
[36m@@ -42,7 +47,7 @@[m [mpublic class MainActivity extends AppCompatActivity {[m
             @Override[m
             public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {[m
                 drawerLayout.closeDrawers();[m
[31m-                return true;[m
[32m+[m[32m                return false;[m
             }[m
         });[m
     }[m
