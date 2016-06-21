package com.kvest.mvvm_research.common.recycler_view_utils;

/**
 * Created by roman on 6/21/16.
 */
public class FirebaseRecyclerViewCollection<E> extends RecyclerViewCollection<E> {
    public void onItemAdded(E item, E previousItem) {
        int newIndex = 0;
        if (previousItem != null) {
            newIndex = indexOf(previousItem) + 1;
        }

        add(newIndex, item);
    }

    public void onItemChanged(E item) {
        int index = indexOf(item);
        if (index != -1) {
            set(index, item);
        }
    }

    public void onItemDeleted(E item) {
        remove(item);
    }

    public void onItemMoved(E item, E previousItem) {
        int newIndex = 0;
        if (previousItem != null) {
            newIndex = indexOf(previousItem) + 1;
        }
        int currentIndex = indexOf(item);

        if (currentIndex != -1) {
            move(currentIndex, newIndex);
        }
    }
}
