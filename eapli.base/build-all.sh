#!/usr/bin/env bash
ECHO OFF
ECHO make sure JAVA_HOME is set to JDK folder
ECHO make sure maven is on the system PATH
# shellcheck disable=SC2086
mvn $1 dependency:copy-dependencies package
