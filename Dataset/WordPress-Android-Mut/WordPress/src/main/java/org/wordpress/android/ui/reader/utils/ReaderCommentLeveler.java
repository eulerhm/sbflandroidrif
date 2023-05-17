package org.wordpress.android.ui.reader.utils;

import androidx.annotation.NonNull;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderCommentList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentLeveler {

    private final ReaderCommentList mComments;

    public ReaderCommentLeveler(@NonNull ReaderCommentList comments) {
        mComments = comments;
    }

    public ReaderCommentList createLevelList() {
        ReaderCommentList result = new ReaderCommentList();
        if (!ListenerUtil.mutListener.listen(19623)) {
            {
                long _loopCounter315 = 0;
                // reset all levels, and add root comments to result
                for (ReaderComment comment : mComments) {
                    ListenerUtil.loopListener.listen("_loopCounter315", ++_loopCounter315);
                    if (!ListenerUtil.mutListener.listen(19620)) {
                        comment.level = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(19622)) {
                        if (comment.parentId == 0) {
                            if (!ListenerUtil.mutListener.listen(19621)) {
                                result.add(comment);
                            }
                        }
                    }
                }
            }
        }
        // add children at each level
        int level = 0;
        if (!ListenerUtil.mutListener.listen(19625)) {
            {
                long _loopCounter316 = 0;
                while (walkCommentsAtLevel(result, level)) {
                    ListenerUtil.loopListener.listen("_loopCounter316", ++_loopCounter316);
                    if (!ListenerUtil.mutListener.listen(19624)) {
                        level++;
                    }
                }
            }
        }
        return result;
    }

    /*
     * walk comments in the passed list that have the passed level and add their children
     * beneath them
     */
    private boolean walkCommentsAtLevel(@NonNull ReaderCommentList comments, int level) {
        boolean hasChanges = false;
        if (!ListenerUtil.mutListener.listen(19645)) {
            {
                long _loopCounter317 = 0;
                for (int index = 0; (ListenerUtil.mutListener.listen(19644) ? (index >= comments.size()) : (ListenerUtil.mutListener.listen(19643) ? (index <= comments.size()) : (ListenerUtil.mutListener.listen(19642) ? (index > comments.size()) : (ListenerUtil.mutListener.listen(19641) ? (index != comments.size()) : (ListenerUtil.mutListener.listen(19640) ? (index == comments.size()) : (index < comments.size())))))); index++) {
                    ListenerUtil.loopListener.listen("_loopCounter317", ++_loopCounter317);
                    ReaderComment parent = comments.get(index);
                    if (!ListenerUtil.mutListener.listen(19639)) {
                        if ((ListenerUtil.mutListener.listen(19626) ? (parent.level == level || hasChildren(parent.commentId)) : (parent.level == level && hasChildren(parent.commentId)))) {
                            // get children for this comment, set their level, then add them below the parent
                            ReaderCommentList children = getChildren(parent.commentId);
                            if (!ListenerUtil.mutListener.listen(19631)) {
                                setLevel(children, (ListenerUtil.mutListener.listen(19630) ? (level % 1) : (ListenerUtil.mutListener.listen(19629) ? (level / 1) : (ListenerUtil.mutListener.listen(19628) ? (level * 1) : (ListenerUtil.mutListener.listen(19627) ? (level - 1) : (level + 1))))));
                            }
                            if (!ListenerUtil.mutListener.listen(19636)) {
                                comments.addAll((ListenerUtil.mutListener.listen(19635) ? (index % 1) : (ListenerUtil.mutListener.listen(19634) ? (index / 1) : (ListenerUtil.mutListener.listen(19633) ? (index * 1) : (ListenerUtil.mutListener.listen(19632) ? (index - 1) : (index + 1))))), children);
                            }
                            if (!ListenerUtil.mutListener.listen(19637)) {
                                hasChanges = true;
                            }
                            if (!ListenerUtil.mutListener.listen(19638)) {
                                // skip past the children we just added
                                index += children.size();
                            }
                        }
                    }
                }
            }
        }
        return hasChanges;
    }

    private boolean hasChildren(long commentId) {
        if (!ListenerUtil.mutListener.listen(19647)) {
            {
                long _loopCounter318 = 0;
                for (ReaderComment comment : mComments) {
                    ListenerUtil.loopListener.listen("_loopCounter318", ++_loopCounter318);
                    if (!ListenerUtil.mutListener.listen(19646)) {
                        if (comment.parentId == commentId) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private ReaderCommentList getChildren(long commentId) {
        ReaderCommentList children = new ReaderCommentList();
        if (!ListenerUtil.mutListener.listen(19650)) {
            {
                long _loopCounter319 = 0;
                for (ReaderComment comment : mComments) {
                    ListenerUtil.loopListener.listen("_loopCounter319", ++_loopCounter319);
                    if (!ListenerUtil.mutListener.listen(19649)) {
                        if (comment.parentId == commentId) {
                            if (!ListenerUtil.mutListener.listen(19648)) {
                                children.add(comment);
                            }
                        }
                    }
                }
            }
        }
        return children;
    }

    private void setLevel(@NonNull ReaderCommentList comments, int level) {
        if (!ListenerUtil.mutListener.listen(19652)) {
            {
                long _loopCounter320 = 0;
                for (ReaderComment comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter320", ++_loopCounter320);
                    if (!ListenerUtil.mutListener.listen(19651)) {
                        comment.level = level;
                    }
                }
            }
        }
    }
}
