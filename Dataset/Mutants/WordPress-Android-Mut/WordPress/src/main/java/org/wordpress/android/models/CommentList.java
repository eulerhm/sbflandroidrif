package org.wordpress.android.models;

import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.util.StringUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CommentList extends ArrayList<CommentModel> {

    public int indexOfCommentId(long commentId) {
        if (!ListenerUtil.mutListener.listen(1401)) {
            {
                long _loopCounter53 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1400) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(1399) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(1398) ? (i > this.size()) : (ListenerUtil.mutListener.listen(1397) ? (i != this.size()) : (ListenerUtil.mutListener.listen(1396) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                    if (!ListenerUtil.mutListener.listen(1395)) {
                        if (commentId == this.get(i).getRemoteCommentId()) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /*
     * replace comments in this list that match the passed list
     */
    public void replaceComments(final CommentList comments) {
        if (!ListenerUtil.mutListener.listen(1408)) {
            if ((ListenerUtil.mutListener.listen(1407) ? (comments == null && (ListenerUtil.mutListener.listen(1406) ? (comments.size() >= 0) : (ListenerUtil.mutListener.listen(1405) ? (comments.size() <= 0) : (ListenerUtil.mutListener.listen(1404) ? (comments.size() > 0) : (ListenerUtil.mutListener.listen(1403) ? (comments.size() < 0) : (ListenerUtil.mutListener.listen(1402) ? (comments.size() != 0) : (comments.size() == 0))))))) : (comments == null || (ListenerUtil.mutListener.listen(1406) ? (comments.size() >= 0) : (ListenerUtil.mutListener.listen(1405) ? (comments.size() <= 0) : (ListenerUtil.mutListener.listen(1404) ? (comments.size() > 0) : (ListenerUtil.mutListener.listen(1403) ? (comments.size() < 0) : (ListenerUtil.mutListener.listen(1402) ? (comments.size() != 0) : (comments.size() == 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1416)) {
            {
                long _loopCounter54 = 0;
                for (CommentModel comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                    int index = indexOfCommentId(comment.getRemoteCommentId());
                    if (!ListenerUtil.mutListener.listen(1415)) {
                        if ((ListenerUtil.mutListener.listen(1413) ? (index >= -1) : (ListenerUtil.mutListener.listen(1412) ? (index <= -1) : (ListenerUtil.mutListener.listen(1411) ? (index < -1) : (ListenerUtil.mutListener.listen(1410) ? (index != -1) : (ListenerUtil.mutListener.listen(1409) ? (index == -1) : (index > -1))))))) {
                            if (!ListenerUtil.mutListener.listen(1414)) {
                                set(index, comment);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * delete comments in this list that match the passed list
     */
    public void deleteComments(final CommentList comments) {
        if (!ListenerUtil.mutListener.listen(1423)) {
            if ((ListenerUtil.mutListener.listen(1422) ? (comments == null && (ListenerUtil.mutListener.listen(1421) ? (comments.size() >= 0) : (ListenerUtil.mutListener.listen(1420) ? (comments.size() <= 0) : (ListenerUtil.mutListener.listen(1419) ? (comments.size() > 0) : (ListenerUtil.mutListener.listen(1418) ? (comments.size() < 0) : (ListenerUtil.mutListener.listen(1417) ? (comments.size() != 0) : (comments.size() == 0))))))) : (comments == null || (ListenerUtil.mutListener.listen(1421) ? (comments.size() >= 0) : (ListenerUtil.mutListener.listen(1420) ? (comments.size() <= 0) : (ListenerUtil.mutListener.listen(1419) ? (comments.size() > 0) : (ListenerUtil.mutListener.listen(1418) ? (comments.size() < 0) : (ListenerUtil.mutListener.listen(1417) ? (comments.size() != 0) : (comments.size() == 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1431)) {
            {
                long _loopCounter55 = 0;
                for (CommentModel comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                    int index = indexOfCommentId(comment.getRemoteCommentId());
                    if (!ListenerUtil.mutListener.listen(1430)) {
                        if ((ListenerUtil.mutListener.listen(1428) ? (index >= -1) : (ListenerUtil.mutListener.listen(1427) ? (index <= -1) : (ListenerUtil.mutListener.listen(1426) ? (index < -1) : (ListenerUtil.mutListener.listen(1425) ? (index != -1) : (ListenerUtil.mutListener.listen(1424) ? (index == -1) : (index > -1))))))) {
                            if (!ListenerUtil.mutListener.listen(1429)) {
                                remove(index);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * returns true if any comments in this list have the passed status
     */
    public boolean hasAnyWithStatus(CommentStatus status) {
        if (!ListenerUtil.mutListener.listen(1433)) {
            {
                long _loopCounter56 = 0;
                for (CommentModel comment : this) {
                    ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                    if (!ListenerUtil.mutListener.listen(1432)) {
                        if (status.toString().equals(comment.getStatus())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * returns true if any comments in this list do NOT have the passed status
     */
    public boolean hasAnyWithoutStatus(CommentStatus status) {
        if (!ListenerUtil.mutListener.listen(1435)) {
            {
                long _loopCounter57 = 0;
                for (CommentModel comment : this) {
                    ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                    if (!ListenerUtil.mutListener.listen(1434)) {
                        if (!status.toString().equals(comment.getStatus())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * does passed list contain the same comments as this list?
     */
    public boolean isSameList(CommentList comments) {
        if (!ListenerUtil.mutListener.listen(1442)) {
            if ((ListenerUtil.mutListener.listen(1441) ? (comments == null && (ListenerUtil.mutListener.listen(1440) ? (comments.size() >= this.size()) : (ListenerUtil.mutListener.listen(1439) ? (comments.size() <= this.size()) : (ListenerUtil.mutListener.listen(1438) ? (comments.size() > this.size()) : (ListenerUtil.mutListener.listen(1437) ? (comments.size() < this.size()) : (ListenerUtil.mutListener.listen(1436) ? (comments.size() == this.size()) : (comments.size() != this.size()))))))) : (comments == null || (ListenerUtil.mutListener.listen(1440) ? (comments.size() >= this.size()) : (ListenerUtil.mutListener.listen(1439) ? (comments.size() <= this.size()) : (ListenerUtil.mutListener.listen(1438) ? (comments.size() > this.size()) : (ListenerUtil.mutListener.listen(1437) ? (comments.size() < this.size()) : (ListenerUtil.mutListener.listen(1436) ? (comments.size() == this.size()) : (comments.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1454)) {
            {
                long _loopCounter58 = 0;
                for (final CommentModel comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                    int index = this.indexOfCommentId(comment.getRemoteCommentId());
                    if (!ListenerUtil.mutListener.listen(1448)) {
                        if ((ListenerUtil.mutListener.listen(1447) ? (index >= -1) : (ListenerUtil.mutListener.listen(1446) ? (index <= -1) : (ListenerUtil.mutListener.listen(1445) ? (index > -1) : (ListenerUtil.mutListener.listen(1444) ? (index < -1) : (ListenerUtil.mutListener.listen(1443) ? (index != -1) : (index == -1))))))) {
                            return false;
                        }
                    }
                    final CommentModel thisComment = this.get(index);
                    if (!ListenerUtil.mutListener.listen(1449)) {
                        if (!StringUtils.equals(thisComment.getStatus(), comment.getStatus())) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1450)) {
                        if (!StringUtils.equals(thisComment.getContent(), comment.getContent())) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1451)) {
                        if (!StringUtils.equals(thisComment.getAuthorName(), comment.getAuthorName())) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1452)) {
                        if (!StringUtils.equals(thisComment.getAuthorEmail(), comment.getAuthorEmail())) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1453)) {
                        if (!StringUtils.equals(thisComment.getAuthorUrl(), comment.getAuthorUrl())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
