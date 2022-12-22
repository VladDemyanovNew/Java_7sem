function createRequest() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }

    try {
        return new ActiveXObject('Msxml2.XMLHTTP');
    }
    catch(error) {
        try {
            return new ActiveXObject('Microsoft.XMLHTTP');
        } catch(error) {
            return null;
        }
    }
}
