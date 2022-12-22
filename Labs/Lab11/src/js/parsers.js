function parseXML(xmlDocument){
    const numTags = xmlDocument.getElementsByTagName('num');
    const numbers = [];

    for (let i = 0; i < numTags.length; i++){
        numbers.push(+numTags[i].innerHTML);
    }

    return numbers;
}

function parseJSON(json) {
    const parsedJson = JSON.parse(json);

    return parsedJson.numbers.map(x => +x.rand);
}
