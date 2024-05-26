rootProject.name = "untitled1"
include("src:main:test")
findProject(":src:main:test")?.name = "test"
