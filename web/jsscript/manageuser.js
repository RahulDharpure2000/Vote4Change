function registeruser() {
    window.location = 'registration.html';
}

function percentege()
{
    removeElectionResult();
    $.post("ManageUserControllerServlet", null, addresult);
}

function addresult(responseText)
{
    console.log(responseText);
    var data = JSON.parse(responseText);

    var options = {
        animationEnabled: true,
        title: {
            text: "Male And Female Vote %"
        },
        axisY: {
            title: "Male + Female"
        },
        toolTip: {
            shared: true,
            reversed: true
        },
        data: [{
                type: "stackedColumn",
                name: "Male / Female Vote %",
                showInLegend: "true",
                yValueFormatString: "",
                dataPoints: data.MaleFemaleVote
            },
            {
                type: "stackedColumn",
                name: "Male / Female %",
                showInLegend: "true",
                yValueFormatString: "",
                dataPoints: data.MaleFemale
            }]
    };


    $("#result").append(data.result);
    $("#chartContainer").CanvasJSChart(options);
}


 function showusers()
 {
    removeElectionResult();
    $.post("UserControllerServlet", null, function(responseText){
        $("#result").append(responseText.trim());
    });
 }
function removeElectionResult()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("chartContainer");
    if (formdiv !== null)
    {
        $("#chartContainer").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}