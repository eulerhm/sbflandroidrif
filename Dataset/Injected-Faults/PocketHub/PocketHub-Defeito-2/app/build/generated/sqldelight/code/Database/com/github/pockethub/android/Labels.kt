package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface Labels {
  val repo_id: Long?

  val name: String?

  val color: String?

  data class Impl(
    override val repo_id: Long?,
    override val name: String?,
    override val color: String?
  ) : Labels {
    override fun toString(): String = """
    |Labels.Impl [
    |  repo_id: $repo_id
    |  name: $name
    |  color: $color
    |]
    """.trimMargin()
  }
}
