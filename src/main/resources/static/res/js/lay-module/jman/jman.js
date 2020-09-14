layui.define(["jquery", "layer"], function (exports) {
    var $ = layui.$,
        // miniAdmin = layui.miniAdmin,
        layer = layui.layer;

    function loadPage(url, data, callback) {
        $.ajax({
            url: url,
            type: 'get',
            data: data,
            dataType: 'html',
            success: function (d) {
                callback && callback(d);
            },
            error: function (xhr, textstatus, thrown) {
                callback && callback(xhr.responseText);
            }
        });
    }

    function showDialog(title, url, data, callback) {
        if (!data) data = {};
        data.comb = "sub";
        $.ajax({
            url: url,
            type: 'get',
            data: data,
            dataType: 'html',
            success: function (d) {
                var dialogEdit = layer.open({
                    type: 1,
                    title: title,
                    content: d,
                    area: ['80%', '90%'],
                });
                callback && callback(dialogEdit);
            },
            error: function (xhr, textstatus, thrown) {
                var dialogEdit = layer.open({
                    type: 1,
                    title: title,
                    content: xhr.responseText,
                    area: ['80%', '90%'],
                });
                callback && callback(dialogEdit);
            }
        });
    }

    function confirm(title, callback) {
        var confirm = layer.confirm(title, {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(confirm);
            callback && callback();
        }, function () {
            layer.close(confirm);
        });
    }

    var jman = {"loadPage": loadPage, "showDialog": showDialog,"confirm":confirm};
    exports("jman", jman);
});