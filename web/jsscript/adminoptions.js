function redirectadministratorpage()
{
    swal("Admin!", "Redirecting To Admin Action Page!", "success").then(function (value) {
        window.location = "adminaction.jsp";
    });
}
function redirectvotingpage()
{
    swal("Admin!", "Redirecting To Voting Controller Page!", "success").then(function (value) {
        window.location = "VotingControllerServlet";
    });
}

function manageuser()
{
    swal("Admin!", "Redirecting To User Management Page!", "success").then(function (value) {
        window.location = "manageuser.jsp";
    });
}

function managecandidate()
{
    swal("Admin!", "Redirecting To Admin Action Page!", "success").then(function (value) {
        window.location = "managecandidate.jsp";
    });
}

//For Registration of Candidate
function showaddcandidateform()
{
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {id: "getid"};
    $.post("AddCandidateControllerServlet", data, function (responseText) {
        $("#cid").val(responseText);
        $('#cid').prop("disabled", true)
    });
}

// For registration of candidate request to servlet
function addcandidate()
{
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "AddNewCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (text) {
            if (text.trim() === 'alr') {
                swal("Admin!", " Sorry Candidate Already Present,\n Try with Another city", "error").then(function (value) {
                    showaddcandidateform();
                });
            } else {
                str = text;
                swal("Admin!", str, "success").then(function (value) {
                    showaddcandidateform();
                });
            }
        },
        error: function (e) {
            swal("Admin!", e, "success");
        }
    });
}

// After Entering of User Id get detail of user from users Table
function getdetails(e)
{
    if (e.keyCode === 13) {
        data = {uid: $("#uid").val()};
        $.post("AddCandidateControllerServlet", data, function (responseText) {
            var details = JSON.parse(responseText);
            var city = details.city;
            var uname = details.username;
            if (uname === "wrong")
                swal("Wrong Adhar!", "Adhar not found in DB", "error");
            else {
                $('#city').empty();
                $('#city').append(city);
                $('#cname').val(uname);
                $("#cname").prop("disabled", true);
            }

        });
    }
}

// show Candidate List and show them seperatly...
function showcandidate()
{
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white; font-weight:bold'>Candidate Id:</div></div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {
        var cidlist = JSON.parse(responseText);
        $('#cid').append(cidlist.cids);
    });


    $("#cid").change(function ()
    {
        var cid = $("#cid").val();
        alert("Sel id:" + cid);
        if (cid === '') {
            swal("No selection!", "Please select an id ", "error");
            return;
        }

        data = {data: cid};
        $.post("ShowCandidateControllerServlet", data, function (responseText)
        {
//            clearText();
            var details = JSON.parse(responseText);
            console.log(details.subdetails);
            $("#addresp").html(details.subdetails);

        });
    });
}














// Update request To servlet after clicking 'Update Candidate Button'
function updatecandidate()
{
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "UpdateCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (responseText, textStatus, xhr)
        {
            if (responseText.trim() === "success")
            {
                swal("Success!", "Updated Successfully!", "success");
                setTimeOut(showupdatecandidateform, 3000);
            }
            else
            {
                swal("Error!", "Some went wrong try again!", "error");
            }
        },
        error: function (e) {
            swal("Admin!", e, "success");
        }
    });
}

// Show candidate Id List so can update candidate 
function showupdatecandidateform() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><select id='cid'></select></td></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Update Candidate' onclick='updatecandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form><br>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addimg'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {
        var cidlist = JSON.parse(responseText);
        $('#cid').append(cidlist.cids);
    });

    $("#cid").change(function ()
    {
        var cid = $("#cid").val();
        alert("Sel id:  " + cid);
        if (cid === '') {
            swal("No selection!", "Please select an id ", "error");
            return;
        }

        data = {data: cid};
        $.post("UpdateCandidateControllerServlet", data, function (responseText)
        {
//            clearText();
            var details = JSON.parse(responseText);
            $("#candidateform").hide();
            $("#candidateform").fadeIn("3500");
            $("#uid").val(details.uid);
            $('#uid').prop("disabled", true)

            data = {uid: $("#uid").val()};
            $.post("AddCandidateControllerServlet", data, function (responseText) {
                var citydetails = JSON.parse(responseText);
                var city = citydetails.city;
                var uname = citydetails.username;
                if (uname === "wrong")
                    swal("Wrong Adhar!", "Adhar not found in DB", "error");
                else {
                    $('#city').empty();
                    $('#city').html(city);
                    $("#city option[value='" + details.city.trim() + "']").attr('selected', 'selected');
                    alert(details.city.trim());
                }
            });
            console.log(details.city);
            $("#cname").val(details.cname);
            $('#cname').prop("disabled", true)
            $("#city option[value='" + details.city.trim() + "']").attr('selected', 'selected');
//            alert(details.city.trim());
            $('#party').val(details.party);
            $('#addimg').html("<img src='data:image/jpg;base64," + details.symbol + "' style='width:300px;height:200px;'/>");
        });
    });



}

function electionresult()
{
    swal("Admin!", "Redirecting To Results Page!", "success").then(function (value) {
        window.location = "electionresult2.jsp";
    });
}


function deletecandidate()
{
    data = {cid: $("#cid").val()};
    $.post("DeleteCandidateControllerServlet", data, function (responseText) {
        if (responseText === 'success')
        {
            swal("Success", "Candidate Deleted Successfully \n Candidate ID - " + $("#cid").val(), "success");
            $("#cid").removeChild($("#cid option[value='" + $("#cid").val() + "']"));

        }
        else {
            swal("Failed", "Couldn't delete Candidate!", "error");
        }
    });
}
function showdeletecandidateform() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><select id='cid'></select></td></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><th><input type='button' value='Delete Candidate' onclick='deletecandidate()' id='delcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form><br>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addimg'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {
        var cidlist = JSON.parse(responseText);
        $('#cid').append(cidlist.cids);
    });

    $("#cid").change(function ()
    {
        var cid = $("#cid").val();
        alert("Sel id:  " + cid);
        if (cid === '') {
            swal("No selection!", "Please select an id ", "error");
            return;
        }

        data = {data: cid};
        $.post("UpdateCandidateControllerServlet", data, function (responseText)
        {
//            clearText();
            var details = JSON.parse(responseText);
            $("#candidateform").hide();
            $("#candidateform").fadeIn("3500");
            $("#uid").val(details.uid);
            $('#uid').prop("disabled", true)

            data = {uid: $("#uid").val()};
            $.post("AddCandidateControllerServlet", data, function (responseText) {
                var citydetails = JSON.parse(responseText);
                var city = citydetails.city;
                var uname = citydetails.username;
                if (uname === "wrong")
                    swal("Wrong Adhar!", "Adhar not found in DB", "error");
                else {
                    $('#city').empty();
                    $('#city').html(city);
                    $("#city option[value='" + details.city.trim() + "']").attr('selected', 'selected');
                    alert(details.city.trim());
                }
            });
            console.log(details.city);
            $("#cname").val(details.cname);
            $('#cname').prop("disabled", true);
            $("#city option[value='" + details.city.trim() + "']").attr('selected', 'selected');
            $('#city').prop("disabled", true);
//            alert(details.city.trim());

            $('#party').val(details.party);
            $('#party').prop("disabled", true)
            $('#addimg').html("<img src='data:image/jpg;base64," + details.symbol + "' style='width:300px;height:200px;'/>");
        });
    });

}



function removecandidateForm()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform");
    if (formdiv !== null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}