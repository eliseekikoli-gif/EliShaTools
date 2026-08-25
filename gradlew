#!/bin/sh
APP_BASE_NAME=`basename "$0"`
DIRNAME=`dirname "$0"`
[ -z "$DIRNAME" ] && DIRNAME=.
APP_HOME=`cd "$DIRNAME" && pwd`

# Téléchargement et exécution automatique du wrapper officiel
if [ ! -f "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" ]; then
    mkdir -p "$APP_HOME/gradle/wrapper"
    curl -sLo "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" https://raw.githubusercontent.com/gradle/gradle/v8.4.0/gradle/wrapper/gradle-wrapper.jar
fi

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec java -Xmx1024m -Dorg.gradle.appname="$APP_BASE_NAME" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
