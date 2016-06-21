package com.kvest.mvvm_research.common.recycler_view_utils;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by roman on 6/21/16.
 */
public class RecyclerViewDataset<E> extends ArrayList<E> {
    private RecyclerView.Adapter adapter;

    public void attachAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void detachAdapter() {
        this.adapter = null;
    }

    @Override
    public boolean add(E object) {
        boolean result = super.add(object);

        if (adapter != null) {
            adapter.notifyItemInserted(size() - 1);
        }

        return result;
    }

    @Override
    public void add(int index, E object) {
        super.add(index, object);

        if (adapter != null) {
            adapter.notifyItemInserted(index);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        //remember list size
        int oldSize = size();

        boolean result = super.addAll(collection);

        if (result && adapter != null) {
            adapter.notifyItemRangeInserted(oldSize, collection.size());
        }

        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        boolean result = super.addAll(index, collection);

        if (result && adapter != null) {
            adapter.notifyItemRangeInserted(index, collection.size());
        }

        return result;
    }

    @Override
    public void clear() {
        super.clear();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public E remove(int index) {
        E result = super.remove(index);

        if (adapter != null) {
            adapter.notifyItemRemoved(index);
        }

        return result;
    }

    @Override
    public boolean remove(Object object) {
        int position = indexOf(object);

        boolean result = super.remove(object);

        if (result && position != -1 && adapter != null) {
            adapter.notifyItemRemoved(position);
        }

        return result;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);

        if (adapter != null) {
            adapter.notifyItemRangeRemoved(fromIndex, toIndex - fromIndex + 1);
        }
    }

    @Override
    public E set(int index, E object) {
        E result = super.set(index, object);

        if (adapter != null) {
            adapter.notifyItemChanged(index);
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = super.retainAll(collection);

        if (result && adapter != null) {
            adapter.notifyDataSetChanged();
        }

        return result;
    }

    public void move(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            return;
        }

        E item = super.remove(fromIndex);
        if (fromIndex < toIndex) {
            --toIndex;
        }
        add(toIndex, item);

        if (adapter != null) {
            adapter.notifyItemMoved(fromIndex, toIndex);
        }
    }
}
