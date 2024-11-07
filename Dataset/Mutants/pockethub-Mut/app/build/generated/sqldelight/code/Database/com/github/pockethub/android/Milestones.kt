package com.github.pockethub.android

import com.meisolsson.githubsdk.model.IssueState
import com.squareup.sqldelight.ColumnAdapter
import kotlin.Long
import kotlin.String

interface Milestones {
  val repo_id: Long?

  val title: String?

  val state: IssueState?

  val milestone_id: Long?

  val number: Long?

  class Adapter(
    val stateAdapter: ColumnAdapter<IssueState, String>
  )

  data class Impl(
    override val repo_id: Long?,
    override val title: String?,
    override val state: IssueState?,
    override val milestone_id: Long?,
    override val number: Long?
  ) : Milestones {
    override fun toString(): String = """
    |Milestones.Impl [
    |  repo_id: $repo_id
    |  title: $title
    |  state: $state
    |  milestone_id: $milestone_id
    |  number: $number
    |]
    """.trimMargin()
  }
}
