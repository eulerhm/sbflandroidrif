package org.wordpress.android.ui.notifications.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import org.wordpress.android.R;
import org.wordpress.android.ui.notifications.blocks.NoteBlock;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NoteBlockAdapter extends ArrayAdapter<NoteBlock> {

    private final LayoutInflater mLayoutInflater;

    private List<NoteBlock> mNoteBlockList;

    public NoteBlockAdapter(Context context, List<NoteBlock> noteBlocks) {
        super(context, 0, noteBlocks);
        if (!ListenerUtil.mutListener.listen(8094)) {
            mNoteBlockList = noteBlocks;
        }
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return mNoteBlockList == null ? 0 : mNoteBlockList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteBlock noteBlock = mNoteBlockList.get(position);
        if (!ListenerUtil.mutListener.listen(8098)) {
            // Check the tag for this recycled view, if it matches we can reuse it
            if ((ListenerUtil.mutListener.listen(8095) ? (convertView == null && noteBlock.getBlockType() != convertView.getTag(R.id.note_block_tag_id)) : (convertView == null || noteBlock.getBlockType() != convertView.getTag(R.id.note_block_tag_id)))) {
                if (!ListenerUtil.mutListener.listen(8096)) {
                    convertView = mLayoutInflater.inflate(noteBlock.getLayoutResourceId(), parent, false);
                }
                if (!ListenerUtil.mutListener.listen(8097)) {
                    convertView.setTag(noteBlock.getViewHolder(convertView));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8099)) {
            // Update the block type for this view
            convertView.setTag(R.id.note_block_tag_id, noteBlock.getBlockType());
        }
        return noteBlock.configureView(convertView);
    }

    public void setNoteList(List<NoteBlock> noteList) {
        if (!ListenerUtil.mutListener.listen(8100)) {
            if (noteList == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8101)) {
            mNoteBlockList = noteList;
        }
        if (!ListenerUtil.mutListener.listen(8102)) {
            notifyDataSetChanged();
        }
    }
}
