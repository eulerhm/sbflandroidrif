package fr.free.nrw.commons.media;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.free.nrw.commons.R;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Adapter for Caption Listview
 */
public class CaptionListViewAdapter extends BaseAdapter {

    List<Caption> captions;

    public CaptionListViewAdapter(final List<Caption> captions) {
        if (!ListenerUtil.mutListener.listen(8731)) {
            this.captions = captions;
        }
    }

    /**
     * @return size of captions list
     */
    @Override
    public int getCount() {
        return captions.size();
    }

    /**
     * @return Object at position i
     */
    @Override
    public Object getItem(final int i) {
        return null;
    }

    /**
     * @return id for current item
     */
    @Override
    public long getItemId(final int i) {
        return 0;
    }

    /**
     * inflate the view and bind data with UI
     */
    @Override
    public View getView(final int i, final View view, final ViewGroup viewGroup) {
        final TextView captionLanguageTextView;
        final TextView captionTextView;
        final View captionLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caption_item, null);
        captionLanguageTextView = captionLayout.findViewById(R.id.caption_language_textview);
        captionTextView = captionLayout.findViewById(R.id.caption_text);
        if (!ListenerUtil.mutListener.listen(8737)) {
            if ((ListenerUtil.mutListener.listen(8732) ? (captions.size() == 1 || captions.get(0).getValue().equals("No Caption")) : (captions.size() == 1 && captions.get(0).getValue().equals("No Caption")))) {
                if (!ListenerUtil.mutListener.listen(8735)) {
                    captionLanguageTextView.setText(captions.get(i).getLanguage());
                }
                if (!ListenerUtil.mutListener.listen(8736)) {
                    captionTextView.setText(captions.get(i).getValue());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8733)) {
                    captionLanguageTextView.setText(captions.get(i).getLanguage() + ":");
                }
                if (!ListenerUtil.mutListener.listen(8734)) {
                    captionTextView.setText(captions.get(i).getValue());
                }
            }
        }
        return captionLayout;
    }
}
