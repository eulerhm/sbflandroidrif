package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface Users {
  val id: Long

  val login: String?

  val name: String?

  val avatarurl: String?

  data class Impl(
    override val id: Long,
    override val login: String?,
    override val name: String?,
    override val avatarurl: String?
  ) : Users {
    override fun toString(): String = """
    |Users.Impl [
    |  id: $id
    |  login: $login
    |  name: $name
    |  avatarurl: $avatarurl
    |]
    """.trimMargin()
  }
}
