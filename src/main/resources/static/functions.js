
function createTableRow(id, date, exam, grade) {
    var table = $("#tableId #tbodyId");
    table.append("<tr><th>" + id + "</th><td id=idx>" + date + "</td><td id=idx>" + exam + "</td><td>" + grade + "</td></tr>");
}

function findAndPopulate() {
    var email = $('#searchStudentInput').val();
    $('#searchStudentInput').val('');
    setStudentData(email);
}

function setStudentData(email) {
    var entryList = [];//id,subjectId,subjectName,date,grade
    $.get("/get/" + email, function (data) {
        $("#nameDiv #nameSpan").text(data.email);
        var dataRequests = [];
        var requestMap = {};
        var subjects = data.subjectList;
        for (var i = 0; i < subjects.length; i++) {
            var object = subjects[i];
            var request = $.get("/getExam/" + object.id);
            requestMap[object.id] = request;
            entryList.push({subjectId: object.id, grade: object.grade, exam: null, date: null});
            dataRequests.push(request);
        }
        $.when.apply(undefined, dataRequests).then(function () {
            $("#tableId #tbodyId tr").remove();
            $.each(requestMap, function (key, value) {
                for (var i = 0; i < entryList.length; i++) {
                    if (entryList[i].subjectId == key) {
                        entryList[i].exam = value.responseJSON.name;
                        entryList[i].date = new Date(value.responseJSON.date).toString("dd MMMM yyyy");
                        createTableRow(i + 1, entryList[i].date, entryList[i].exam, entryList[i].grade);
                    }
                }
            });
        });
    })
}

function insertData() {
    var subjectName = $('#subjectName').val();
    var grade = $('#grade').val();
    var student = $("#nameDiv #nameSpan").text();

    $.get("/get/" + student, function (data) {
        var exam = {
            name: subjectName, date: new Date()
        };

        var subject = {
            grade: grade, exam: exam, user: data
        };

        $.ajax({
            type: "POST",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            url: "/saveGrade",
            data: JSON.stringify(subject),
            dataType: 'json'
        }).done(handle(student));

    });
}

function handle(student) {
    setTimeout(function () {
        setStudentData(student)
    }, 1000);
}