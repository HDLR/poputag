function getDate(addYears) {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var endYear = parseInt(year)+parseInt(addYears);
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var  endDate= endYear + seperator1 + month + seperator1 + strDate;
    return endDate;
}