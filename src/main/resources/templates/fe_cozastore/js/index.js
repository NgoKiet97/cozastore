$(document).ready(function () {

    var token = localStorage.getItem("token")

    $.ajax({
        // headers: {
        //     "Authorization": "Bearer " + token
        // },
        method: "GET",
        url: "http://localhost:8080/category",
    })
        .done(function (result) {
            if (result.data) {
                $.each(result.data, function (index, value) {
                    //String literal
                    var content = `<button custom-id="${value.id}" class="btn-category stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".women">
                                 ${value.name}
                                </button>`
                    $("#tab-category").append(content)
                })
            }
            console.log("test", result)
        });

    $("#tab-category").on('click', '.btn-category', function () {
        
        var id = $(this).attr('custom-id')

        $.ajax({
            method: "GET",
            url: `http://localhost:8080/product/category/${id}`,
        })
            .done(function (result) {
                if (result.data) {
                    // $("#product-by-categoryID").remove()

                    $.each(result.data, function (index, value) {
                        //String literal
                        var content =
                            `<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 women">
                                <div class="block2">
                                    <div class="block2-pic hov-img0">
                                        <img src="${value.image}" alt="IMG-PRODUCT">
                                        <a href="#"
                                            class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
                                            Quick View
                                        </a>
                                    </div>
                                    <div class="block2-txt flex-w flex-t p-t-14">
                                        <div class="block2-txt-child1 flex-col-l ">
                                            <a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                                ${value.name}
                                            </a>
                                            <span class="stext-105 cl3">
                                                $${value.price}
                                            </span>
                                        </div>
                                        <div class="block2-txt-child2 flex-r p-t-3">
                                            <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                                <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                                                <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                                            </a>
                                        </div>
                                    </div>
                                </div>
				            </div>`
                        $("#product-by-categoryID").append(content)
                    })
                }

                console.log("test", result)
            });

    })

})