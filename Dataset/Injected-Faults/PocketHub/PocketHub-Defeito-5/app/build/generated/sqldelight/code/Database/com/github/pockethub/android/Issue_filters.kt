package com.github.pockethub.android

import kotlin.Boolean
import kotlin.Long
import kotlin.String

interface Issue_filters {
  val id: String

  val repo_id: Long?

  val milestone_id: Long?

  val assignee_id: Long?

  val open: Boolean?

  val direction: String?

  val sort_type: String?

  data class Impl(
    override val id: String,
    override val repo_id: Long?,
    override val milestone_id: Long?,
    override val assignee_id: Long?,
    override val open: Boolean?,
    override val direction: String?,
    override val sort_type: String?
  ) : Issue_filters {
    override fun toString(): String = """
    |Issue_filters.Impl [
    |  id: $id
    |  repo_id: $repo_id
    |  milestone_id: $milestone_id
    |  assignee_id: $assignee_id
    |  open: $open
    |  direction: $direction
    |  sort_type: $sort_type
    |]
    """.trimMargin()
  }
}
