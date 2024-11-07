package com.github.pockethub.android

import com.meisolsson.githubsdk.model.IssueState
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

interface GetFilters {
  val id: String

  val repo_id: Long?

  val milestone_id: Long?

  val assignee_id: Long?

  val open: Boolean?

  val direction: String?

  val sort_type: String?

  val title: String?

  val state: IssueState?

  val number: Long?

  val id_: Long?

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

  val id__: Long?

  val login: String?

  val name_: String?

  val avatarurl: String?

  data class Impl(
    override val id: String,
    override val repo_id: Long?,
    override val milestone_id: Long?,
    override val assignee_id: Long?,
    override val open: Boolean?,
    override val direction: String?,
    override val sort_type: String?,
    override val title: String?,
    override val state: IssueState?,
    override val number: Long?,
    override val id_: Long?,
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
    override val permissions_push: Boolean?,
    override val id__: Long?,
    override val login: String?,
    override val name_: String?,
    override val avatarurl: String?
  ) : GetFilters {
    override fun toString(): String = """
    |GetFilters.Impl [
    |  id: $id
    |  repo_id: $repo_id
    |  milestone_id: $milestone_id
    |  assignee_id: $assignee_id
    |  open: $open
    |  direction: $direction
    |  sort_type: $sort_type
    |  title: $title
    |  state: $state
    |  number: $number
    |  id_: $id_
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
    |  id__: $id__
    |  login: $login
    |  name_: $name_
    |  avatarurl: $avatarurl
    |]
    """.trimMargin()
  }
}
