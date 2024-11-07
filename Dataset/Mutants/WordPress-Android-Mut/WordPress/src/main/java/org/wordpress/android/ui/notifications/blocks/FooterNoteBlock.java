package org.wordpress.android.ui.notifications.blocks;

import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.tools.FormattableContent;
import org.wordpress.android.fluxc.tools.FormattableRange;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.FormattableContentUtilsKt;
import org.wordpress.android.util.RtlUtils;
import org.wordpress.android.util.image.ImageManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FooterNoteBlock extends NoteBlock {

    private NoteBlockClickableSpan mClickableSpan;

    public FooterNoteBlock(FormattableContent noteObject, ImageManager imageManager, NotificationsUtilsWrapper notificationsUtilsWrapper, OnNoteBlockTextClickListener onNoteBlockTextClickListener) {
        super(noteObject, imageManager, notificationsUtilsWrapper, onNoteBlockTextClickListener);
    }

    public void setClickableSpan(FormattableRange rangeObject, String noteType) {
        if (!ListenerUtil.mutListener.listen(8331)) {
            if (rangeObject == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8332)) {
            mClickableSpan = new NoteBlockClickableSpan(rangeObject, false, true);
        }
        if (!ListenerUtil.mutListener.listen(8333)) {
            mClickableSpan.setCustomType(noteType);
        }
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.FOOTER;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.note_block_footer;
    }

    @Override
    public View configureView(final View view) {
        final FooterNoteBlockHolder noteBlockHolder = (FooterNoteBlockHolder) view.getTag();
        if (!ListenerUtil.mutListener.listen(8339)) {
            // Note text
            if (!TextUtils.isEmpty(getNoteText())) {
                Spannable spannable = getNoteText();
                NoteBlockClickableSpan[] spans = spannable.getSpans(0, spannable.length(), NoteBlockClickableSpan.class);
                if (!ListenerUtil.mutListener.listen(8336)) {
                    {
                        long _loopCounter170 = 0;
                        for (NoteBlockClickableSpan span : spans) {
                            ListenerUtil.loopListener.listen("_loopCounter170", ++_loopCounter170);
                            if (!ListenerUtil.mutListener.listen(8335)) {
                                span.enableColors(view.getContext());
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8337)) {
                    noteBlockHolder.getTextView().setText(spannable);
                }
                if (!ListenerUtil.mutListener.listen(8338)) {
                    noteBlockHolder.getTextView().setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8334)) {
                    noteBlockHolder.getTextView().setVisibility(View.GONE);
                }
            }
        }
        String noticonGlyph = getNoticonGlyph();
        if (!ListenerUtil.mutListener.listen(8345)) {
            if (!TextUtils.isEmpty(noticonGlyph)) {
                if (!ListenerUtil.mutListener.listen(8341)) {
                    noteBlockHolder.getNoticonView().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8342)) {
                    noteBlockHolder.getNoticonView().setText(noticonGlyph);
                }
                if (!ListenerUtil.mutListener.listen(8344)) {
                    // mirror noticon in the rtl mode
                    if (RtlUtils.isRtl(noteBlockHolder.getNoticonView().getContext())) {
                        if (!ListenerUtil.mutListener.listen(8343)) {
                            noteBlockHolder.getNoticonView().setScaleX(-1);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8340)) {
                    noteBlockHolder.getNoticonView().setVisibility(View.GONE);
                }
            }
        }
        return view;
    }

    @NotNull
    private String getNoticonGlyph() {
        return FormattableContentUtilsKt.getRangeValueOrEmpty(getNoteData(), 0);
    }

    @Override
    Spannable getNoteText() {
        return mNotificationsUtilsWrapper.getSpannableContentForRanges(getNoteData(), null, getOnNoteBlockTextClickListener(), true);
    }

    public Object getViewHolder(View view) {
        return new FooterNoteBlockHolder(view);
    }

    class FooterNoteBlockHolder {

        private final View mFooterView;

        private final TextView mTextView;

        private final TextView mNoticonView;

        FooterNoteBlockHolder(View view) {
            mFooterView = view.findViewById(R.id.note_footer);
            if (!ListenerUtil.mutListener.listen(8347)) {
                mFooterView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(8346)) {
                            onRangeClick();
                        }
                    }
                });
            }
            mTextView = view.findViewById(R.id.note_footer_text);
            mNoticonView = view.findViewById(R.id.note_footer_noticon);
        }

        public TextView getTextView() {
            return mTextView;
        }

        public TextView getNoticonView() {
            return mNoticonView;
        }
    }

    private void onRangeClick() {
        if (!ListenerUtil.mutListener.listen(8349)) {
            if ((ListenerUtil.mutListener.listen(8348) ? (mClickableSpan == null && getOnNoteBlockTextClickListener() == null) : (mClickableSpan == null || getOnNoteBlockTextClickListener() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8350)) {
            getOnNoteBlockTextClickListener().onNoteBlockTextClicked(mClickableSpan);
        }
    }
}
