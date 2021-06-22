package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'HttpsGithubComSkrysmanskiResharperInspectcodeToolsetbug'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, GitVcsRoot({
    id("HttpsGithubComSkrysmanskiResharperInspectcodeToolsetbug")
    name = "https://github.com/skrysmanski/resharper-inspectcode-toolsetbug"
    url = "https://github.com/skrysmanski/resharper-inspectcode-toolsetbug"
    branch = "refs/heads/master"
}))
