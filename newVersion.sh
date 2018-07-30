#!/usr/bin/env bash
if [[ "$1" == "" ]]; then
    echo "please enter the new version"; exit 1
fi
if [[ "$2" == "" ]]; then
    echo "please enter tapsell sdk version"; exit 1
fi
git checkout master
pluginVersion=$1
sdkVersion=$2
echo $pluginVersion
echo $sdkVersion
wget https://storage.backtory.com/tapsell-server/sdk/cordova/tapsell-sdk-cordova-$sdkVersion.jar
rm ./src/android/*.jar
mv tapsell-sdk-cordova-$sdkVersion.jar ./src/android/tapsell-cordova-v$sdkVersion.jar
echo "replaced jar"
newLine="       <source-file src=\"src/android/tapsell-cordova-v$sdkVersion.jar\" target-dir=\"libs\" />"
sed -i "/source-file src=\"src\/android\/tapsell-cordova-v/c\ $newLine" plugin.xml
echo "updated plugin.xml"
newLine="var tapsellPluginVersion = \"$pluginVersion\";"
sed -i "/tapsellPluginVersion = /c\ $newLine" www/tapsell.js
echo "updated www/tapsell.js"
sed -i "5s/.*/    version=\"$pluginVersion\">/" plugin.xml
echo "updated plugin.xml"
sed -i "2s/.*/  \"version\": \"$pluginVersion\",/" package.json
echo "updated package.json"
git add --all
git commit -m "updating for plugin version $pluginVersion"
#git push