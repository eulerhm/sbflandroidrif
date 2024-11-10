package com.github.pockethub.android

import kotlin.Long
import kotlin.String

interface SelectUserAndOrgs {
  val id: Long

  val login: String?

  val name: String?

  val avatarurl: String?

  data class Impl(
    override val id: Long,
    override val login: String?,
    override val name: String?,
    override val avatarurl: String?
  ) : SelectUserAndOrgs {
    override fun toString(): String = """
    |SelectUserAndOrgs.Impl [
    |  id: $id
    |  login: $login
    |  name: $name
    |  avatarurl: $avatarurl
    |]
    """.trimMargin()
  }
}
