package com.github.pockethub.android

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

interface SelectUserRepositories {
  val repoId: Long?

  val name: String?

  val id: Long?

  val login: String?

  val name_: String?

  val avatarurl: String?

  val private: Boolean?

  val fork: Boolean?

  val description: String?

  val forks: Int?

  val watchers: Int?

  val language: String?

  val hasIssues: Boolean?

  val mirrorUrl: String?

  val permissions_admin: Boolean?

  val permissions_pull: Boolean?

  val permissions_push: Boolean?

  data class Impl(
    override val repoId: Long?,
    override val name: String?,
    override val id: Long?,
    override val login: String?,
    override val name_: String?,
    override val avatarurl: String?,
    override val private: Boolean?,
    override val fork: Boolean?,
    override val description: String?,
    override val forks: Int?,
    override val watchers: Int?,
    override val language: String?,
    override val hasIssues: Boolean?,
    override val mirrorUrl: String?,
    override val permissions_admin: Boolean?,
    override val permissions_pull: Boolean?,
    override val permissions_push: Boolean?
  ) : SelectUserRepositories {
    override fun toString(): String = """
    |SelectUserRepositories.Impl [
    |  repoId: $repoId
    |  name: $name
    |  id: $id
    |  login: $login
    |  name_: $name_
    |  avatarurl: $avatarurl
    |  private: $private
    |  fork: $fork
    |  description: $description
    |  forks: $forks
    |  watchers: $watchers
    |  language: $language
    |  hasIssues: $hasIssues
    |  mirrorUrl: $mirrorUrl
    |  permissions_admin: $permissions_admin
    |  permissions_pull: $permissions_pull
    |  permissions_push: $permissions_push
    |]
    """.trimMargin()
  }
}
