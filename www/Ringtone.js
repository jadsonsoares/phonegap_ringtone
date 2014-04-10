var calendar =  {
    createEvent: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'ringtone', // mapped to our native Java class called "Calendar"
            'selectRingtone', // with this action name
            []
        );
    }
}
module.exports = calendar;