package com.lysm.ttclub.fragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.adapter.StickyListAdapter;
import com.lysm.ttclub.base.BaseFargment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class TansuoFragment extends BaseFargment implements
        AdapterView.OnItemClickListener, StickyListHeadersListView.OnHeaderClickListener,
        StickyListHeadersListView.OnStickyHeaderOffsetChangedListener,
        StickyListHeadersListView.OnStickyHeaderChangedListener{

    //适配器
    private StickyListAdapter mAdapter;
    private StickyListHeadersListView stickyList;
    private FragmentActivity activity;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tansuo;
    }

    @Override
    public void ininView() {
        activity = getActivity();


        initStickList();
    }

    /**
     * 初始化简历
     */
    private void initStickList()
    {
        mAdapter = new StickyListAdapter(activity);

        stickyList = (StickyListHeadersListView) rootView.findViewById(R.id.list);

        stickyList.setOnItemClickListener(this);
        stickyList.setOnHeaderClickListener(this);
        stickyList.setOnStickyHeaderChangedListener(this);
        stickyList.setOnStickyHeaderOffsetChangedListener(this);

        /* stickyList.addHeaderView(getLayoutInflater().inflate(R.layout.list_header, null));
        stickyList.addFooterView(getLayoutInflater().inflate(R.layout.list_footer, null));
        */
        // stickyList.setEmptyView(findViewById(R.id.empty));
        stickyList.setDrawingListUnderStickyHeader(true);
        stickyList.setAreHeadersSticky(true);
        stickyList.setAdapter(mAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky)
    {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId)
    {

    }

    @Override
    public void onStickyHeaderOffsetChanged(StickyListHeadersListView l, View header, int offset)
    {

    }
}
