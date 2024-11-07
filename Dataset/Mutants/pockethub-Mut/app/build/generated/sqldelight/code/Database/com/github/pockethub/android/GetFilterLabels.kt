package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface GetFilterLabels {
  val issue_filter_id: String?

  val repo_id: Long?

  val name: String?

  val repo_id_: Long?

  val name_: String?

  val color: String?

  data class Impl(
    override val issue_filter_id: String?,
    override val repo_id: Long?,
    override val name: String?,
    override val repo_id_: Long?,
    override val name_: String?,
    override val color: String?
  ) : GetFilterLabels {
    override fun toString(): String = """
    |GetFilterLabels.Impl [
    |  issue_filter_id: $issue_filter_id
    |  repo_id: $repo_id
    |  name: $name
    |  repo_id_: $repo_id_
    |  name_: $name_
    |  color: $color
    |]
    """.trimMargin()
  }
}
