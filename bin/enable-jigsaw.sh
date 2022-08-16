#!/usr/bin/env bash
# Renames module-info.java files to disable/enable the Java Module System.
# Usage example: enable-jigsaw.sh disable

if [[ $# != 1 ]]; then
    echo "Please specify exactly one parameter."
    exit 1
fi

enable="$1"

case "${enable}" in
    enable)
        mv leasingninja-sales/src/main/java/module-info.java.hidden leasingninja-sales/src/main/java/module-info.java
        mv leasingninja-riskmanagement/src/main/java/module-info.java.hidden leasingninja-riskmanagement/src/main/java/module-info.java
        mv leasingninja-webapp/src/main/java/module-info.java.hidden leasingninja-webapp/src/main/java/module-info.java ;;
    disable)
        mv leasingninja-sales/src/main/java/module-info.java leasingninja-sales/src/main/java/module-info.java.hidden
        mv leasingninja-riskmanagement/src/main/java/module-info.java leasingninja-riskmanagement/src/main/java/module-info.java.hidden
        mv leasingninja-webapp/src/main/java/module-info.java leasingninja-webapp/src/main/java/module-info.java.hidden ;;
    *)
        exit -1 ;;
esac
