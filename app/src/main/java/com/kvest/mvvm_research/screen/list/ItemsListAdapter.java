package com.kvest.mvvm_research.screen.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kvest.mvvm_research.R;
import com.kvest.mvvm_research.common.datamodel.Item;
import com.kvest.mvvm_research.common.recycler_view_utils.RecyclerViewCollection;
import com.kvest.mvvm_research.databinding.ListItemBinding;

/**
 * Created by kvest on 20.06.16.
 */
public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private RecyclerViewCollection<Item> items;

    public ItemsListAdapter(RecyclerViewCollection<Item> items) {
        this.items = items;

        if (this.items != null) {
            this.items.attachAdapter(this);
        }
    }

    public void setItems(@NonNull RecyclerViewCollection<Item> items) {
        if (items != null) {
            this.items = items;
            this.items.attachAdapter(this);
        } else {
            if (this.items != null) {
                this.items.detachAdapter();
            }
            this.items = null;
        }

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Item item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
