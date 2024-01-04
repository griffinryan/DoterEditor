# Doter IDE and Code Editor

![doteralpha.png](https://torpoisebucket.s3.us-west-2.amazonaws.com/doteralpha.png)

**_Doter: "Dreams of Text Editors Running"_**

### Overview

- Doter Editor is a alpha-build of a full-featured IDE. Now building with JDK 21 LTS on all platforms.

- Doter current supports code editing of C#, C++, C, Python, Java, Ruby, HTML, CSS, Javascript, Markdown, and much more.

- Doter Editor is based off [Monaco](https://github.com/microsoft/monaco-editor), the powerful text editor from Microsoft's [VSCode](https://github.com/microsoft/vscode).

----
----

[![GitHub issues by-label](https://img.shields.io/github/issues/griffinryan/doter/help%20wanted?label=issues%20need%20help&logo=github)](https://github.com/griffinryan/doter/issues?q=label%3A%22help+wanted%22+is%3Aopen+is%3Aissue)

Doter Editor is built with [Gradle](https://github.com/gradle/gradle)!

[dotereditor.com](https://dotereditor.com/)

___
### Support
- Doter Editor is created by [Griffin Ryan][griffinryan-github].

- Found a problem? Pull requests are welcome.

- If you have ideas, questions or suggestions - **Welcome to [discussions](https://github.com/griffinryan/doter/discussions)**. 😊
___


## Get started

> Clone the Doter Editor project.

    git clone https://github.com/griffinryan/DoterEditor.git
    cd DoterEditor/

> Use [Maven](https://github.com/apache/maven) to build the project.

    mvn clean install
    mvn compile
    mvn exec:java -Dexec.mainClass=com.griffinryan.doter.DoterApplication

---

## To-Do:

> Add settings panel

> Implement terminal emulation support

> Add compiler/code-running functionalities based on current project language

> Create a welcome splash screen for the end user's ease of access.

> Track current problems in dedicated/individual user messages to eventually display in a pane dedicated to project errors, warnings, and output.

> Add event listening for modified files that are not yet saved, along with traditional indicators to display to the user. Along with this, if a file is unsaved display a "quit confirmation" message before the user can quit the application.

[griffinryan-github]: https://github.com/griffinryan/
