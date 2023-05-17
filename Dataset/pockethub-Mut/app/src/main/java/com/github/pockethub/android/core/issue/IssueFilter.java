/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.core.issue;

import android.os.Parcel;
import android.os.Parcelable;
import com.meisolsson.githubsdk.model.Label;
import com.meisolsson.githubsdk.model.Milestone;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import java.util.*;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Issue filter containing at least one valid query
 */
public class IssueFilter implements Parcelable, Cloneable, Comparator<Label> {

    /**
     * Filter field key
     */
    public static final String FIELD_FILTER = "filter";

    /**
     * Filter by issue assignee
     */
    public static final String FILTER_ASSIGNEE = "assignee";

    /**
     * Filter by issue's milestone
     */
    public static final String FILTER_MILESTONE = "milestone";

    /**
     * Filter by user mentioned in issue
     */
    public static final String FILTER_MENTIONED = "mentioned";

    /**
     * Filter by subscribed issues for user
     */
    public static final String FILTER_SUBSCRIBED = "subscribed";

    /**
     * Filter by created issues by user
     */
    public static final String FILTER_CREATED = "created";

    /**
     * Filter by assigned issues for user
     */
    public static final String FILTER_ASSIGNED = "assigned";

    /**
     * Filter by issue's labels
     */
    public static final String FILTER_LABELS = "labels";

    /**
     * Filter by issue's state
     */
    public static final String FILTER_STATE = "state";

    /**
     * Issue open state filter value
     */
    public static final String STATE_OPEN = "open";

    /**
     * Issue closed state filter value
     */
    public static final String STATE_CLOSED = "closed";

    /**
     * Issue body field name
     */
    public static final String FIELD_BODY = "body";

    /**
     * Issue title field name
     */
    public static final String FIELD_TITLE = "title";

    /**
     * Since date field
     */
    public static final String FIELD_SINCE = "since";

    /**
     * Sort direction of output
     */
    public static final String FIELD_DIRECTION = "direction";

    /**
     * Ascending direction sort order
     */
    public static final String DIRECTION_ASCENDING = "asc";

    /**
     * Descending direction sort order
     */
    public static final String DIRECTION_DESCENDING = "desc";

    /**
     * Sort field key
     */
    public static final String FIELD_SORT = "sort";

    /**
     * Sort by created at
     */
    public static final String SORT_CREATED = "created";

    /**
     * Sort by updated at
     */
    public static final String SORT_UPDATED = "updated";

    /**
     * Sort by commented on at
     */
    public static final String SORT_COMMENTS = "comments";

    private final Repository repository;

    private final String id;

    private List<Label> labels = new ArrayList<>();

    private Milestone milestone;

    private User assignee;

    private boolean open;

    private String direction;

    private String sortType;

    /**
     * Create filter
     *
     * @param repository
     */
    public IssueFilter(final Repository repository, String id) {
        this.id = id;
        this.repository = repository;
        if (!ListenerUtil.mutListener.listen(346)) {
            open = true;
        }
        if (!ListenerUtil.mutListener.listen(347)) {
            direction = DIRECTION_DESCENDING;
        }
        if (!ListenerUtil.mutListener.listen(348)) {
            sortType = SORT_CREATED;
        }
    }

    protected IssueFilter(Parcel in) {
        id = in.readString();
        repository = in.readParcelable(Repository.class.getClassLoader());
        if (!ListenerUtil.mutListener.listen(349)) {
            labels = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(350)) {
            in.readList(labels, Label.class.getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(351)) {
            milestone = in.readParcelable(Milestone.class.getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(352)) {
            assignee = in.readParcelable(User.class.getClassLoader());
        }
        if (!ListenerUtil.mutListener.listen(353)) {
            open = in.readByte() != 0;
        }
        if (!ListenerUtil.mutListener.listen(354)) {
            direction = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(355)) {
            sortType = in.readString();
        }
    }

    public static final Creator<IssueFilter> CREATOR = new Creator<IssueFilter>() {

        @Override
        public IssueFilter createFromParcel(Parcel in) {
            return new IssueFilter(in);
        }

        @Override
        public IssueFilter[] newArray(int size) {
            return new IssueFilter[size];
        }
    };

    /**
     * Set only open issues to be returned
     *
     * @param open
     *            true for open issues, false for closed issues
     * @return this filter
     */
    public IssueFilter setOpen(final boolean open) {
        if (!ListenerUtil.mutListener.listen(356)) {
            this.open = open;
        }
        return this;
    }

    /**
     * Add label to filter
     *
     * @param label
     * @return this filter
     */
    public IssueFilter addLabel(Label label) {
        if (!ListenerUtil.mutListener.listen(357)) {
            if (label == null) {
                return this;
            }
        }
        if (!ListenerUtil.mutListener.listen(359)) {
            if (labels == null) {
                if (!ListenerUtil.mutListener.listen(358)) {
                    labels = new ArrayList<>();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(360)) {
            labels.add(label);
        }
        return this;
    }

    /**
     * @param labels
     * @return this filter
     */
    public IssueFilter setLabels(Collection<Label> labels) {
        if (!ListenerUtil.mutListener.listen(367)) {
            if ((ListenerUtil.mutListener.listen(361) ? (labels != null || !labels.isEmpty()) : (labels != null && !labels.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(365)) {
                    if (this.labels == null) {
                        if (!ListenerUtil.mutListener.listen(364)) {
                            this.labels = new ArrayList<>();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(363)) {
                            this.labels.clear();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(366)) {
                    this.labels.addAll(labels);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(362)) {
                    this.labels = null;
                }
            }
        }
        return this;
    }

    /**
     * @return labels
     */
    public List<Label> getLabels() {
        return labels;
    }

    /**
     * @return repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @param milestone
     * @return this filter
     */
    public IssueFilter setMilestone(Milestone milestone) {
        if (!ListenerUtil.mutListener.listen(368)) {
            this.milestone = milestone;
        }
        return this;
    }

    /**
     * @return milestone
     */
    public Milestone getMilestone() {
        return milestone;
    }

    /**
     * @param assignee
     * @return this filter
     */
    public IssueFilter setAssignee(User assignee) {
        if (!ListenerUtil.mutListener.listen(369)) {
            this.assignee = assignee;
        }
        return this;
    }

    /**
     * @param direction Can be either {@value DIRECTION_ASCENDING} or {@value DIRECTION_ASCENDING}.
     * @return this filter
     */
    public IssueFilter setDirection(String direction) {
        if (!ListenerUtil.mutListener.listen(370)) {
            this.direction = direction;
        }
        return this;
    }

    /**
     * @param sortType Can be either {@value SORT_COMMENTS}, {@value SORT_CREATED}
     *                 or {@value SORT_UPDATED}.
     * @return this filter
     */
    public IssueFilter setSortType(String sortType) {
        if (!ListenerUtil.mutListener.listen(371)) {
            this.sortType = sortType;
        }
        return this;
    }

    public String getSortType() {
        return sortType;
    }

    public String getDirection() {
        return direction;
    }

    /**
     * Are only open issues returned?
     *
     * @return true if open only, false if closed only
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @return assignee
     */
    public User getAssignee() {
        return assignee;
    }

    /**
     * Create a map of all the request parameters represented by this filter
     *
     * @return non-null map of filter request parameters
     */
    public Map<String, Object> toFilterMap() {
        final Map<String, Object> filter = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(372)) {
            filter.put(FIELD_SORT, sortType);
        }
        if (!ListenerUtil.mutListener.listen(373)) {
            filter.put(FIELD_DIRECTION, direction);
        }
        if (!ListenerUtil.mutListener.listen(375)) {
            if (assignee != null) {
                if (!ListenerUtil.mutListener.listen(374)) {
                    filter.put(FILTER_ASSIGNEE, assignee.login());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(377)) {
            if (milestone != null) {
                if (!ListenerUtil.mutListener.listen(376)) {
                    filter.put(FILTER_MILESTONE, Integer.toString(milestone.number()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(382)) {
            if ((ListenerUtil.mutListener.listen(378) ? (labels != null || !labels.isEmpty()) : (labels != null && !labels.isEmpty()))) {
                StringBuilder labelsQuery = new StringBuilder();
                if (!ListenerUtil.mutListener.listen(380)) {
                    {
                        long _loopCounter9 = 0;
                        for (Label label : labels) {
                            ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                            if (!ListenerUtil.mutListener.listen(379)) {
                                labelsQuery.append(label.name()).append(',');
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(381)) {
                    filter.put(FILTER_LABELS, labelsQuery.toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(385)) {
            if (open) {
                if (!ListenerUtil.mutListener.listen(384)) {
                    filter.put(FILTER_STATE, STATE_OPEN);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(383)) {
                    filter.put(FILTER_STATE, STATE_CLOSED);
                }
            }
        }
        return filter;
    }

    /**
     * Get display {@link CharSequence} representing this filter
     *
     * @return display
     */
    public CharSequence toDisplay() {
        List<String> segments = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(388)) {
            if (open) {
                if (!ListenerUtil.mutListener.listen(387)) {
                    segments.add("Open issues");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(386)) {
                    segments.add("Closed issues");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(390)) {
            if (assignee != null) {
                if (!ListenerUtil.mutListener.listen(389)) {
                    segments.add("Assignee: " + assignee.login());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(392)) {
            if (milestone != null) {
                if (!ListenerUtil.mutListener.listen(391)) {
                    segments.add("Milestone: " + milestone.title());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(407)) {
            if ((ListenerUtil.mutListener.listen(393) ? (labels != null || !labels.isEmpty()) : (labels != null && !labels.isEmpty()))) {
                StringBuilder builder = new StringBuilder("Labels: ");
                if (!ListenerUtil.mutListener.listen(395)) {
                    {
                        long _loopCounter10 = 0;
                        for (Label label : labels) {
                            ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                            if (!ListenerUtil.mutListener.listen(394)) {
                                builder.append(label.name()).append(',').append(' ');
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(400)) {
                    builder.deleteCharAt((ListenerUtil.mutListener.listen(399) ? (builder.length() % 1) : (ListenerUtil.mutListener.listen(398) ? (builder.length() / 1) : (ListenerUtil.mutListener.listen(397) ? (builder.length() * 1) : (ListenerUtil.mutListener.listen(396) ? (builder.length() + 1) : (builder.length() - 1))))));
                }
                if (!ListenerUtil.mutListener.listen(405)) {
                    builder.deleteCharAt((ListenerUtil.mutListener.listen(404) ? (builder.length() % 1) : (ListenerUtil.mutListener.listen(403) ? (builder.length() / 1) : (ListenerUtil.mutListener.listen(402) ? (builder.length() * 1) : (ListenerUtil.mutListener.listen(401) ? (builder.length() + 1) : (builder.length() - 1))))));
                }
                if (!ListenerUtil.mutListener.listen(406)) {
                    segments.add(builder.toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(408)) {
            if (segments.isEmpty()) {
                return "";
            }
        }
        StringBuilder all = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(410)) {
            {
                long _loopCounter11 = 0;
                for (String segment : segments) {
                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                    if (!ListenerUtil.mutListener.listen(409)) {
                        all.append(segment).append(',').append(' ');
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(415)) {
            all.deleteCharAt((ListenerUtil.mutListener.listen(414) ? (all.length() % 1) : (ListenerUtil.mutListener.listen(413) ? (all.length() / 1) : (ListenerUtil.mutListener.listen(412) ? (all.length() * 1) : (ListenerUtil.mutListener.listen(411) ? (all.length() + 1) : (all.length() - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(420)) {
            all.deleteCharAt((ListenerUtil.mutListener.listen(419) ? (all.length() % 1) : (ListenerUtil.mutListener.listen(418) ? (all.length() / 1) : (ListenerUtil.mutListener.listen(417) ? (all.length() * 1) : (ListenerUtil.mutListener.listen(416) ? (all.length() + 1) : (all.length() - 1))))));
        }
        return all;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] { open, assignee != null ? assignee.id() : null, milestone != null ? milestone.number() : null, assignee != null ? assignee.id() : null, repository != null ? repository.id() : null, labels, direction, sortType });
    }

    private boolean isEqual(Object a, Object b) {
        if (!ListenerUtil.mutListener.listen(422)) {
            if ((ListenerUtil.mutListener.listen(421) ? (a == null || b == null) : (a == null && b == null))) {
                return true;
            }
        }
        return (ListenerUtil.mutListener.listen(423) ? (a != null || a.equals(b)) : (a != null && a.equals(b)));
    }

    private boolean isEqual(Milestone a, Milestone b) {
        if (!ListenerUtil.mutListener.listen(425)) {
            if ((ListenerUtil.mutListener.listen(424) ? (a == null || b == null) : (a == null && b == null))) {
                return true;
            }
        }
        return (ListenerUtil.mutListener.listen(427) ? ((ListenerUtil.mutListener.listen(426) ? (a != null || b != null) : (a != null && b != null)) || a.number() == b.number()) : ((ListenerUtil.mutListener.listen(426) ? (a != null || b != null) : (a != null && b != null)) && a.number() == b.number()));
    }

    private boolean isEqual(User a, User b) {
        if (!ListenerUtil.mutListener.listen(429)) {
            if ((ListenerUtil.mutListener.listen(428) ? (a == null || b == null) : (a == null && b == null))) {
                return true;
            }
        }
        return (ListenerUtil.mutListener.listen(431) ? ((ListenerUtil.mutListener.listen(430) ? (a != null || b != null) : (a != null && b != null)) || a.id() == b.id()) : ((ListenerUtil.mutListener.listen(430) ? (a != null || b != null) : (a != null && b != null)) && a.id() == b.id()));
    }

    private boolean isEqual(Repository a, Repository b) {
        return (ListenerUtil.mutListener.listen(433) ? ((ListenerUtil.mutListener.listen(432) ? (a != null || b != null) : (a != null && b != null)) || a.id() == b.id()) : ((ListenerUtil.mutListener.listen(432) ? (a != null || b != null) : (a != null && b != null)) && a.id() == b.id()));
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(434)) {
            if (o == this) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(435)) {
            if (!(o instanceof IssueFilter)) {
                return false;
            }
        }
        IssueFilter other = (IssueFilter) o;
        return (ListenerUtil.mutListener.listen(441) ? ((ListenerUtil.mutListener.listen(440) ? ((ListenerUtil.mutListener.listen(439) ? ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) || isEqual(labels, other.labels)) : ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) && isEqual(labels, other.labels))) || isEqual(sortType, other.sortType)) : ((ListenerUtil.mutListener.listen(439) ? ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) || isEqual(labels, other.labels)) : ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) && isEqual(labels, other.labels))) && isEqual(sortType, other.sortType))) || isEqual(direction, other.direction)) : ((ListenerUtil.mutListener.listen(440) ? ((ListenerUtil.mutListener.listen(439) ? ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) || isEqual(labels, other.labels)) : ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) && isEqual(labels, other.labels))) || isEqual(sortType, other.sortType)) : ((ListenerUtil.mutListener.listen(439) ? ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) || isEqual(labels, other.labels)) : ((ListenerUtil.mutListener.listen(438) ? ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) || isEqual(repository, repository)) : ((ListenerUtil.mutListener.listen(437) ? ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) || isEqual(assignee, other.assignee)) : ((ListenerUtil.mutListener.listen(436) ? (open == other.open || isEqual(milestone, other.milestone)) : (open == other.open && isEqual(milestone, other.milestone))) && isEqual(assignee, other.assignee))) && isEqual(repository, repository))) && isEqual(labels, other.labels))) && isEqual(sortType, other.sortType))) && isEqual(direction, other.direction)));
    }

    @Override
    public IssueFilter clone() {
        try {
            return (IssueFilter) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since this class implements Cloneable
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int compare(Label lhs, Label rhs) {
        return CASE_INSENSITIVE_ORDER.compare(lhs.name(), rhs.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(442)) {
            dest.writeString(id);
        }
        if (!ListenerUtil.mutListener.listen(443)) {
            dest.writeParcelable(repository, flags);
        }
        if (!ListenerUtil.mutListener.listen(444)) {
            dest.writeList(labels);
        }
        if (!ListenerUtil.mutListener.listen(445)) {
            dest.writeParcelable(milestone, flags);
        }
        if (!ListenerUtil.mutListener.listen(446)) {
            dest.writeParcelable(assignee, flags);
        }
        if (!ListenerUtil.mutListener.listen(447)) {
            dest.writeByte((byte) (open ? 1 : 0));
        }
        if (!ListenerUtil.mutListener.listen(448)) {
            dest.writeString(direction);
        }
        if (!ListenerUtil.mutListener.listen(449)) {
            dest.writeString(sortType);
        }
    }

    public String getId() {
        return id;
    }
}
