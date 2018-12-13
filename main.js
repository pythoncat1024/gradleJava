
function printJavaList(list){
    var len = list.length;
    for(var i = 0;i<len;i++){
        print("data from java: "+list.get(i));
    }
    return list;
}