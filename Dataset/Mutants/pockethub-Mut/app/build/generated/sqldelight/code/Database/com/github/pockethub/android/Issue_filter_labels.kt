package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface Issue_filter_labels {
  val issue_filter_id: String?

  val repo_id: Long?

  val name: String?

  data class Impl(
    override val issue_filter_id: String?,
    override val repo_id: Long?,
    override val name: String?
  ) : Issue_filter_labels {
    override fun toString(): String = """
    |Issue_filter_labels.Impl [
    |  issue_filter_id: $issue_filter_id
    |  repo_id: $repo_id
    |  name: $name
    |]
    """.trimMargin()
  }
}
