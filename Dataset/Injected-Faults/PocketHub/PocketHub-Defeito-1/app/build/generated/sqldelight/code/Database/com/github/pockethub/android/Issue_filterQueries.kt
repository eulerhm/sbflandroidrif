package com.github.pockethub.android

import com.meisolsson.githubsdk.model.IssueState
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Boolean
import kotlin.Long
import kotlin.String

interface Issue_filterQueries : Transacter {
  fun getFilters(): Query<GetFilters>

  fun <T : Any> getFilterLabels(issue_filter_id: String?, mapper: (
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?,
    repo_id_: Long?,
    name_: String?,
    color: String?
  ) -> T): Query<T>

  fun getFilterLabels(issue_filter_id: String?): Query<GetFilterLabels>

  fun insertOrReplaceIssueFilter(
    id: String,
    repo_id: Long?,
    milestone_id: Long?,
    assignee_id: Long?,
    open: Boolean?,
    direction: String?,
    sort_type: String?
  )

  fun insertOrReplaceLabel(
    repo_id: Long?,
    name: String?,
    color: String?
  )

  fun insertOrReplaceIssueFilterLabel(
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?
  )

  fun insertOrReplaceMilestone(
    repo_id: Long?,
    title: String?,
    state: IssueState?,
    milestone_id: Long?,
    number: Long?
  )

  fun removeIssueFilter(id: String)

  fun removeIssueFilterLabel(
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?
  )

  fun clearIssueFilters()

  fun clearMilestones()

  fun clearLabels()

  fun clearIssueFilterLabels()
}
