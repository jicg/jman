/**
 * date:2019/08/16
 * author:Mr.Chung
 * description:此处放layui自定义扩展
 */

// window.rootPath = (function (src) {
//     console.log(src);
//     console.log(document.scripts);
//     src = document.scripts[document.scripts.length - 1].src;
//     console.log(src);
//     return src.substring(0, src.lastIndexOf("/") + 1);
// })();

layui.config({
    base:   "/res/js/lay-module/",
    debug: true,
    version: false
}).extend({
    jman: "jman/jman", // layuimini后台扩展
    miniAdmin: "layuimini/miniAdmin", // layuimini后台扩展
    miniMenu: "layuimini/miniMenu", // layuimini菜单扩展
    miniPage: "layuimini/miniPage", // layuimini 单页扩展
    miniTab: "layuimini/miniTab", // layuimini tab扩展
    miniTheme: "layuimini/miniTheme", // layuimini 主题扩展
    miniTongji: "layuimini/miniTongji", // layuimini 统计扩展
    step: 'step-lay/step', // 分步表单扩展
    treeTable: 'treetable-lay/treeTable', //table树形扩展
    tableSelect: 'tableSelect/tableSelect', // table选择扩展
    treeSelect: 'treeSelect/treeSelect', // table选择扩展
    iconPickerFa: 'iconPicker/iconPickerFa', // fa图标选择扩展
    iconPicker: 'iconPicker/iconPicker', // 图标选择扩展
    echarts: 'echarts/echarts', // echarts图表扩展
    echartsTheme: 'echarts/echartsTheme', // echarts图表主题扩展
    wangEditor: 'wangEditor/wangEditor', // wangEditor富文本扩展
    layarea: 'layarea/layarea', //  省市县区三级联动下拉选择器
    dtree: 'dtree/dtree'
});