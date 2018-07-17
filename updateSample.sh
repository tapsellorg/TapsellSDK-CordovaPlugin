git clone git@github.com:tapsellorg/TapsellSDK-CordovaSample.git
cd TapsellSDK-CordovaSample
cordova plugin remove tapsell-v3-cordova-plugin
cordova plugin add tapsell-v3-cordova-plugin
git add --all
git config --local user.name "circleCI"
git config --local user.email "technical.tapsell@gmail.com"
git commit -m "updating for sdk cordova version $1"
git push
cd ..
rm -rf TapsellSDK-CordovaSample