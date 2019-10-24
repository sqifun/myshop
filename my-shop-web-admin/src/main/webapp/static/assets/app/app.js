let App = function () {

    //iCheck
    let _masterCheckbox;
    let _checkbox;

    //用户存放id的数据
    let _idArray;

    //默认的Dropzone参数
    let defaultDropzone = {
        url: "",
        paramName: "dropFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles +"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * 私有方法，初始化ICheck
     */
    var handlerInitCheckbox = function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制端Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        //获取全部Checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox全选功能
     */
    let handleCheckboxAll = function () {

        _masterCheckbox.on("ifClicked", function (e) {
            console.log(e.target.checked);
            //返回true，表示没有选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });

    };

    /**
     * 删除单笔记录
     * @param url
     * @param id
     * @param msg
     */
    let handlerDeleteSingle = function(url, id, msg) {
        //可选参数
        if (!msg) {
            msg = null;
        }
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null ? "您确定删除数据项吗？" : msg);
        $("#modal-default").modal("show");

        //绑定删除事件
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });

    };

    /**
     * 批量删除
     * @param url
     */
    let handleDeleteMulti = function (url) {
        _idArray = new Array();

        //将选中元素的ID放入数组中
        _checkbox.each(function () {
            let _id = $(this).attr("id");
            if(_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确定删除数据项吗？");
        }

        //点击删除按钮弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });

    };

    /**
     * AJAX异步删除
     * @param url
     */
    let handlerDeleteData = function(url) {
        $("#modal-default").modal("hide");
        //如果没有选择数据项则关闭模态框
        if (_idArray.length > 0) {
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    //设置为同步执行
                    "async": false,
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {

                        //请求成功后，无论是成功或是失败，都需要弹出模态框进行提示，所以这里需要先解绑
                        $("#btnModalOk").unbind("click");

                        //删除成功
                        if (data.status === 200) {
                            //刷新页面
                            $("#btnModalOk").bind("click", function () {
                                window.location.reload();
                            });
                        }

                        //删除失败
                        else {
                            //确定按钮的时间改为隐藏模态框
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }

                        //因为无论如何都要提示信息，所以这里的模态框是必须调用的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                })
            }, 500)

        }
    };

    //初始化DataTables
    let handlerInitDataTables = function (url, columns) {
        let _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function (setting, json) {
                handlerInitCheckbox();
                handleCheckboxAll();
            }
        });
        return _dataTable;
    };


    /**
     * 初始化Dropzone
     * @param opts
     */
    let handleInitDropzone = function (opts) {
        //关闭Dropzone的自动发现功能
        Dropzone.autoDiscover = false;
        //继承
        let options = $.extend(defaultDropzone, opts);
        new Dropzone(options.id, options);
    };

    /**
     * 查看详情
     * @param url
     */
    let handlerShowDetail = function (url) {
        //这里是通过ajax请求html的方式，将jsp装载进模态框中
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 初始化zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    let handleInitZTree = function (url, autoParam, callback) {
        let setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam,
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click", function () {
            let zTree = $.fn.zTree.getZTreeObj("myTree");
            let nodes = zTree.getSelectedNodes();
            //未选择
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            }
            //已选择
            else {
                callback(nodes);
            }
        })
    };


    return {

        /**
         * 初始化
         */
        init: function() {
            handlerInitCheckbox();
            handleCheckboxAll();
        },

        /**
         * 删除单笔数据
         */
        deleteSingle: function(url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handleDeleteMulti(url);
        },

        /**
         * 初始化DataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function(url, autoParam, callback) {
            handleInitZTree(url, autoParam, callback);
        },

        /**
         * 初始化Dropzone
         * @param opts
         */
        initDropzone: function(opts) {
            handleInitDropzone(opts);
        },

        /**
         * 显示详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        }

    }

}();


$(document).ready(function () {
    App.init();
});