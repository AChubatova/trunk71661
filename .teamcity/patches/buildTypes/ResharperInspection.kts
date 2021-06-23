package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'ResharperInspection'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("ResharperInspection")
    name = "resharper inspection"

    vcs {
        root(RelativeId("HttpsGithubComSkrysmanskiResharperInspectcodeToolsetbug"))
    }

    steps {
        step {
            type = "dotnet-tools-inspectcode"
            param("dotnet-tools-inspectcode.customCmdArgs", "--output=reports/cs-report.xml --no-swea --exclude=**/*.ts")
            param("dotnet-tools-inspectcode.solution", "VisualStudioToolSetBug.sln")
            param("jetbrains.resharper-clt.platform", "x64")
            param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.DEFAULT%")
        }
    }
}))

