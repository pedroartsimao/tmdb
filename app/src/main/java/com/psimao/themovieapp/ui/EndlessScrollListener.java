package com.psimao.themovieapp.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;

    private int currentPage = 1;

    private GridLayoutManager layoutManager;

    protected EndlessScrollListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
        }

        if (!loading && lastVisibleItem == totalItemCount - 1) {
            currentPage++;
            onScrollEndReached(currentPage);
            loading = true;
        }
    }

    public abstract void onScrollEndReached(int currentPage);
}
