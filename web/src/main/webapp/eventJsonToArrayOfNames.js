function eventJsonToArrayOfNames(json){
    var result = [];
    var output = [];
    var keys = Object.keys(json);
    keys.forEach(function(key){
        result.push(json[key]);
    });
    return result;
   /* result = jQuery.parseJSON(json);
    result.forEach(output.push(read_prop(obj,'name')));
    /* for each (var event in result){
       output.push(read_prop(event,'name'));
     }

     */
  //  return output;
}
function read_prop(obj, prop) {
    return obj[prop];
}