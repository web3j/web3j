package org.web3j.console;

public class ProjectTemplates {
    static final String rawJavaClass =
            "package <package_name>;\n" + "\n" + "public class <class_name>{\n" + "\n" + "}";
    static final String rawGradleBuildFile =

            "plugins {\n" +
                    "    id 'java'\n" +
                    "    id 'org.web3j' version '4.4.0'\n" +
                    "}\n" +
                    "\n" +
                    "group '<package_name>'\n" +
                    "version '0.1.0'\n" +
                    "\n" +
                    "sourceCompatibility = 1.8\n" +
                    "\n" +
                    "repositories {\n" +
                    "    mavenCentral()\n" +
                    "}\n" +
                    "\n" +
                    "web3j {\n" +
                    "    generatedPackageName = '<package_name>.generated.contracts'\n" +
                    "    excludedContracts = ['Mortal']\n" +
                    "}\n" +
                    "\n" +
                    "ext {\n" +
                    "    web3jVersion = '4.4.0'\n" +
                    "    logbackVersion = '1.2.3'\n" +
                    "    junitVersion = '4.12'\n" +
                    "}\n" +
                    "\n" +
                    "dependencies {\n" +
                    "    implementation \"org.web3j:core:$web3jVersion\",\n" +
                    "            \"ch.qos.logback:logback-core:$logbackVersion\",\n" +
                    "            \"ch.qos.logback:logback-classic:$logbackVersion\"\n" +
                    "    testImplementation \"junit:junit:$junitVersion\"\n" +
                    "}";


    static final String rawGradleSettingsFile = "pluginManagement {\n" +
                    "    repositories {\n" +
                    "        mavenCentral()\n" +
                    "        gradlePluginPortal()\n" +
                    "    }\n" +
                    "}\n" +
                    "rootProject.name = '<project_name>'\n";
    static final String solidityContract = "pragma solidity ^0.4.25;\n" +
            "\n" +
            "// Modified Greeter contract. Based on example at https://www.ethereum.org/greeter.\n" +
            "\n" +
            "contract Mortal {\n" +
            "    /* Define variable owner of the type address*/\n" +
            "    address owner;\n" +
            "\n" +
            "    /* this function is executed at initialization and sets the owner of the contract */\n" +
            "    constructor () public { owner = msg.sender; }\n" +
            "\n" +
            "    /* Function to recover the funds on the contract */\n" +
            "    function kill() public { if (msg.sender == owner) selfdestruct(owner); }\n" +
            "}\n" +
            "\n" +
            "contract Greeter is Mortal {\n" +
            "    /* define variable greeting of the type string */\n" +
            "    string greeting;\n" +
            "\n" +
            "    /* this runs when the contract is executed */\n" +
            "    constructor (string _greeting) public {\n" +
            "        greeting = _greeting;\n" +
            "    }\n" +
            "\n" +
            "    function newGreeting(string _greeting) public {\n" +
            "        emit Modified(greeting, _greeting, greeting, _greeting);\n" +
            "        greeting = _greeting;\n" +
            "    }\n" +
            "\n" +
            "    /* main function */\n" +
            "    function greet() public constant returns (string)  {\n" +
            "        return greeting;\n" +
            "    }\n" +
            "\n" +
            "    /* we include indexed events to demonstrate the difference that can be\n" +
            "    captured versus non-indexed */\n" +
            "    event Modified(\n" +
            "            string indexed oldGreetingIdx, string indexed newGreetingIdx,\n" +
            "            string oldGreeting, string newGreeting);\n" +
            "}";
    static final String gradleWrapperProperties =
           "distributionBase=GRADLE_USER_HOME\n" +
                   "distributionPath=wrapper/dists\n" +
                   "zipStoreBase=GRADLE_USER_HOME\n" +
                   "zipStorePath=wrapper/dists\n" +
                   "distributionUrl=https\\://services.gradle.org/distributions/gradle-5.4.1-all.zip";
    static final String gradlew =
            "#!/usr/bin/env sh\n" +
                    "\n" +
                    "##############################################################################\n" +
                    "##\n" +
                    "##  Gradle start up script for UN*X\n" +
                    "##\n" +
                    "##############################################################################\n" +
                    "\n" +
                    "# Attempt to set APP_HOME\n" +
                    "# Resolve links: $0 may be a link\n" +
                    "PRG=\"$0\"\n" +
                    "# Need this for relative symlinks.\n" +
                    "while [ -h \"$PRG\" ] ; do\n" +
                    "    ls=`ls -ld \"$PRG\"`\n" +
                    "    link=`expr \"$ls\" : '.*-> \\(.*\\)$'`\n" +
                    "    if expr \"$link\" : '/.*' > /dev/null; then\n" +
                    "        PRG=\"$link\"\n" +
                    "    else\n" +
                    "        PRG=`dirname \"$PRG\"`\"/$link\"\n" +
                    "    fi\n" +
                    "done\n" +
                    "SAVED=\"`pwd`\"\n" +
                    "cd \"`dirname \\\"$PRG\\\"`/\" >/dev/null\n" +
                    "APP_HOME=\"`pwd -P`\"\n" +
                    "cd \"$SAVED\" >/dev/null\n" +
                    "\n" +
                    "APP_NAME=\"Gradle\"\n" +
                    "APP_BASE_NAME=`basename \"$0\"`\n" +
                    "\n" +
                    "# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n" +
                    "DEFAULT_JVM_OPTS=\"\"\n" +
                    "\n" +
                    "# Use the maximum available, or set MAX_FD != -1 to use that value.\n" +
                    "MAX_FD=\"maximum\"\n" +
                    "\n" +
                    "warn () {\n" +
                    "    echo \"$*\"\n" +
                    "}\n" +
                    "\n" +
                    "die () {\n" +
                    "    echo\n" +
                    "    echo \"$*\"\n" +
                    "    echo\n" +
                    "    exit 1\n" +
                    "}\n" +
                    "\n" +
                    "# OS specific support (must be 'true' or 'false').\n" +
                    "cygwin=false\n" +
                    "msys=false\n" +
                    "darwin=false\n" +
                    "nonstop=false\n" +
                    "case \"`uname`\" in\n" +
                    "  CYGWIN* )\n" +
                    "    cygwin=true\n" +
                    "    ;;\n" +
                    "  Darwin* )\n" +
                    "    darwin=true\n" +
                    "    ;;\n" +
                    "  MINGW* )\n" +
                    "    msys=true\n" +
                    "    ;;\n" +
                    "  NONSTOP* )\n" +
                    "    nonstop=true\n" +
                    "    ;;\n" +
                    "esac\n" +
                    "\n" +
                    "CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar\n" +
                    "\n" +
                    "# Determine the Java command to use to start the JVM.\n" +
                    "if [ -n \"$JAVA_HOME\" ] ; then\n" +
                    "    if [ -x \"$JAVA_HOME/jre/sh/java\" ] ; then\n" +
                    "        # IBM's JDK on AIX uses strange locations for the executables\n" +
                    "        JAVACMD=\"$JAVA_HOME/jre/sh/java\"\n" +
                    "    else\n" +
                    "        JAVACMD=\"$JAVA_HOME/bin/java\"\n" +
                    "    fi\n" +
                    "    if [ ! -x \"$JAVACMD\" ] ; then\n" +
                    "        die \"ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME\n" +
                    "Please set the JAVA_HOME variable in your environment to match the\n" +
                    "location of your Java installation.\"\n" +
                    "    fi\n" +
                    "else\n" +
                    "    JAVACMD=\"java\"\n" +
                    "    which java >/dev/null 2>&1 || die \"ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n" +
                    "Please set the JAVA_HOME variable in your environment to match the\n" +
                    "location of your Java installation.\"\n" +
                    "fi\n" +
                    "\n" +
                    "# Increase the maximum file descriptors if we can.\n" +
                    "if [ \"$cygwin\" = \"false\" -a \"$darwin\" = \"false\" -a \"$nonstop\" = \"false\" ] ; then\n" +
                    "    MAX_FD_LIMIT=`ulimit -H -n`\n" +
                    "    if [ $? -eq 0 ] ; then\n" +
                    "        if [ \"$MAX_FD\" = \"maximum\" -o \"$MAX_FD\" = \"max\" ] ; then\n" +
                    "            MAX_FD=\"$MAX_FD_LIMIT\"\n" +
                    "        fi\n" +
                    "        ulimit -n $MAX_FD\n" +
                    "        if [ $? -ne 0 ] ; then\n" +
                    "            warn \"Could not set maximum file descriptor limit: $MAX_FD\"\n" +
                    "        fi\n" +
                    "    else\n" +
                    "        warn \"Could not query maximum file descriptor limit: $MAX_FD_LIMIT\"\n" +
                    "    fi\n" +
                    "fi\n" +
                    "\n" +
                    "# For Darwin, add options to specify how the application appears in the dock\n" +
                    "if $darwin; then\n" +
                    "    GRADLE_OPTS=\"$GRADLE_OPTS \\\"-Xdock:name=$APP_NAME\\\" \\\"-Xdock:icon=$APP_HOME/media/gradle.icns\\\"\"\n" +
                    "fi\n" +
                    "\n" +
                    "# For Cygwin, switch paths to Windows format before running java\n" +
                    "if $cygwin ; then\n" +
                    "    APP_HOME=`cygpath --path --mixed \"$APP_HOME\"`\n" +
                    "    CLASSPATH=`cygpath --path --mixed \"$CLASSPATH\"`\n" +
                    "    JAVACMD=`cygpath --unix \"$JAVACMD\"`\n" +
                    "\n" +
                    "    # We build the pattern for arguments to be converted via cygpath\n" +
                    "    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`\n" +
                    "    SEP=\"\"\n" +
                    "    for dir in $ROOTDIRSRAW ; do\n" +
                    "        ROOTDIRS=\"$ROOTDIRS$SEP$dir\"\n" +
                    "        SEP=\"|\"\n" +
                    "    done\n" +
                    "    OURCYGPATTERN=\"(^($ROOTDIRS))\"\n" +
                    "    # Add a user-defined pattern to the cygpath arguments\n" +
                    "    if [ \"$GRADLE_CYGPATTERN\" != \"\" ] ; then\n" +
                    "        OURCYGPATTERN=\"$OURCYGPATTERN|($GRADLE_CYGPATTERN)\"\n" +
                    "    fi\n" +
                    "    # Now convert the arguments - kludge to limit ourselves to /bin/sh\n" +
                    "    i=0\n" +
                    "    for arg in \"$@\" ; do\n" +
                    "        CHECK=`echo \"$arg\"|egrep -c \"$OURCYGPATTERN\" -`\n" +
                    "        CHECK2=`echo \"$arg\"|egrep -c \"^-\"`                                 ### Determine if an option\n" +
                    "\n" +
                    "        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition\n" +
                    "            eval `echo args$i`=`cygpath --path --ignore --mixed \"$arg\"`\n" +
                    "        else\n" +
                    "            eval `echo args$i`=\"\\\"$arg\\\"\"\n" +
                    "        fi\n" +
                    "        i=$((i+1))\n" +
                    "    done\n" +
                    "    case $i in\n" +
                    "        (0) set -- ;;\n" +
                    "        (1) set -- \"$args0\" ;;\n" +
                    "        (2) set -- \"$args0\" \"$args1\" ;;\n" +
                    "        (3) set -- \"$args0\" \"$args1\" \"$args2\" ;;\n" +
                    "        (4) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" ;;\n" +
                    "        (5) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" ;;\n" +
                    "        (6) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" ;;\n" +
                    "        (7) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" ;;\n" +
                    "        (8) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" ;;\n" +
                    "        (9) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" \"$args8\" ;;\n" +
                    "    esac\n" +
                    "fi\n" +
                    "\n" +
                    "# Escape application args\n" +
                    "save () {\n" +
                    "    for i do printf %s\\\\n \"$i\" | sed \"s/'/'\\\\\\\\''/g;1s/^/'/;\\$s/\\$/' \\\\\\\\/\" ; done\n" +
                    "    echo \" \"\n" +
                    "}\n" +
                    "APP_ARGS=$(save \"$@\")\n" +
                    "\n" +
                    "# Collect all arguments for the java command, following the shell quoting and substitution rules\n" +
                    "eval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \"\\\"-Dorg.gradle.appname=$APP_BASE_NAME\\\"\" -classpath \"\\\"$CLASSPATH\\\"\" org.gradle.wrapper.GradleWrapperMain \"$APP_ARGS\"\n" +
                    "\n" +
                    "# by default we should be in the correct project dir, but when run from Finder on Mac, the cwd is wrong\n" +
                    "if [ \"$(uname)\" = \"Darwin\" ] && [ \"$HOME\" = \"$PWD\" ]; then\n" +
                    "  cd \"$(dirname \"$0\")\"\n" +
                    "fi\n" +
                    "\n" +
                    "exec \"$JAVACMD\" \"$@\"\n";

    static final String gradlewBat =
            "@if \"%DEBUG%\" == \"\" @echo off\n" +
                    "@rem ##########################################################################\n" +
                    "@rem\n" +
                    "@rem  Gradle startup script for Windows\n" +
                    "@rem\n" +
                    "@rem ##########################################################################\n" +
                    "\n" +
                    "@rem Set local scope for the variables with windows NT shell\n" +
                    "if \"%OS%\"==\"Windows_NT\" setlocal\n" +
                    "\n" +
                    "set DIRNAME=%~dp0\n" +
                    "if \"%DIRNAME%\" == \"\" set DIRNAME=.\n" +
                    "set APP_BASE_NAME=%~n0\n" +
                    "set APP_HOME=%DIRNAME%\n" +
                    "\n" +
                    "@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n" +
                    "set DEFAULT_JVM_OPTS=\n" +
                    "\n" +
                    "@rem Find java.exe\n" +
                    "if defined JAVA_HOME goto findJavaFromJavaHome\n" +
                    "\n" +
                    "set JAVA_EXE=java.exe\n" +
                    "%JAVA_EXE% -version >NUL 2>&1\n" +
                    "if \"%ERRORLEVEL%\" == \"0\" goto init\n" +
                    "\n" +
                    "echo.\n" +
                    "echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n" +
                    "echo.\n" +
                    "echo Please set the JAVA_HOME variable in your environment to match the\n" +
                    "echo location of your Java installation.\n" +
                    "\n" +
                    "goto fail\n" +
                    "\n" +
                    ":findJavaFromJavaHome\n" +
                    "set JAVA_HOME=%JAVA_HOME:\"=%\n" +
                    "set JAVA_EXE=%JAVA_HOME%/bin/java.exe\n" +
                    "\n" +
                    "if exist \"%JAVA_EXE%\" goto init\n" +
                    "\n" +
                    "echo.\n" +
                    "echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%\n" +
                    "echo.\n" +
                    "echo Please set the JAVA_HOME variable in your environment to match the\n" +
                    "echo location of your Java installation.\n" +
                    "\n" +
                    "goto fail\n" +
                    "\n" +
                    ":init\n" +
                    "@rem Get command-line arguments, handling Windows variants\n" +
                    "\n" +
                    "if not \"%OS%\" == \"Windows_NT\" goto win9xME_args\n" +
                    "\n" +
                    ":win9xME_args\n" +
                    "@rem Slurp the command line arguments.\n" +
                    "set CMD_LINE_ARGS=\n" +
                    "set _SKIP=2\n" +
                    "\n" +
                    ":win9xME_args_slurp\n" +
                    "if \"x%~1\" == \"x\" goto execute\n" +
                    "\n" +
                    "set CMD_LINE_ARGS=%*\n" +
                    "\n" +
                    ":execute\n" +
                    "@rem Setup the command line\n" +
                    "\n" +
                    "set CLASSPATH=%APP_HOME%\\gradle\\wrapper\\gradle-wrapper.jar\n" +
                    "\n" +
                    "@rem Execute Gradle\n" +
                    "\"%JAVA_EXE%\" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% \"-Dorg.gradle.appname=%APP_BASE_NAME%\" -classpath \"%CLASSPATH%\" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%\n" +
                    "\n" +
                    ":end\n" +
                    "@rem End local scope for the variables with windows NT shell\n" +
                    "if \"%ERRORLEVEL%\"==\"0\" goto mainEnd\n" +
                    "\n" +
                    ":fail\n" +
                    "rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of\n" +
                    "rem the _cmd.exe /c_ return code!\n" +
                    "if  not \"\" == \"%GRADLE_EXIT_CONSOLE%\" exit 1\n" +
                    "exit /b 1\n" +
                    "\n" +
                    ":mainEnd\n" +
                    "if \"%OS%\"==\"Windows_NT\" endlocal\n" +
                    "\n" +
                    ":omega";
}