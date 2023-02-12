function resultbycity()
{
    data = {
        check: "city"
    };
    removeElectionResult();
    $.post("ResultControllerServlet", data, function (responseText) {
        $("#result").append(responseText);
    });
}

function resultbyparty()
{
    data = {
        check: "party"
    };

    removeElectionResult();
    $.post("ResultControllerServlet", data, addresult);
}

function addresult(responseText)
{
    var data = JSON.parse(responseText);

    var options = {
        title: {
            text: "Voting Result by Evoting"
        },
        data: [{
                type: "pie",
                startAngle: 45,
                showInLegend: "true",
                legendText: "{label}",
                indexLabel: "{label} ({y})",
                yValueFormatString: "#,##0.#" % "",
                dataPoints: data.array
            }]
    };
    $("#result").append(data.result);
    $("#electionresult").CanvasJSChart(options);
}

function removeElectionResult()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("electionresult");
    if (formdiv !== null)
    {
        $("#electionresult").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}