package com.example.eduardo.futebol.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.eduardo.futebol.R;
import com.example.eduardo.futebol.dao.PosicaoDao;
import com.example.eduardo.futebol.model.Posicao;
import com.example.eduardo.futebol.ui.fragment.EscalacaoFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*BottomNavigationView mBottomNavigationView;
    ViewPager mViewPager;

    Fragment mFragments[] = new Fragment[5];*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mViewPager = findViewById(R.id.main_view_pager);
        setupViewPager();*/
        findViewById(R.id.btn_carregar_conteudo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //getSupportFragmentManager().beginTransaction().add(R.id.container, EscalacaoFragment.newInstance()).commit();
    }

   /* private void setupViewPager() {
        mFragments[0] = EscalacaoFragment.newInstance();
        /*mFragments[1] = fragment;
        mFragments[2] = fragment;
        mFragments[3] = fragment;
        mFragments[4] = fragment;

        FragmentStatePagerAdapter pageAdapter = setViewPagerAdapter();
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(pageAdapter);

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_escalacao:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.tab_tabela:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.tab_jogos:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.tab_mercado:
                        mViewPager.setCurrentItem(0);
                        break;

                    case R.id.tab_opcoes:
                        mViewPager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });
    }
    @NonNull
    private FragmentStatePagerAdapter setViewPagerAdapter() {
        return new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[0];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        };
    }*/
}

/*class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}*/
