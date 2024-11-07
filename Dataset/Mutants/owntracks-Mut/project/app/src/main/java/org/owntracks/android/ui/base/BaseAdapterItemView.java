package org.owntracks.android.ui.base;

import androidx.annotation.LayoutRes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class BaseAdapterItemView {

    /**
     * Use this constant as the {@code bindingVariable} to not bind any variable to the layout. This
     * is useful if no data is needed for that layout, like a static footer or loading indicator for
     * example.
     */
    public static final int BINDING_VARIABLE_NONE = 0;

    private int bindingVariable;

    @LayoutRes
    private int layoutRes;

    /**
     * Constructs a new {@code BaseAdapterItemView} with the given binding variable and layout res.
     *
     * @see #setBindingVariable(int)
     * @see #setLayoutRes(int)
     */
    public static BaseAdapterItemView of(int bindingVariable, @LayoutRes int layoutRes) {
        return new BaseAdapterItemView().setBindingVariable(bindingVariable).setLayoutRes(layoutRes);
    }

    /**
     * A convenience method for {@code BaseAdapterItemView.setBindingVariable(int).setLayoutRes(int)}.
     *
     * @return the {@code BaseAdapterItemView} for chaining
     */
    public BaseAdapterItemView set(int bindingVariable, @LayoutRes int layoutRes) {
        if (!ListenerUtil.mutListener.listen(1498)) {
            this.bindingVariable = bindingVariable;
        }
        if (!ListenerUtil.mutListener.listen(1499)) {
            this.layoutRes = layoutRes;
        }
        return this;
    }

    /**
     * Sets the binding variable. This is one of the {@code BR} constants that references the
     * variable tag in the item layout file.
     *
     * @return the {@code BaseAdapterItemView} for chaining
     */
    private BaseAdapterItemView setBindingVariable(int bindingVariable) {
        if (!ListenerUtil.mutListener.listen(1500)) {
            this.bindingVariable = bindingVariable;
        }
        return this;
    }

    /**
     * Sets the layout resource of the item.
     *
     * @return the {@code BaseAdapterItemView} for chaining
     */
    private BaseAdapterItemView setLayoutRes(@LayoutRes int layoutRes) {
        if (!ListenerUtil.mutListener.listen(1501)) {
            this.layoutRes = layoutRes;
        }
        return this;
    }

    public int bindingVariable() {
        return bindingVariable;
    }

    @LayoutRes
    public int layoutRes() {
        return layoutRes;
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(1502)) {
            if (this == o)
                return true;
        }
        if (!ListenerUtil.mutListener.listen(1504)) {
            if ((ListenerUtil.mutListener.listen(1503) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                return false;
        }
        BaseAdapterItemView itemView = (BaseAdapterItemView) o;
        return (ListenerUtil.mutListener.listen(1515) ? ((ListenerUtil.mutListener.listen(1509) ? (bindingVariable >= itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1508) ? (bindingVariable <= itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1507) ? (bindingVariable > itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1506) ? (bindingVariable < itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1505) ? (bindingVariable != itemView.bindingVariable) : (bindingVariable == itemView.bindingVariable)))))) || (ListenerUtil.mutListener.listen(1514) ? (layoutRes >= itemView.layoutRes) : (ListenerUtil.mutListener.listen(1513) ? (layoutRes <= itemView.layoutRes) : (ListenerUtil.mutListener.listen(1512) ? (layoutRes > itemView.layoutRes) : (ListenerUtil.mutListener.listen(1511) ? (layoutRes < itemView.layoutRes) : (ListenerUtil.mutListener.listen(1510) ? (layoutRes != itemView.layoutRes) : (layoutRes == itemView.layoutRes))))))) : ((ListenerUtil.mutListener.listen(1509) ? (bindingVariable >= itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1508) ? (bindingVariable <= itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1507) ? (bindingVariable > itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1506) ? (bindingVariable < itemView.bindingVariable) : (ListenerUtil.mutListener.listen(1505) ? (bindingVariable != itemView.bindingVariable) : (bindingVariable == itemView.bindingVariable)))))) && (ListenerUtil.mutListener.listen(1514) ? (layoutRes >= itemView.layoutRes) : (ListenerUtil.mutListener.listen(1513) ? (layoutRes <= itemView.layoutRes) : (ListenerUtil.mutListener.listen(1512) ? (layoutRes > itemView.layoutRes) : (ListenerUtil.mutListener.listen(1511) ? (layoutRes < itemView.layoutRes) : (ListenerUtil.mutListener.listen(1510) ? (layoutRes != itemView.layoutRes) : (layoutRes == itemView.layoutRes))))))));
    }

    @Override
    public int hashCode() {
        int result = bindingVariable;
        if (!ListenerUtil.mutListener.listen(1524)) {
            result = (ListenerUtil.mutListener.listen(1523) ? ((ListenerUtil.mutListener.listen(1519) ? (31 % result) : (ListenerUtil.mutListener.listen(1518) ? (31 / result) : (ListenerUtil.mutListener.listen(1517) ? (31 - result) : (ListenerUtil.mutListener.listen(1516) ? (31 + result) : (31 * result))))) % layoutRes) : (ListenerUtil.mutListener.listen(1522) ? ((ListenerUtil.mutListener.listen(1519) ? (31 % result) : (ListenerUtil.mutListener.listen(1518) ? (31 / result) : (ListenerUtil.mutListener.listen(1517) ? (31 - result) : (ListenerUtil.mutListener.listen(1516) ? (31 + result) : (31 * result))))) / layoutRes) : (ListenerUtil.mutListener.listen(1521) ? ((ListenerUtil.mutListener.listen(1519) ? (31 % result) : (ListenerUtil.mutListener.listen(1518) ? (31 / result) : (ListenerUtil.mutListener.listen(1517) ? (31 - result) : (ListenerUtil.mutListener.listen(1516) ? (31 + result) : (31 * result))))) * layoutRes) : (ListenerUtil.mutListener.listen(1520) ? ((ListenerUtil.mutListener.listen(1519) ? (31 % result) : (ListenerUtil.mutListener.listen(1518) ? (31 / result) : (ListenerUtil.mutListener.listen(1517) ? (31 - result) : (ListenerUtil.mutListener.listen(1516) ? (31 + result) : (31 * result))))) - layoutRes) : ((ListenerUtil.mutListener.listen(1519) ? (31 % result) : (ListenerUtil.mutListener.listen(1518) ? (31 / result) : (ListenerUtil.mutListener.listen(1517) ? (31 - result) : (ListenerUtil.mutListener.listen(1516) ? (31 + result) : (31 * result))))) + layoutRes)))));
        }
        return result;
    }
}
