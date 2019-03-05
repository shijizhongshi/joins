$(function () {
    onloadprovince();
    $("#btnSearchData").click(function () {
        var txtprovince = $("#txtprovince").val();
        var txtcity = $("#txtcity").val();
        getDataByPage(txtprovince, txtcity, 0);
    })
})

function getDataByPage(txtprovince, txtcity, pagenow) {
    $.ajax({
        type: "post",
        url: "/ashx/SearchStore.ashx",
        data: { province: txtprovince, city: txtcity, pagenow: pagenow },
        success: function (data) {
            var dataJson = eval("(" + data + ")");

            if (dataJson[0] == "1") {
                $("#dataContent").html(dataJson[1]);
                $("#pageContent").html(dataJson[2]);
            }

            if (dataJson[0] == "0") {
                alert("该地区暂无店铺！");
                $("#dataContent").html("该地区暂无店铺");
                $("#pageContent").html("");
            }

            if (dataJson[0] == "-1") {
                alert("请选择城市，然后再试！");
            }

        },
        orrer: function () {

        }
    })
}