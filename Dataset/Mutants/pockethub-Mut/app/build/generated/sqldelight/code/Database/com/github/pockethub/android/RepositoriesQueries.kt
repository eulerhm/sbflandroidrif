package com.github.pockethub.android

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

interface RepositoriesQueries : Transacter {
  fun <T : Any> selectUserRepositories(orgId: Long?, mapper: (
    repoId: Long?,
    name: String?,
    id: Long?,
    login: String?,
    name_: String?,
    avatarurl: String?,
    private: Boolean?,
    fork: Boolean?,
    description: String?,
    forks: Int?,
    watchers: Int?,
    language: String?,
    hasIssues: Boolean?,
    mirrorUrl: String?,
    permissions_admin: Boolean?,
    permissions_pull: Boolean?,
    permissions_push: Boolean?
  ) -> T): Query<T>

  fun selectUserRepositories(orgId: Long?): Query<SelectUserRepositories>

  fun clearUserRepositories(orgId: Long?)

  fun clearRepositories()

  fun replaceRepo(
    repoId: Long?,
    name: String?,
    orgId: Long?,
    ownerId: Long?,
    private: Boolean?,
    fork: Boolean?,
    description: String?,
    forks: Int?,
    watchers: Int?,
    language: String?,
    hasIssues: Boolean?,
    mirrorUrl: String?,
    permissions_admin: Boolean?,
    permissions_pull: Boolean?,
    permissions_push: Boolean?
  )
}
