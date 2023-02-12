
var userid, username, email, password, cpassword, mobile, city, address,gender;
function redirectUser()
{
    window.location = "login.html";
}
function handleError(xhr)
{
    swal("Error", "Registration Failed! Try again..", "error");
    return;
}
function processResponse(responseText, textStatus, xhr)
{
    if (responseText.trim() === "success")
    {
        swal("Success!", "Registered Successfully! please Login", "success");
        setTimeOut(redirectUser, 3000);
    }
    else if (responseText.trim() === "uap")
    {
        swal("Error!", "User already Present!", "error");
    }
    else
    {
        swal("Error!", "Some went wrong try again!", "error");
    }
}
function addUser()
{
    userid = $("#userid").val();
    username = $("#username").val();
    email = $("#email").val();
    password = $("#password").val();
    cpassword = $("#cpassword").val();
    mobile = $("#mobile").val();
    city = $("#city").val();
    address = $("#address").val();
    gender=$('input[name="gender"]:checked').val();
    if (validateUser() === false)
    {
        swal("Error", "All Feilds are mandatory!", "error");
        return;
    }
    else {
        if (password != cpassword)
        {
            swal("Error!", "Please enter password correctly!", "error");
            return;
        }
        else if (validateEmail() == false) {
            swal("Error!", "Please enter valid email address", "error");
            return;
        }
        else if (validateMobile() == false)
        {
            swal("Error!", "Please enter valid mobile no", "error");
            return;
        }
        var data = {
            userid: userid,
            username: username,
            email: email,
            password: password,
            mobile: mobile,
            city: city,
            address: address,
            gender:gender
        };
        console.log(data);
        xhr = $.post("RegistrationControllerServlet", data, processResponse);
        xhr.onerror=handleError;
    }
}

function validateUser()
{
    if (username === "" || userid === "" || email === "" || password === "" || cpassword === "" || mobile === "" || city === "" || address === "" || mobile === ""|| gender==="")
    {
        return false;
    }
    return true
}

function validateEmail()
{
    var attherate = email.indexOf("@");
    var dot = email.indexOf(".")
    if (attherate < 1 || dot < attherate + 2 )
    {
        return false;
    }
    return true;
}

function validateMobile()
{
    if (!mobile.match('[0-9]{10}'))
    {
        swal("Error!", "Please enter a valide mobile No!", "error");
        return false;
    }
    return true;
}