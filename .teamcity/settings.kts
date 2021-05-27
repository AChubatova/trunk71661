import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    vcsRoot(HttpsGithubComThirusabariCSharpSamplesForBeginners)

    buildType(ResharperDuplicates)

    params {
        param("s2", "s")
    }
}

object ResharperDuplicates : BuildType({
    name = "resharper duplicates"

    vcs {
        root(HttpsGithubComThirusabariCSharpSamplesForBeginners)
    }

    steps {
        step {
            name = "Resharper - Duplicates"
            type = "dotnet-tools-dupfinder"
            param("dotnet-tools-dupfinder.hashing.discard_local_variables_name", "true")
            param("dotnet-tools-dupfinder.hashing.discard_fields_name", "true")
            param("dotnet-tools-dupfinder.exclude_files", """
                **/*.Designer.cs
                Database/**
                **UnitTests**
                **/AssemblyInfo.cs
                EnergyWorkbench/Examples/**
                EnergyWorkbench/Tests/**
                **/ScipApi.*.cs
                ExcelTools/Sandbox/**
                **/LpSolveApi.cs
                **/*.generated.cs
            """.trimIndent())
            param("dotnet-tools-dupfinder.hashing.discard_types", "true")
            param("dotnet-tools-dupfinder.hashing.normalize_types", "true")
            param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.DEFAULT%")
        }
        script {
            scriptContent = "ping 127.0.0.1 -n 60 > nul"
        }
        step {
            type = "dotnet-tools-dupfinder"
            param("dotnet-tools-dupfinder.hashing.discard_local_variables_name", "true")
            param("dotnet-tools-dupfinder.hashing.discard_types", "true")
            param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.DEFAULT%")
        }
    }
})

object HttpsGithubComThirusabariCSharpSamplesForBeginners : GitVcsRoot({
    name = "https://github.com/thirusabari/C-Sharp-Samples-for-Beginners"
    url = "https://github.com/thirusabari/C-Sharp-Samples-for-Beginners"
    branch = "refs/heads/master"
})
