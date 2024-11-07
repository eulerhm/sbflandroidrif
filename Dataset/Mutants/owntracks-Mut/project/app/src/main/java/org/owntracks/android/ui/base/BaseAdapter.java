package org.owntracks.android.ui.base;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.OnRebindCallback;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.lang.ref.WeakReference;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    static final Object DATA_INVALIDATION = new Object();

    @NonNull
    private final BaseAdapterItemView itemViewArg;

    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);

    private WeakReference<ClickListener> clickListenerWeakReference;

    private List<T> items;

    private LayoutInflater inflater;

    private ItemIds<T> itemIds;

    // Currently attached recyclerview, we don't have to listen to notifications if null.
    @Nullable
    private RecyclerView recyclerView;

    protected BaseAdapter(@NonNull BaseAdapterItemView arg) {
        this.itemViewArg = arg;
    }

    @NonNull
    public BaseAdapterItemView getItemViewArg() {
        return itemViewArg;
    }

    protected void setItems(@Nullable List<T> items) {
        if (!ListenerUtil.mutListener.listen(1414)) {
            if (this.items == items) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1419)) {
            // No need to make a sound if nobody is listening right?
            if (recyclerView != null) {
                if (!ListenerUtil.mutListener.listen(1416)) {
                    if (this.items instanceof ObservableList) {
                        if (!ListenerUtil.mutListener.listen(1415)) {
                            ((ObservableList<T>) this.items).removeOnListChangedCallback(callback);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1418)) {
                    if (items instanceof ObservableList) {
                        if (!ListenerUtil.mutListener.listen(1417)) {
                            ((ObservableList<T>) items).addOnListChangedCallback(callback);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1420)) {
            this.items = items;
        }
        if (!ListenerUtil.mutListener.listen(1421)) {
            notifyDataSetChanged();
        }
    }

    private T getAdapterItem(int position) {
        return items.get(position);
    }

    private ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);
    }

    private void onBindBinding(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes, int position, T item) {
        if (!ListenerUtil.mutListener.listen(1425)) {
            if (bindingVariable != BaseAdapterItemView.BINDING_VARIABLE_NONE) {
                boolean result = binding.setVariable(bindingVariable, item);
                if (!ListenerUtil.mutListener.listen(1423)) {
                    if (!result) {
                        if (!ListenerUtil.mutListener.listen(1422)) {
                            Timber.e("Unable to bind %s to %s", bindingVariable, item);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1424)) {
                    binding.executePendingBindings();
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(1428)) {
            if ((ListenerUtil.mutListener.listen(1426) ? (this.recyclerView == null || items instanceof ObservableList) : (this.recyclerView == null && items instanceof ObservableList))) {
                if (!ListenerUtil.mutListener.listen(1427)) {
                    ((ObservableList<T>) items).addOnListChangedCallback(callback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1429)) {
            this.recyclerView = recyclerView;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(1432)) {
            if ((ListenerUtil.mutListener.listen(1430) ? (this.recyclerView != null || items instanceof ObservableList) : (this.recyclerView != null && items instanceof ObservableList))) {
                if (!ListenerUtil.mutListener.listen(1431)) {
                    ((ObservableList<T>) items).removeOnListChangedCallback(callback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1433)) {
            this.recyclerView = null;
        }
    }

    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutId) {
        if (!ListenerUtil.mutListener.listen(1435)) {
            if (inflater == null) {
                if (!ListenerUtil.mutListener.listen(1434)) {
                    inflater = LayoutInflater.from(viewGroup.getContext());
                }
            }
        }
        ViewDataBinding binding = onCreateBinding(inflater, layoutId, viewGroup);
        if (!ListenerUtil.mutListener.listen(1436)) {
            binding.getRoot().setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1437)) {
            binding.getRoot().setOnLongClickListener(this);
        }
        final ViewHolder holder = onCreateViewHolder(binding);
        if (!ListenerUtil.mutListener.listen(1441)) {
            binding.addOnRebindCallback(new OnRebindCallback() {

                @Override
                public boolean onPreBind(ViewDataBinding binding) {
                    return isRecyclerReady();
                }

                @Override
                public void onCanceled(ViewDataBinding binding) {
                    if (!ListenerUtil.mutListener.listen(1438)) {
                        if (!isRecyclerReady()) {
                            return;
                        }
                    }
                    int position = holder.getAdapterPosition();
                    if (!ListenerUtil.mutListener.listen(1440)) {
                        if (position != RecyclerView.NO_POSITION) {
                            if (!ListenerUtil.mutListener.listen(1439)) {
                                notifyItemChanged(position, DATA_INVALIDATION);
                            }
                        }
                    }
                }
            });
        }
        return holder;
    }

    boolean isRecyclerReady() {
        return (ListenerUtil.mutListener.listen(1442) ? (recyclerView != null || recyclerView.isComputingLayout()) : (recyclerView != null && recyclerView.isComputingLayout()));
    }

    private ViewHolder onCreateViewHolder(ViewDataBinding binding) {
        return new BindingViewHolder(binding);
    }

    @Override
    public void onClick(View view) {
        ClickListener<T> listener = clickListenerWeakReference.get();
        if (!ListenerUtil.mutListener.listen(1444)) {
            if (listener != null)
                if (recyclerView != null) {
                    if (!ListenerUtil.mutListener.listen(1443)) {
                        listener.onClick(getAdapterItem(recyclerView.getChildLayoutPosition(view)), view, false);
                    }
                }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ClickListener<T> listener = clickListenerWeakReference.get();
        if (!ListenerUtil.mutListener.listen(1446)) {
            if (listener != null)
                if (recyclerView != null) {
                    if (!ListenerUtil.mutListener.listen(1445)) {
                        listener.onClick(getAdapterItem(recyclerView.getChildLayoutPosition(view)), view, true);
                    }
                }
        }
        return false;
    }

    private static class BindingViewHolder extends RecyclerView.ViewHolder {

        BindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }
    }

    @Override
    public final void onBindViewHolder(ViewHolder viewHolder, int position) {
        T item = items.get(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
        if (!ListenerUtil.mutListener.listen(1447)) {
            onBindBinding(binding, itemViewArg.bindingVariable(), itemViewArg.layoutRes(), position, item);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (!ListenerUtil.mutListener.listen(1450)) {
            if (isForDataBinding(payloads)) {
                ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
                if (!ListenerUtil.mutListener.listen(1449)) {
                    binding.executePendingBindings();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1448)) {
                    super.onBindViewHolder(holder, position, payloads);
                }
            }
        }
    }

    private boolean isForDataBinding(List<Object> payloads) {
        if (!ListenerUtil.mutListener.listen(1457)) {
            if ((ListenerUtil.mutListener.listen(1456) ? (payloads == null && (ListenerUtil.mutListener.listen(1455) ? (payloads.size() >= 0) : (ListenerUtil.mutListener.listen(1454) ? (payloads.size() <= 0) : (ListenerUtil.mutListener.listen(1453) ? (payloads.size() > 0) : (ListenerUtil.mutListener.listen(1452) ? (payloads.size() < 0) : (ListenerUtil.mutListener.listen(1451) ? (payloads.size() != 0) : (payloads.size() == 0))))))) : (payloads == null || (ListenerUtil.mutListener.listen(1455) ? (payloads.size() >= 0) : (ListenerUtil.mutListener.listen(1454) ? (payloads.size() <= 0) : (ListenerUtil.mutListener.listen(1453) ? (payloads.size() > 0) : (ListenerUtil.mutListener.listen(1452) ? (payloads.size() < 0) : (ListenerUtil.mutListener.listen(1451) ? (payloads.size() != 0) : (payloads.size() == 0))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1464)) {
            {
                long _loopCounter15 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1463) ? (i >= payloads.size()) : (ListenerUtil.mutListener.listen(1462) ? (i <= payloads.size()) : (ListenerUtil.mutListener.listen(1461) ? (i > payloads.size()) : (ListenerUtil.mutListener.listen(1460) ? (i != payloads.size()) : (ListenerUtil.mutListener.listen(1459) ? (i == payloads.size()) : (i < payloads.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                    Object obj = payloads.get(i);
                    if (!ListenerUtil.mutListener.listen(1458)) {
                        if (obj != DATA_INVALIDATION) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        return itemViewArg.layoutRes();
    }

    /**
     * Set the item id's for the items. If not null, this will set {@link
     * RecyclerView.Adapter#setHasStableIds(boolean)} to true.
     */
    public void setItemIds(@Nullable ItemIds<T> itemIds) {
        if (!ListenerUtil.mutListener.listen(1465)) {
            this.itemIds = itemIds;
        }
        if (!ListenerUtil.mutListener.listen(1466)) {
            setHasStableIds(itemIds != null);
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public long getItemId(int position) {
        return itemIds == null ? position : itemIds.getItemId(position, items.get(position));
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        final WeakReference<BaseAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BaseAdapter<T> adapter) {
            this.adapterRef = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            BaseAdapter<T> adapter = adapterRef.get();
            if (!ListenerUtil.mutListener.listen(1467)) {
                if (adapter == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1468)) {
                ensureChangeOnMainThread();
            }
            if (!ListenerUtil.mutListener.listen(1469)) {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, final int positionStart, final int itemCount) {
            BaseAdapter<T> adapter = adapterRef.get();
            if (!ListenerUtil.mutListener.listen(1470)) {
                if (adapter == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1471)) {
                ensureChangeOnMainThread();
            }
            if (!ListenerUtil.mutListener.listen(1472)) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, final int positionStart, final int itemCount) {
            BaseAdapter<T> adapter = adapterRef.get();
            if (!ListenerUtil.mutListener.listen(1473)) {
                if (adapter == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1474)) {
                ensureChangeOnMainThread();
            }
            if (!ListenerUtil.mutListener.listen(1475)) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, final int fromPosition, final int toPosition, final int itemCount) {
            BaseAdapter<T> adapter = adapterRef.get();
            if (!ListenerUtil.mutListener.listen(1476)) {
                if (adapter == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1477)) {
                ensureChangeOnMainThread();
            }
            if (!ListenerUtil.mutListener.listen(1492)) {
                {
                    long _loopCounter16 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(1491) ? (i >= itemCount) : (ListenerUtil.mutListener.listen(1490) ? (i <= itemCount) : (ListenerUtil.mutListener.listen(1489) ? (i > itemCount) : (ListenerUtil.mutListener.listen(1488) ? (i != itemCount) : (ListenerUtil.mutListener.listen(1487) ? (i == itemCount) : (i < itemCount)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                        if (!ListenerUtil.mutListener.listen(1486)) {
                            adapter.notifyItemMoved((ListenerUtil.mutListener.listen(1481) ? (fromPosition % i) : (ListenerUtil.mutListener.listen(1480) ? (fromPosition / i) : (ListenerUtil.mutListener.listen(1479) ? (fromPosition * i) : (ListenerUtil.mutListener.listen(1478) ? (fromPosition - i) : (fromPosition + i))))), (ListenerUtil.mutListener.listen(1485) ? (toPosition % i) : (ListenerUtil.mutListener.listen(1484) ? (toPosition / i) : (ListenerUtil.mutListener.listen(1483) ? (toPosition * i) : (ListenerUtil.mutListener.listen(1482) ? (toPosition - i) : (toPosition + i))))));
                        }
                    }
                }
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, final int positionStart, final int itemCount) {
            BaseAdapter<T> adapter = adapterRef.get();
            if (!ListenerUtil.mutListener.listen(1493)) {
                if (adapter == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1494)) {
                ensureChangeOnMainThread();
            }
            if (!ListenerUtil.mutListener.listen(1495)) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }

    interface ItemIds<T> {

        long getItemId(int position, T item);
    }

    static void ensureChangeOnMainThread() {
        if (!ListenerUtil.mutListener.listen(1496)) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
            }
        }
    }

    public interface ClickListener<T> {

        void onClick(@NonNull T object, @NonNull View view, boolean longClick);
    }

    protected void setClickListener(ClickListener listener) {
        if (!ListenerUtil.mutListener.listen(1497)) {
            this.clickListenerWeakReference = new WeakReference<>(listener);
        }
    }
}
