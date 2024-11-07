package org.wordpress.android.ui.suggestion.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.suggestion.Suggestion;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SuggestionAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater mInflater;

    private Filter mSuggestionFilter;

    private List<Suggestion> mSuggestionList;

    private List<Suggestion> mOrigSuggestionList;

    private final int mAvatarSz;

    @Nullable
    @AttrRes
    private Integer mBackgroundColor;

    @Inject
    protected ImageManager mImageManager;

    private final char mPrefix;

    public SuggestionAdapter(Context context, char prefix) {
        this.mPrefix = prefix;
        if (!ListenerUtil.mutListener.listen(23116)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mAvatarSz = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small);
        mInflater = LayoutInflater.from(context);
    }

    public void setBackgroundColorAttr(int backgroundColor) {
        if (!ListenerUtil.mutListener.listen(23117)) {
            mBackgroundColor = backgroundColor;
        }
    }

    public void setSuggestionList(List<Suggestion> suggestionList) {
        if (!ListenerUtil.mutListener.listen(23118)) {
            mOrigSuggestionList = suggestionList;
        }
    }

    public List<Suggestion> getSuggestionList() {
        return mOrigSuggestionList;
    }

    public List<Suggestion> getFilteredSuggestions() {
        return mSuggestionList;
    }

    @Override
    public int getCount() {
        if (!ListenerUtil.mutListener.listen(23119)) {
            if (mSuggestionList == null) {
                return 0;
            }
        }
        return mSuggestionList.size();
    }

    @Override
    public Suggestion getItem(int position) {
        if (!ListenerUtil.mutListener.listen(23120)) {
            if (mSuggestionList == null) {
                return null;
            }
        }
        return mSuggestionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SuggestionViewHolder holder;
        if ((ListenerUtil.mutListener.listen(23121) ? (convertView == null && convertView.getTag() == null) : (convertView == null || convertView.getTag() == null))) {
            if (!ListenerUtil.mutListener.listen(23122)) {
                convertView = mInflater.inflate(R.layout.suggestion_list_row, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(23125)) {
                if (mBackgroundColor != null) {
                    TypedValue typedValue = new TypedValue();
                    if (!ListenerUtil.mutListener.listen(23123)) {
                        convertView.getContext().getTheme().resolveAttribute(mBackgroundColor, typedValue, true);
                    }
                    View view = convertView.findViewById(R.id.suggestion_list_root_view);
                    if (!ListenerUtil.mutListener.listen(23124)) {
                        view.setBackgroundResource(typedValue.resourceId);
                    }
                }
            }
            holder = new SuggestionViewHolder(convertView);
            if (!ListenerUtil.mutListener.listen(23126)) {
                convertView.setTag(holder);
            }
        } else {
            holder = (SuggestionViewHolder) convertView.getTag();
        }
        Suggestion suggestion = getItem(position);
        if (!ListenerUtil.mutListener.listen(23130)) {
            if (suggestion != null) {
                String avatarUrl = GravatarUtils.fixGravatarUrl(suggestion.getAvatarUrl(), mAvatarSz);
                if (!ListenerUtil.mutListener.listen(23127)) {
                    mImageManager.loadIntoCircle(holder.mImgAvatar, ImageType.AVATAR_WITH_BACKGROUND, avatarUrl);
                }
                String value = mPrefix + suggestion.getValue();
                if (!ListenerUtil.mutListener.listen(23128)) {
                    holder.mValue.setText(value);
                }
                if (!ListenerUtil.mutListener.listen(23129)) {
                    holder.mDisplayValue.setText(suggestion.getDisplayValue());
                }
            }
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (!ListenerUtil.mutListener.listen(23132)) {
            if (mSuggestionFilter == null) {
                if (!ListenerUtil.mutListener.listen(23131)) {
                    mSuggestionFilter = new SuggestionFilter();
                }
            }
        }
        return mSuggestionFilter;
    }

    private static class SuggestionViewHolder {

        private final ImageView mImgAvatar;

        private final TextView mValue;

        private final TextView mDisplayValue;

        SuggestionViewHolder(View row) {
            mImgAvatar = row.findViewById(R.id.suggest_list_row_avatar);
            mValue = row.findViewById(R.id.suggestion_list_row_raw_value_label);
            mDisplayValue = row.findViewById(R.id.suggestion_list_row_display_value_label);
        }
    }

    private class SuggestionFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Suggestion> filteredSuggestions = getFilteredSuggestions(constraint);
            FilterResults results = new FilterResults();
            if (!ListenerUtil.mutListener.listen(23133)) {
                results.values = filteredSuggestions;
            }
            if (!ListenerUtil.mutListener.listen(23134)) {
                results.count = filteredSuggestions.size();
            }
            return results;
        }

        @NonNull
        private List<Suggestion> getFilteredSuggestions(CharSequence constraint) {
            if (mOrigSuggestionList == null) {
                return Collections.emptyList();
            } else if ((ListenerUtil.mutListener.listen(23140) ? (constraint == null && (ListenerUtil.mutListener.listen(23139) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(23138) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(23137) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(23136) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(23135) ? (constraint.length() != 0) : (constraint.length() == 0))))))) : (constraint == null || (ListenerUtil.mutListener.listen(23139) ? (constraint.length() >= 0) : (ListenerUtil.mutListener.listen(23138) ? (constraint.length() <= 0) : (ListenerUtil.mutListener.listen(23137) ? (constraint.length() > 0) : (ListenerUtil.mutListener.listen(23136) ? (constraint.length() < 0) : (ListenerUtil.mutListener.listen(23135) ? (constraint.length() != 0) : (constraint.length() == 0))))))))) {
                return mOrigSuggestionList;
            } else {
                List<Suggestion> filteredSuggestions = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(23145)) {
                    {
                        long _loopCounter345 = 0;
                        for (Suggestion suggestion : mOrigSuggestionList) {
                            ListenerUtil.loopListener.listen("_loopCounter345", ++_loopCounter345);
                            String lowerCaseConstraint = constraint.toString().toLowerCase(Locale.getDefault());
                            boolean suggestionMatchesConstraint = (ListenerUtil.mutListener.listen(23142) ? ((ListenerUtil.mutListener.listen(23141) ? (suggestion.getValue().toLowerCase(Locale.ROOT).startsWith(lowerCaseConstraint) && suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).startsWith(lowerCaseConstraint)) : (suggestion.getValue().toLowerCase(Locale.ROOT).startsWith(lowerCaseConstraint) || suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).startsWith(lowerCaseConstraint))) && suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).contains(" " + lowerCaseConstraint)) : ((ListenerUtil.mutListener.listen(23141) ? (suggestion.getValue().toLowerCase(Locale.ROOT).startsWith(lowerCaseConstraint) && suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).startsWith(lowerCaseConstraint)) : (suggestion.getValue().toLowerCase(Locale.ROOT).startsWith(lowerCaseConstraint) || suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).startsWith(lowerCaseConstraint))) || suggestion.getDisplayValue().toLowerCase(Locale.getDefault()).contains(" " + lowerCaseConstraint)));
                            if (!ListenerUtil.mutListener.listen(23144)) {
                                if (suggestionMatchesConstraint) {
                                    if (!ListenerUtil.mutListener.listen(23143)) {
                                        filteredSuggestions.add(suggestion);
                                    }
                                }
                            }
                        }
                    }
                }
                return filteredSuggestions;
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (!ListenerUtil.mutListener.listen(23146)) {
                mSuggestionList = (List<Suggestion>) results.values;
            }
            if (!ListenerUtil.mutListener.listen(23149)) {
                if (results.count == 0) {
                    if (!ListenerUtil.mutListener.listen(23148)) {
                        notifyDataSetInvalidated();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23147)) {
                        notifyDataSetChanged();
                    }
                }
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Suggestion suggestion = (Suggestion) resultValue;
            return suggestion.getValue();
        }
    }
}
