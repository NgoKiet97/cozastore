// Khi nội dung file html đã được hiển thị trên browser thì sẽ kích hoạt
$(document).ready(function(){
    
    // Đăng ký sự kiện click cho thẻ tag được chỉ định bên HTML
    $("#btn-sign-in").click(function(){
        // .val() : Lấy giá trị của thẻ input được chỉ định
        var username = $("#user").val()
        var password = $("#pass").val()

        // Xuất giá trị ra trên tab console trên trình duyệt
        // console.log("username : ",username, " password : ",password);

        //ajax : Dùng để call ngầm API mà không cần trình duyệt
        //axios, fetch
        //data : chỉ có khi tham số truyền ngầm
        $.ajax({
            url: "http://localhost:8080/login/signin",
            method: "post",
            data: {
                username: username,
                password: password
            }
        }).done(function(result){

            localStorage.setItem("token", result.data)
            console.log("đăng nhập thành công")
            // if(result.data){
            //     //Đăng nhập thành công, chuyển về page trang chủ
                window.location.href = "index.html"

            // } else {
            //     //Đăng nhập thất bại
            //     alert("Đăng nhập thất bại, vui lòng thử lại")
            // }
        })
    })


    //b1: đăng kí sự kiện click cho button
    //b2: lấy dữ liệu người dùng nhập ở màn hình signup 
    //b3: gửi dữ liệu người dùng nhập về cho BE

    //Đăng kí sự kiện click cho button
    $("#btn-signup").click(function(){
        var username = $("#user-signup").val()
        var password = $("#pass-signup").val()
        var repeatpassword = $("#repass").val()
        var email = $("#email").val()

        if($.trim(username) == ""){
            alert("Vui lòng nhập dữ liệu")
            return
        }
        
        if(password != repeatpassword){
            alert("Mật khẩu nhập lại không đúng")
            return
        }

        //Khai báo kiểu dữ liệu object và gán giá trị key - value tương ứng
        var dataToBE = {
                        username: username,
                        password : password,
                        email : email
                    }

        //Gọi dữ liệu và gửi về cho BE dạng raw JSON
        $.ajax({
            url: "http://localhost:8080/login/signup",
            method: "post",
            data: JSON.stringify(dataToBE), //Convert JSON về kiểu String
            contentType: "application/json"
        }).done(function(result){
            if(result.data){
                alert("Đăng kí tài khoản thành công")
            } else{
                alert("Đăng kí tài khoản thất bại")
            }
        })
    })


})