package com.github.pockethub.android

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

interface Repositories {
  val id: Long

  val repoId: Long?

  val orgId: Long?

  val name: String?

  val ownerId: Long?

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
    override val id: Long,
    override val repoId: Long?,
    override val orgId: Long?,
    override val name: String?,
    override val ownerId: Long?,
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
  ) : Repositories {
    override fun toString(): String = """
    |Repositories.Impl [
    |  id: $id
    |  repoId: $repoId
    |  orgId: $orgId
    |  name: $name
    |  ownerId: $ownerId
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
