layui.define(["jquery", "layer", "miniAdmin"], function (exports) {
    var $ = layui.$,
        miniAdmin = layui.miniAdmin,
        layer = layui.layer
    ;

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
        handleNet(url, "get", data, 'html', function (d) {
            var dialogEdit = layer.open({
                type: 1,
                title: title,
                content: d,
                area: ['80%', '90%'],
            });
            callback && callback(dialogEdit);
        }, function (e) {
            var dialogEdit = layer.open({
                type: 1,
                title: title,
                content: xhr.responseText,
                area: ['80%', '90%'],
            });
            callback && callback(dialogEdit);
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

    function post(url, data, callback, error) {

        handleNet(url, "POST", 'application/x-www-form-urlencoded; charset=UTF-8', data, callback, error);
    }

    function get(url, data, callback, error) {
        handleNet(url, "GET", 'application/x-www-form-urlencoded; charset=UTF-8', data, callback, error);
    }

    function handleNet(url, type, contentType, data, callback, error) {
        // data= JSON.stringify(data);
        var param = {
            url: url,
            type: type,
            data: data,
            contentType: contentType,
            success: function (d) {
                if (d && d.code && d.code !== 0) {
                    miniAdmin.error(d.msg);
                    return;
                }
                callback && callback(d);
            },
            error: function (xhr, textstatus, thrown) {
                if (!error) {
                    miniAdmin.error(xhr.responseText)
                }
                error && error(xhr.responseText);
            }
        };
        // if (param.dataType === 'json') {
        //     param.contentType = false; // 注意这里应设为false
        //     param.processData = false;
        // }

        $.ajax(param);
    }


    var jman = {
        "loadPage": loadPage,
        "showDialog": showDialog,
        "confirm": confirm,
        "post": post,
        "get": get,
        "error": miniAdmin.error,
        "success": miniAdmin.success
    };
    exports("jman", jman);
});