#!/bin/bash

set -e
set -o pipefail

if [[ -z "${BUTLER_API_KEY}" ]]; then
  echo "Unable to deploy! No BUTLER_API_KEY environment variable specified!"
  exit 1
fi

prepare_butler() {
    echo "Preparing butler..."
    download_if_not_exist http://dl.itch.ovh/butler/linux-amd64/head/butler butler
    chmod +x butler
}

prepare_packr() {
    echo "Preparing packr..."
    download_if_not_exist https://libgdx.badlogicgames.com/ci/packr/packr.jar packr.jar
    cp desktop/build/libs/desktop-1.0.jar fishmonger.jar
}

# platform = $1
# packrConfig = $2
# jre = $3
# ext = $4
prepare_and_push() {
    echo "Preparing $1 build..."
    download_if_not_exist https://bitbucket.org/alexkasko/openjdk-unofficial-builds/downloads/$3 $3
    java -jar packr.jar --jdk $3 --output fishmonger-$1$4 -- deployment/desktop/$2

    zip -r fishmonger-$1$4.zip fishmonger-$1$4
    rm -rf fishmonger-$1$4

    echo "Push $1 build to itch.io..."
    ./butler push fishmonger-$1$4.zip bitbrain/fishmonger:$1
}

prepare_and_push_raw() {
    echo "Preparing $1 build..."
    rm -rf fishmonger-$1
    mkdir fishmonger-$1
    mkdir fishmonger-$1/assets
    cp -rf core/assets/* fishmonger-$1/assets
    cp fishmonger.jar fishmonger-$1

    zip -r fishmonger-$1.zip fishmonger-$1
    rm -rf fishmonger-$1

    echo "Push $1 build to itch.io..."
    ./butler push fishmonger-$1.zip bitbrain/fishmonger:$1
}

download_if_not_exist() {
    if [ ! -f $2 ]; then
        curl -L -O $1 > $2
    fi
}

# Initialisation
prepare_butler
prepare_packr

prepare_and_push "windows" "packr-windows.json" "openjdk-1.7.0-u80-unofficial-windows-i586-image.zip"
prepare_and_push_raw "windows-mac-linux"

echo "Done."
exit 0
