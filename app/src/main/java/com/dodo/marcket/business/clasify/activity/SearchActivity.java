package com.dodo.marcket.business.clasify.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.clasify.constrant.SearchContract;
import com.dodo.marcket.business.clasify.presenter.SearchPresenter;
import com.dodo.marcket.wedget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.mEd_search)
    ClearEditText mEdSearch;
    @BindView(R.id.mRv_search)
    RecyclerView mRvSearch;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;

    private List<ProductBean> mDates = new ArrayList<>();
    private LinearLayoutManager manager;
    private ProductAdapter adapter;
    private int page = 1;
    private int pageSize = 10;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("搜索");
        initRv();


        String search = getIntent().getStringExtra("search");

        if (search != null && !search.equals("")) {
            mEdSearch.setText(search);
            mPresenter.searchProduct(search, page, pageSize);
        }

        //搜索框文字改变及时请求
        mEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    return;
                }
                page = 1;
                mDates.clear();
                adapter.notifyDataSetChanged();
                mPresenter.searchProduct(s.toString(), page, pageSize);
            }
        });

        //软键盘点击确定按钮
        mEdSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))

                {

                    if ((mEdSearch.getText().toString().trim()).equals("")) {
                        return false;
                    }
                    page = 1;
                    mDates.clear();
                    adapter.notifyDataSetChanged();
                    mPresenter.searchProduct(mEdSearch.getText().toString().trim(), page, pageSize);
                    return true;

                }

                return false;

            }

        });


    }

    private void initRv() {
        manager = new LinearLayoutManager(mContext);
        adapter = new ProductAdapter(mContext, mDates);

        mRvSearch.setLayoutManager(manager);
        mRvSearch.setAdapter(adapter);


        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {

            }

            @Override
            public void onSelectedClick(int pos, boolean isSelectAll) {

            }

            @Override
            public void onJianClicked(int pos) {

            }

            @Override
            public void onJiaClicked(int pos) {

            }

            @Override
            public void onAddClicked(int pos) {

            }

            @Override
            public void onMutiSizeClicked(int pos) {

            }
        });
    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {

    }

    @Override
    public void getSearchResult(List<ProductBean> productList) {
        if (productList == null || productList.size() == 0) {
            if (page==1){
                mLLNoDate.setVisibility(View.VISIBLE);
            }
            return;
        }else {
            mLLNoDate.setVisibility(View.GONE);
        }

        if (page==1){
            mDates.clear();
        }
        page++;
//        mDates.clear();
        mDates.addAll(productList);
        adapter.notifyDataSetChanged();
    }

}
