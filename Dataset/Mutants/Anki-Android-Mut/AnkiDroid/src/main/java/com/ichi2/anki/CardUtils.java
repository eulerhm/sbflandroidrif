package com.ichi2.anki;

import com.ichi2.libanki.Card;
import com.ichi2.libanki.Note;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Utilities for working on multiple cards
 */
public class CardUtils {

    /**
     * @return List of corresponding notes without duplicates, even if the input list has multiple cards of the same note.
     */
    public static Set<Note> getNotes(Collection<Card> cards) {
        Set<Note> notes = new HashSet<>(cards.size());
        if (!ListenerUtil.mutListener.listen(6729)) {
            {
                long _loopCounter120 = 0;
                for (Card card : cards) {
                    ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                    if (!ListenerUtil.mutListener.listen(6728)) {
                        notes.add(card.note());
                    }
                }
            }
        }
        return notes;
    }

    /**
     * @return All cards of all notes
     */
    public static List<Card> getAllCards(Set<Note> notes) {
        List<Card> allCards = new ArrayList<>(notes.size());
        if (!ListenerUtil.mutListener.listen(6731)) {
            {
                long _loopCounter121 = 0;
                for (Note note : notes) {
                    ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                    if (!ListenerUtil.mutListener.listen(6730)) {
                        allCards.addAll(note.cards());
                    }
                }
            }
        }
        return allCards;
    }

    public static void markAll(List<Note> notes, boolean mark) {
        if (!ListenerUtil.mutListener.listen(6738)) {
            {
                long _loopCounter122 = 0;
                for (Note note : notes) {
                    ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                    if (!ListenerUtil.mutListener.listen(6737)) {
                        if (mark) {
                            if (!ListenerUtil.mutListener.listen(6736)) {
                                if (!note.hasTag("marked")) {
                                    if (!ListenerUtil.mutListener.listen(6734)) {
                                        note.addTag("marked");
                                    }
                                    if (!ListenerUtil.mutListener.listen(6735)) {
                                        note.flush();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6732)) {
                                note.delTag("marked");
                            }
                            if (!ListenerUtil.mutListener.listen(6733)) {
                                note.flush();
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isIn(long[] array, long val) {
        if (!ListenerUtil.mutListener.listen(6745)) {
            {
                long _loopCounter123 = 0;
                for (long v : array) {
                    ListenerUtil.loopListener.listen("_loopCounter123", ++_loopCounter123);
                    if (!ListenerUtil.mutListener.listen(6744)) {
                        if ((ListenerUtil.mutListener.listen(6743) ? (v >= val) : (ListenerUtil.mutListener.listen(6742) ? (v <= val) : (ListenerUtil.mutListener.listen(6741) ? (v > val) : (ListenerUtil.mutListener.listen(6740) ? (v < val) : (ListenerUtil.mutListener.listen(6739) ? (v != val) : (v == val))))))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
