
var videoplayer =  {
    play: function(name,successCallback,errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'VideoPlayerPlugin', // mapped to our native Java class called "Calendar"
            'play', // with this action name
            [name]
        );
    }
}
module.exports = videoplayer;
