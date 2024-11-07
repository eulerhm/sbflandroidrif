package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface Organizations {
  val id: Long

  data class Impl(
    override val id: Long
  ) : Organizations {
    override fun toString(): String = """
    |Organizations.Impl [
    |  id: $id
    |]
    """.trimMargin()
  }
}
